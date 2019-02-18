package ckmbks.demo.domain.user.repository;

import ckmbks.demo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);

    //查询未完成
    @Query(value = "select u from User u join UserLoginToken ult on ult.userId=u.id where ult.token=:token")
    Optional<User> findByToken(String token);

}