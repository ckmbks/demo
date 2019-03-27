package ckmbks.demo.domain.user.dto;

import ckmbks.demo.domain.user.User;
import ckmbks.demo.domain.user.enums.Sex;
import ckmbks.demo.domain.user.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserDto {
    public UserDto(long id, String userName, String phone, UserType userType, Sex sex, Date createTime, String createUser,BigDecimal weight, String roles) {
        this();
        this.id = id;
        this.userName = userName;
        this.phone = phone;
        this.userType = userType;
        this.sex = sex;
        this.createTime = createTime;
        this.createUser = createUser;
        this.weight = weight;
        this.roles = new ArrayList<String>();
        this.roles.add(roles);
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
        roles = user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());
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
