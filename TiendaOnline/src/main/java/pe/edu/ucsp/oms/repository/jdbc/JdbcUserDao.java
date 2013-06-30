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

import pe.edu.ucsp.oms.domain.User;
import pe.edu.ucsp.oms.repository.UserDao;

@Repository
public class JdbcUserDao extends JdbcGenericDao<User, Long> implements
		UserDao {

	private final UserMapper mapper = new UserMapper();
	protected PasswordEncoder encoder = new Md5PasswordEncoder();

	@Override
	public void update(final User user) {
		String sql = "UPDATE " + getTableName() + " SET username = ? , password=?, first_name = ?, last_name = ?, email = ?  WHERE id = ?";
		jdbcTemplate.update(sql,user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getId());
	}

	@Override
	public void save(User user) {
		user.setPassword(encoder.encodePassword(user.getPassword(), null));
	  super.save(user);
	}

	@Override
	public User findByEmail(String email) {
	    String sql = "SELECT * FROM " + getTableName() + " WHERE email = :email";
	    SqlParameterSource namedParameters = new MapSqlParameterSource("email", email);
	    return jdbcTemplate.queryForObject(sql, getRowMapper(), namedParameters);
	}

	@Override
	public List<User> filterByEmail(String email) {
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
	protected RowMapper<User> getRowMapper() {
		return mapper;
	}

	@Override
	protected String getTableName() {
		return User.TABLE_NAME;
	}

	public static class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setFirstName(rs.getString("first_name"));
			user.setLastName(rs.getString("last_name"));
			user.setEmail(rs.getString("email"));
			user.setState(rs.getBoolean("state"));
			user.setBalance(rs.getDouble("balance"));
			user.setPoints(rs.getInt("points"));
			return user;
		}
	}

	@Override
	public boolean existsUser(String username, String password) {
		String sql = "SELECT * FROM " + getTableName() + " WHERE username = ? and password = ?";
        return jdbcTemplate.queryForRowSet(sql, username, password).next();        
	}
}
