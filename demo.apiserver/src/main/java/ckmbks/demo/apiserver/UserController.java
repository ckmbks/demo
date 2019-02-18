package ckmbks.demo.apiserver;

import ckmbks.demo.domain.user.dto.UserDto;
import ckmbks.demo.domain.user.dto.UserLoginTokenDto;
import ckmbks.framework.query.PageData;
import ckmbks.demo.service.UserService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseApiController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/login", method = {RequestMethod.GET, RequestMethod.POST})
    public UserLoginTokenDto login(String userName, String password) {
        return userService.login(userName, password);
    }

    @RequestMapping(value = "/user/getById", method = {RequestMethod.GET, RequestMethod.POST})
    public UserDto getById(long id) {
        var user= userService.getById(id);
        return new UserDto(user);
    }

    @RequestMapping(value = "/user/getByToken", method = {RequestMethod.GET, RequestMethod.POST})
    public UserDto getByToken(String token) {
        var user=  userService.getByToken(token);
        return new UserDto(user);
    }

    @RequestMapping(value = "/user/getPage", method = {RequestMethod.GET, RequestMethod.POST})
    public PageData<UserDto> getPage() {
        return  userService.getPage();
    }

}
