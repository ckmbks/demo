package ckmbks.demo.service;

import ckmbks.demo.domain.user.User;
import ckmbks.demo.domain.user.UserLoginToken;
import ckmbks.demo.domain.user.dto.UserDto;
import ckmbks.demo.domain.user.dto.UserLoginTokenDto;
import ckmbks.demo.domain.user.repository.UserLoginTokenRepository;
import ckmbks.demo.domain.user.repository.UserRepository;
import ckmbks.framework.exceptions.AlertException;
import ckmbks.framework.query.PageData;
import ckmbks.framework.query.QueryParams;
import lombok.var;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.MessageFormat;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository UserRepository;
    @Autowired
    public UserLoginTokenRepository UserLoginTokenRepository;

    @PersistenceContext
    public javax.persistence.EntityManager EntityManager;
//
//    @Autowired
//    public UserDtoMapper UserDtoMapper;

    @Autowired
    private ckmbks.framework.query.Db Db;

    public UserLoginTokenDto login(String userName, String password) {
        var user = UserRepository.findByUserName(userName)
                .orElseThrow(() -> new AlertException(MessageFormat.format("不存在用户名{0}！", userName)));
        if (!user.getPassword().equals(password))
            throw new AlertException("密码错误！");

        var token = UserLoginTokenRepository.findByUserId(user.getId())
                .orElseGet(() -> new UserLoginToken(user));
        UserLoginTokenRepository.save(token);
        return new UserLoginTokenDto(token);
    }

    @Override
    public User getById(long id) {
        return UserRepository.findById(id)
                .orElseThrow(() -> new AlertException(MessageFormat.format("不存在用户id={0}！", id)));
    }

    @Override
    public User getByToken(String token) {
        return UserRepository.findByToken(token)
                .orElseThrow(() -> new AlertException("用户登录已失效或未登录！"));
    }

    @Override
    public PageData<UserDto> getPage(QueryParams params) {
//        var sql = "SELECT new ckmbks.demo.domain.user.dto.UserDto(u.id,u.userName,u.phone,u.userType,u.sex,u.createTime,u.createUser,u.weight,'admin') from User u";
//        var query = EntityManager.createQuery("SELECT new ckmbks.demo.domain.user.dto.UserDto(u.id,u.userName,u.phone,u.userType,u.sex,u.createTime,u.createUser,u.weight,'admin') from User u");
//        List result = query.query();
//
//        EntityManager.close();
//        var page = new PageData<UserDto>();
//        page.setList(result);
//        return page;
        return Db.getPageData("user.getPage", params, UserDto.class);

//分页时，实际返回的结果list类型是Page<E>，如果想取出分页信息，需要强制转换为Page<E>
//        return new PageData(list,((Page) list).getTotal());

//        RowBounds rowBounds = new RowBounds(offset, limit);
//        var list = UserDtoMapper.getClassName(filters,rowBounds);
////包装为PageInfo对象
//        PageInfo page = new com.github.pagehelper.PageInfo(list);
//        var result = UserDtoMapper.getPage();
//        return new PageData(result, result.size());


//
//        Map<String, Object> map = new HashMap<>();
//        PageHelper.startPage(PaginationContext.getPageNum(), PaginationContext.getPageSize());
//        List<User> list = this.userMapper.getUserByNoAndEmail(map);
//        return new PageBean<User>(list);
    }

}
