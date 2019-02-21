package ckmbks.framework.query;

import ckmbks.framework.convert.Convert;
import ckmbks.framework.http.HttpUtil;
import ckmbks.framework.map.CaseInsensitiveLinkedMap;
import ckmbks.framework.util.ArrayUtil;
import ckmbks.framework.util.ObjectUtil;
import ckmbks.framework.util.StrUtil;
import lombok.Data;
import lombok.var;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
public class QueryParams {
    public QueryParams() {
    }

    public QueryParams(int index, int size, Map<String, String> where) {
        setPageIndex(index);
        setPageSize(size);
        setWhere(where);
    }

    /// <summary>
    /// 将HttpContext.Current.Request的request.QueryString和request.Form转换并填充到SqlParams
    /// </summary>
    /// <returns></returns>
    public QueryParams fill() {
        var request = HttpUtil.getRequest();
        if (!StrUtil.isEmpty(request.getParameter("page")))
            pageIndex = Convert.toInt(request.getParameter("page"));

        if (!StrUtil.isEmpty(request.getParameter("rows")))
            pageSize = Convert.toInt(request.getParameter("rows"));

        if (!StrUtil.isEmpty(request.getParameter("sort")))
            order = request.getParameter("sort") + request.getParameter("order");

        fillWhere(request);

        return this;
    }

    protected void fillWhere(HttpServletRequest request) {
        var map = request.getParameterMap();
        for (var key : map.keySet()) {
            if (StrUtil.isEmpty(key) || key.toLowerCase() == "page" || key.toLowerCase() == "rows" || key.toLowerCase() == "order" || key.toLowerCase() == "sort")
                continue;

            var value = ArrayUtil.join(map.get(key), ",");
            if (StrUtil.isEmpty(value))
                continue;

            add(key, value);
        }
    }

    public void add(String key, String value) {
        if (where == null)
            where = new CaseInsensitiveLinkedMap<>();
        if (containsKey(key))
            where.remove(key);

        if (StrUtil.isEmpty(key) || ObjectUtil.isNull(value) || (value instanceof String && StrUtil.isEmpty(Convert.toStr(value))))
            return;

        where.put(key, value);
    }

    private Map<String, String> where = new CaseInsensitiveLinkedMap<>();

    private String order = "";

    private int pageSize = 50;

    private int pageIndex = 1;

    public boolean containsKey(String key) {
        return getWhere().containsKey(key);
    }
}
