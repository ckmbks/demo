package ckmbks.framework.query;

import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.var;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Component
public class Db {

    @PersistenceContext
    public EntityManager EntityManager;

    @Value("${spring.datasource.url}")
    private String dataSource;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public NamedParameterJdbcTemplate getJdbcTemplate() {
        var source = new MysqlDataSource();
        source.setUrl(dataSource);
        source.setUser(username);
        source.setPassword(password);
        return new NamedParameterJdbcTemplate(source);
    }

    public PageData getPageData(String reportName, QueryParams params) {
        return new ReportMapperExecutor(this, reportName, params).getPageData();
    }

    public <T> PageData<T> getPageData(String reportName, QueryParams params, Class<T> tClass) {
        return new ReportMapperExecutor(this, reportName, params).getPageData(tClass);
    }


    public List query(String sql, Map<String, Object> sqlParameters, QueryParams queryParams) {
        var query = EntityManager.createQuery(sql);
        query.setFirstResult((queryParams.getPageIndex() - 1) * queryParams.getPageSize());
        query.setMaxResults(queryParams.getPageSize());
        for (var key : sqlParameters.keySet()) {
            query.setParameter(key, sqlParameters.get(key));
        }
        List result = query.getResultList();
        EntityManager.close();
        return result;
    }

    public <T> List<T> query(String sql, Map<String, Object> paramMap, Class<T> tClass) {
        return getJdbcTemplate().query(sql, paramMap, new BeanPropertyRowMapper(tClass));
    }

    public List<Map<String, Object>> query(String sql, Map<String, Object> paramMap) {
        return getJdbcTemplate().queryForList(sql, paramMap);
    }

    public <T> T queryForObject(String sql, Map<String, Object> sqlParameters, Class<T> tClass) {
        return getJdbcTemplate().queryForObject(sql, sqlParameters, tClass);
    }
}
