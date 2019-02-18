package ckmbks.demo.domain.user.dto;

import ckmbks.demo.domain.user.User;
import ckmbks.demo.domain.user.enums.Sex;
import ckmbks.demo.domain.user.enums.UserType;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDto  {
    public UserDto() {
    }

    public UserDto(User user) {
        this();
        id = user.getId();
        userName = user.getUserName();
        phone = user.getPhone();
        userType = user.getUserType();
        sex = user.getSex();
        createTime = user.getCreateTime();
        createUser = user.getCreateUser();
        roles = user.getRoles().stream().map(role->role.getName()).collect(Collectors.toList());
    }
    @Id
    private long id;

    private String userName = "";

    private String phone = "";

    private UserType userType;

    private Sex sex;

    private Date createTime = new Date();

    private String createUser = "";

    private BigDecimal weight;

    private List<String> roles;

}
