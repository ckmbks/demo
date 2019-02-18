package ckmbks.framework.query;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

@Data
public class PageData<T> extends PageInfo {
    public PageData() {
    }

    public PageData(List<T> list, long total) {
        setList(list);
        setTotal(total);
    }
}
