package ckmbks.demo.apiserver;

import ckmbks.demo.domain.user.dto.UserDto;
import ckmbks.demo.service.UserService;
import ckmbks.framework.json.JSONUtil;
import ckmbks.framework.query.PageData;
import ckmbks.framework.query.QueryParams;
import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApiserverApplicationTests {
    @Autowired
    private UserService userService;

    @Autowired
    private ckmbks.framework.query.Db Db;

    @Test
    public void JsonTest() {
        var dto1 = new PageData<Object>();
        dto1.getList().add(new UserDto());

        var str1 = JSONUtil.toJsonStr(dto1);

        var dto2 = userService.getPage(new QueryParams().fill());
        dto1.getList().add(new UserDto());

        var str2 = JSONUtil.toJsonStr(dto2);
    }

    @Test
    public void OtherJsonTest() {
        var sql = "select * from user where id=:userId";
        var param = new HashMap<String, Object>();
        param.put("userId", "1");
        var result1 = Db.query(sql, param,UserDto.class);
        var str1 = JSONUtil.toJsonStr(result1);
    }

}
