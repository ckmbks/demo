package ckmbks.demo.domain.user.repository;

import ckmbks.demo.domain.user.Role;
import ckmbks.demo.domain.user.dto.UserDto;
import ckmbks.framework.query.PageData;
import ckmbks.framework.query.QueryParams;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.var;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDtoRepository{
//    default PageData<UserDto> getPage(QueryParams params) {
//        PageHelper.startPage(params.getPageIndex(), params.getPageSize(),params.getOrder());
//        var list = getPage(params.getWhere());
//        return new PageData(list, ((Page) list).getTotal());
//
//    }

}