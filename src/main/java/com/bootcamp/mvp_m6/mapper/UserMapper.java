package com.bootcamp.mvp_m6.mapper;

import com.bootcamp.mvp_m6.dto.user.UserPrivateRegisterDTO;
import com.bootcamp.mvp_m6.dto.user.UserPublicRegisterDTO;
import com.bootcamp.mvp_m6.model.User;

public class UserMapper {

    public static User toEntity(UserPublicRegisterDTO dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .cellphone(dto.getCellphone())
                .rut(dto.getRut())
                .passHash(dto.getPassword())
                .build();
    }

    public static User toEntity(UserPrivateRegisterDTO dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .cellphone(dto.getCellphone())
                .rut(dto.getRut())
                .role(dto.getRole())
                .passHash(dto.getPassword())
                .build();
    }
}
