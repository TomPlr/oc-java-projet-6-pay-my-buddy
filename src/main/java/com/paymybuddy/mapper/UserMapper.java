package com.paymybuddy.mapper;

import com.paymybuddy.dto.ProfileFormDto;
import com.paymybuddy.dto.UserDto;
import com.paymybuddy.entity.UserEntity;
import com.paymybuddy.model.UserModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(UserEntity userEntity);

    UserEntity toEntity(UserDto userDto);

    UserDto toUserDto(ProfileFormDto profileFormDto);

    ProfileFormDto toProfileFormDto(UserModel userModel);
}
