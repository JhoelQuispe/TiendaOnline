package pe.edu.ucsp.oms.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Repository;

import pe.edu.ucsp.oms.domain.Administrator;
import pe.edu.ucsp.oms.repository.AdministratorDao;

@Repository
public class JdbcAdministratorDao extends JdbcGenericDao<Administrator, Long> implements AdministratorDao{

	private final AdministratorMapper mapper = new AdministratorMapper();
	protected PasswordEncoder encoder = new Md5PasswordEncoder();

	@Override
	public void update(final Administrator admi) {
		String sql = "UPDATE " + getTableName() + " SET first_name = ?, last_name = ?, email = ?, adminame = ? WHERE id = ?";
		jdbcTemplate.update(sql, admi.getFirstName(), admi.getLastName(), admi.getEmail(), admi.getUsername(), admi.getId());
	}

	@Override
	public void save(Administrator admi) {
		admi.setPassword(encoder.encodePassword(admi.getPassword(), null));
	  super.save(admi);
	}

	@Override
	public Administrator findByEmail(String email) {
    String sql = "SELECT * FROM " + getTableName() + " WHERE email = :email";
    SqlParameterSource namedParameters = new MapSqlParameterSource("email", email);
    return jdbcTemplate.queryForObject(sql, getRowMapper(), namedParameters);
	}

	@Override
	public List<Administrator> filterByEmail(String email) {
    String sql = "SELECT * FROM " + getTableName() + " WHERE email LIKE :email";
    SqlParameterSource namedParameters = new MapSqlParameterSource("email", email);
    return jdbcTemplate.query(sql, getRowMapper(), namedParameters);
	}

	@Override
	protected SimpleJdbcInsert createJdbcInsert() {
		return new SimpleJdbcInsert(jdbcTemplate.getDataSource())
				.withTableName(getTableName()).usingGeneratedKeyColumns("id");
	}

	@Override
	protected RowMapper<Administrator> getRowMapper() {
		return mapper;
	}

	@Override
	protected String getTableName() {
		return Administrator.TABLE_NAME;
	}

	public static class AdministratorMapper implements RowMapper<Administrator> {
		public Administrator mapRow(ResultSet rs, int rowNum) throws SQLException {
			Administrator admi = new Administrator();
			admi.setId(rs.getLong("id"));
			admi.setUsername(rs.getString("adminame"));
			admi.setFirstName(rs.getString("first_name"));
			admi.setLastName(rs.getString("last_name"));
			admi.setPassword(rs.getString("password"));
			admi.setEmail(rs.getString("email"));
			return admi;
		}
	}
}