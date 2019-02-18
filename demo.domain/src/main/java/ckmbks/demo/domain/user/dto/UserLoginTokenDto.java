package ckmbks.demo.domain.user.dto;

import ckmbks.demo.domain.user.UserLoginToken;
import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class UserLoginTokenDto {
    public UserLoginTokenDto() {
    }

    public UserLoginTokenDto(UserLoginToken userLoginToken) {
        this();
        userId = userLoginToken.getUserId();
        token = userLoginToken.getToken();
        createTime = userLoginToken.getCreateTime();
    }

    @Id
    private String token;

    private long userId;

    private String[] roles = new String[]{
            "admin"
    };

    private Date createTime = new Date();

}
