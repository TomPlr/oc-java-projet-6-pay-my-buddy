package com.paymybuddy.assembler;

import com.paymybuddy.entity.UserEntity;
import com.paymybuddy.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAssembler {

    public UserModel toModel(UserEntity user) {
        List<String> connections = user.getConnections().stream().map(UserEntity::getUsername).toList();

        return new UserModel(user.getUsername(), user.getEmail(), user.getActive(), connections);
    }

}
