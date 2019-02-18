package ckmbks.demo.domain.user;

import ckmbks.framework.domain.AggregateRoot;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class UserLoginToken implements AggregateRoot {
    public UserLoginToken() {
    }

    public UserLoginToken(User user) {
        this();
        userId = user.getId();
        token = UUID.randomUUID().toString();
    }

    @Id
    private String token;

    private long userId;

    private Date createTime = new Date();

}
