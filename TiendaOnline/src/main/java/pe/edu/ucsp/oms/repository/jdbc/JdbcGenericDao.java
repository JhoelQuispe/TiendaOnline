package pe.edu.ucsp.oms.repository.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import pe.edu.ucsp.oms.domain.BaseEntity;
import pe.edu.ucsp.oms.repository.GenericDao;

public abstract class JdbcGenericDao<T extends BaseEntity<PK>, PK extends Number> implements GenericDao<T, PK> {

	protected JdbcTemplate jdbcTemplate;
	protected SimpleJdbcInsert jdbcInsert;
	
	protected abstract SimpleJdbcInsert createJdbcInsert();

	protected abstract RowMapper<T> getRowMapper();

	protected abstract String getTableName();

	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcInsert = createJdbcInsert();
    }
    
	@Override
    public long count() {
        String sql = "select count(*) from " + getTableName();
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

	@Override
	@SuppressWarnings("unchecked")
    public void save(T entity) {
    	Number generatedId = jdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(entity));
    	entity.setId((PK)generatedId);
    }
	
	@Override
	public boolean exists(PK id) {
        String sql = "SELECT id FROM " + getTableName() + " WHERE id = ?";
        return jdbcTemplate.queryForRowSet(sql, id).next();
	}
	
	@Override
	public List<T> findAll() {
		String sql = "SELECT * FROM " + getTableName();        
        return jdbcTemplate.query(sql, getRowMapper());
	}
	
	@Override
	public T find(PK id) {
        String sql = "SELECT * FROM " + getTableName() + " WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, getRowMapper(), id);
	}
	
	@Override
	public void remove(T entity) {
		removeById(entity.getId());
	}
	
	@Override
	public void removeById(PK id) {
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?", id);
	}
}
