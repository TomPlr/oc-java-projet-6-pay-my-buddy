package com.paymybuddy.model;

import java.util.List;

public record UserModel(int id, String username, String email, Boolean active, List<String> connections) {
}
