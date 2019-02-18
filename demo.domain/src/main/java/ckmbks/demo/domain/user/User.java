package ckmbks.demo.domain.user;

import ckmbks.framework.domain.AggregateRoot;
import ckmbks.demo.domain.user.enums.Sex;
import ckmbks.demo.domain.user.enums.UserType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class User implements AggregateRoot {

    @Id
    private long id;

    private String userName = "";

    private String password = "";

    private String phone = "";

    @Enumerated
    private UserType userType;

    @Enumerated
    private Sex sex;

    private Date createTime = new Date();

    private String createUser = "";

    private BigDecimal weight;

    @ManyToMany //  级联保存 更新 删除 刷新 延迟加载
    //  也就是关联映射了一张新的表 department_employee 默认是通过两张表名称+ “_” 来命名的 其中新表的department_id字段作为一个外键映射到当前表的“id”字段，
    //  需要映射的表employee的字段employee_id  也作为一个外键映射到新表的employee_id字段
    //    @JoinTable(name = "department_employee",joinColumns = {@JoinColumn(name = "department_id",referencedColumnName = "id")},
    //            inverseJoinColumns = {@JoinColumn(name = "employee_id",referencedColumnName = "employee_id")})
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

}
