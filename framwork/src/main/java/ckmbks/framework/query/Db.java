package ckmbks.framework.query;

import lombok.var;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Component
public class Db {

    @PersistenceContext
    public EntityManager EntityManager;

    public <T> PageData<T> getPageData(Class<T> tClass, String reportName, QueryParams params) {
        return new ReportMapperExecutor(this, reportName, params).getPageData();
    }


    public <T> List<T> getResultList(String sql, Map<String, String> sqlParameters) {
        var query = EntityManager.createNativeQuery(sql);
        for (var key : sqlParameters.keySet()) {
            query.setParameter(key, sqlParameters.get(key));
        }
        List<T> result = query.getResultList();
        EntityManager.close();
        return result;
    }

    public long getCount(String sql, Map<String, String> sqlParameters) {
        BigInteger result = getSingleResult(sql, sqlParameters);
        return result.longValue();
    }

    public <T> T getSingleResult(String sql, Map<String, String> sqlParameters) {
        var query = EntityManager.createNativeQuery(sql);
        for (var key : sqlParameters.keySet()) {
            query.setParameter(key, sqlParameters.get(key));
        }
        var result = query.getSingleResult();
        EntityManager.close();
        return (T) result;
    }
}
