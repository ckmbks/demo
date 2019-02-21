package ckmbks.demo.service;


import ckmbks.demo.domain.user.User;
import ckmbks.demo.domain.user.dto.UserDto;
import ckmbks.demo.domain.user.dto.UserLoginTokenDto;
import ckmbks.framework.query.PageData;
import ckmbks.framework.query.QueryParams;

public interface UserService {

    /**
     * 登录
     * @param userName
     * @param password
     */
    UserLoginTokenDto login(String userName, String password);

    /**
     * 获取
     * @param id
     * @return
     */
    User getById(long id);

    User getByToken(String token);

    PageData<UserDto> getPage(QueryParams params);

}
