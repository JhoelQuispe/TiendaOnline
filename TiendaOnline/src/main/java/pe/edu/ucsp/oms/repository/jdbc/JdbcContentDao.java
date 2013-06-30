package pe.edu.ucsp.oms.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import pe.edu.ucsp.oms.domain.Category;
import pe.edu.ucsp.oms.domain.Content;
import pe.edu.ucsp.oms.domain.MediaType;
import pe.edu.ucsp.oms.domain.Promo;
import pe.edu.ucsp.oms.repository.ContentDao;


@Repository
public class JdbcContentDao extends JdbcGenericDao<Content, Long> implements ContentDao {

	private final ContentMapper mapper = new ContentMapper();
	
	@Override
	public void update(Content content) {
		String sql = "UPDATE " + getTableName() + " SET name=?, autor=?, description=?, price=? WHERE id=?";
		jdbcTemplate.update(sql, content.getName(), content.getAutor(), content.getDescription(), content.getPrice(), content.getId());
	}

	@Override
	protected SimpleJdbcInsert createJdbcInsert() {
		return new SimpleJdbcInsert(jdbcTemplate.getDataSource())
		.withTableName(getTableName()).usingGeneratedKeyColumns("id");
	}

	@Override
	protected RowMapper<Content> getRowMapper() {
		return mapper;
	}

	@Override
	protected String getTableName() {
		return Content.TABLE_NAME;
	}
	
	public final class ContentMapper implements RowMapper<Content> {
		public Content mapRow(ResultSet rs, int rowNum) throws SQLException {
			Content content = new Content();
			content.setId(rs.getLong("id"));
			content.setName(rs.getString("name"));
			content.setAutor(rs.getString("autor"));
			content.setDescription(rs.getString("description"));
			content.setPrice(rs.getFloat("price"));
			content.setSize(rs.getInt("size"));
			content.setTimesDownload(rs.getInt("times_download"));
			content.setCategory(new Category());
			content.setPromo(new Promo());
			content.setMediaType(new MediaType());
			content.setContent(rs.getString("content"));
			return content;
		}
	}

}
