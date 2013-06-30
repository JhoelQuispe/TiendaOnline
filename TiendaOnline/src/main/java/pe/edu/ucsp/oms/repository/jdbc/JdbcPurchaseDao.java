package pe.edu.ucsp.oms.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Repository;

import pe.edu.ucsp.oms.domain.Purchase;
import pe.edu.ucsp.oms.repository.PurchaseDao;

@Repository
public class JdbcPurchaseDao extends JdbcGenericDao<Purchase, Long> implements
		PurchaseDao {

	private final MediaContentMapper mapper = new MediaContentMapper();
	protected PasswordEncoder encoder = new Md5PasswordEncoder();

	@Override
	public void update(Purchase purchase) {
		String sql = "UPDATE " + getTableName()
				+ " SET date = :downloadDate WHERE id = :id";
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(
				purchase);
		jdbcTemplate.update(sql, parameterSource);
	}

	@Override
	protected SimpleJdbcInsert createJdbcInsert() {
		return new SimpleJdbcInsert(jdbcTemplate.getDataSource())
				.withTableName(getTableName()).usingGeneratedKeyColumns("id_content");
	}

	@Override
	protected RowMapper<Purchase> getRowMapper() {
		return mapper;
	}

	@Override
	protected String getTableName() {
		return Purchase.TABLE_NAME;
	}

	public static class MediaContentMapper implements RowMapper<Purchase> {
		public Purchase mapRow(ResultSet rs, int rowNum) throws SQLException {
			Purchase mediaContent = new Purchase();
			mediaContent.setId(rs.getLong("id_content"));
			mediaContent.setUsusuarioId(rs.getLong("usuario_id"));
			mediaContent.setProductoId(rs.getLong("producto_id"));
			mediaContent.setDownloadDate(rs.getDate("fecha"));
			return mediaContent;
		}
	}
}
