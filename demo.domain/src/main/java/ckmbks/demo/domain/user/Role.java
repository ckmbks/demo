package ckmbks.demo.domain.user;

import ckmbks.framework.domain.AggregateRoot;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Role implements AggregateRoot {

    @Id
    private long id;

    private String name = "";

    private Date createTime = new Date();

    private String createUser = "";

}
