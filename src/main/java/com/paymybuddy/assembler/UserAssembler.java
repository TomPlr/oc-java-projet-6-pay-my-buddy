package com.paymybuddy.assembler;

import com.paymybuddy.entity.UserEntity;
import com.paymybuddy.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAssembler {

    public UserModel toModel(UserEntity user) {
        List<String> connections = user.getConnections().stream().map(UserEntity::getUsername).toList();

        return new UserModel(user.getId(), user.getUsername(), user.getEmail(), user.getActive(), connections);
    }

//    public List<UserModel> toModels(List<UserEntity> users) {
//        List<String> connections = new ArrayList<>();
//        users.forEach(user -> user.getConnections().forEach(connection -> connections.add(connection.getUsername())));
//
//        return users.stream().map(user -> new UserModel(user.getId(), user.getUsername(), user.getEmail(), user.getActive(), connections)).toList();
//    }
}
