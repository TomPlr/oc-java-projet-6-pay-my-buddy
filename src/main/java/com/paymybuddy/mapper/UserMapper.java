package com.paymybuddy.mapper;

import com.paymybuddy.dto.UserDto;
import com.paymybuddy.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(UserEntity userEntity);
}
