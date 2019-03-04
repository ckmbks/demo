package ckmbks.demo.dataaccess;

import ckmbks.demo.domain.user.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface UserDtoMapper {
    List<UserDto> getPage();
}
