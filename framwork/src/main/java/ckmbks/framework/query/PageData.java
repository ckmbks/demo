package ckmbks.framework.query;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageData<T> {
    public PageData() {
    }

    public PageData(List<T> list, long total) {
        setList(list);
        setTotal(total);
    }

    long total;

    List<T> list = new ArrayList<T>();
}
