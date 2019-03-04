package ckmbks.framework.query;

import ckmbks.framework.map.CaseInsensitiveMap;
import ckmbks.framework.util.StrUtil;
import lombok.var;

import java.util.ArrayList;
import java.util.Map;

public class ReportMapperExecutor {
    public ReportMapperExecutor(Db db, String reportName, QueryParams queryParams) {
        Db = db;
        this.reportMapper = ReportMapper.Create(reportName);
        if (this.reportMapper == null)
            throw new RuntimeException(StrUtil.format("报表{0}不存在！", reportName));
        this.queryParams = queryParams;
    }

    private ReportMapper reportMapper;

    private QueryParams queryParams;

//
//    private String prepareSql;
//
//    private String reportSql;
//
//    private String totalSql;
//
//    private String orderSql;

    private Map<String, String> paramGroupMap = new CaseInsensitiveMap<>();

    private Map<String, String> sqlParams = new CaseInsensitiveMap<>();

    protected Db Db;

    /// <summary>
    /// 获取分页数据(注意：该方法不包括在事务中！)
    /// </summary>
    /// <param name="reportName"></param>
    /// <param name="sqlParams"></param>
    /// <returns></returns>
    public <T> PageData<T> getPageData() {
        generateParamGroupStr();
        var reportsql = generateReportSql();
        var prepareSql = generatePrepareSql();
        var totalSql = generateTotalSql();
        var orderSql = generateOrderSql();

        totalSql = prepareSql + totalSql;

        var offset = (queryParams.getPageIndex() - 1) * queryParams.getPageSize();
        reportsql = StrUtil.format("{0} {1} limit {2} offset {3}", reportsql, orderSql, queryParams.getPageSize(), offset);
        var sql = prepareSql + reportsql;

        var result = new PageData<T>();
        result.setList(Db.getResultList(sql, sqlParams));
        result.setTotal(Db.getCount(totalSql, sqlParams));

        return result;
    }

    public String generatePrepareSql() {
        return reportMapper.getPrepareSql();
    }

    public String generateReportSql() {
        var result = reportMapper.getReportSql();
        for (var key :
                paramGroupMap.keySet()) {
            result = StrUtil.replace(result, "@" + key, paramGroupMap.get(key));
        }
        return result;
    }

    public String generateTotalSql() {
        var result = reportMapper.getTotalSql();
        for (var key :
                paramGroupMap.keySet()) {
            result = StrUtil.replace(result, "@" + key, paramGroupMap.get(key));
        }
        return result;
    }

    public String generateOrderSql() {
        if (!StrUtil.isEmpty(queryParams.getOrder()))
            return "ORDER BY " + queryParams.getOrder();
        if (StrUtil.isEmpty(reportMapper.getOrderSql()))
            return reportMapper.getOrderSql();

        return "ORDER BY Id DESC";
    }


    private void generateParamGroupStr() {
        for (var paramGroup : reportMapper.getParamGroups()) {
            var paramGroupList = new ArrayList<String>();

            var where = queryParams.getWhere();

            for (var param : paramGroup.getParams()) {
                if (param.isFixed()) {
                    paramGroupList.add(param.getValue());
                    continue;
                }

                for (var key : where.keySet()) {
                    if (!StrUtil.equalsIgnoreCase(key, param.getName()))
                        continue;

                    var sql = param.getValue();
                    if (!StrUtil.isEmpty(sql)) {
                        paramGroupList.add(param.getValue());

                        if (!sqlParams.containsKey(key)) {
                            sqlParams.put(key, where.get(key));
                        }
                    }
                }
            }

            if (paramGroupList.size() > 0) {
                var sql = StrUtil.join(" AND ", paramGroupList.toArray());
                paramGroupMap.put(paramGroup.getName(), sql);
            } else {
                paramGroupMap.put(paramGroup.getName(), "");
            }
        }
    }

}
