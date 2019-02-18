package ckmbks.demo.dataaccess;

import ckmbks.demo.domain.user.dto.UserDto;

import java.util.List;

public interface UserDtoMapper {
    List<UserDto> getPage();
}
