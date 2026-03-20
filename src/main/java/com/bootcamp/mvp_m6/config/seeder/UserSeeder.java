package com.bootcamp.mvp_m6.config.seeder;

import com.bootcamp.mvp_m6.dto.user.UserPrivateRegisterDTO;
import com.bootcamp.mvp_m6.dto.user.UserPublicRegisterDTO;
import com.bootcamp.mvp_m6.enums.Role;
import com.bootcamp.mvp_m6.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSeeder implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        /*Usuario administrado*/
        UserPrivateRegisterDTO admin = UserPrivateRegisterDTO.builder()
                .email("admin@email.cl")
                .password("admin1234")
                .rut("12345678-9")
                .cellphone("+56900001111")
                .name("admin")
                .role(Role.ADMIN)
                .build();

        /*Usuario normal*/
        UserPublicRegisterDTO user = UserPublicRegisterDTO.builder()
                .email("user@email.cl")
                .password("user1234")
                .rut("12345679-k")
                .cellphone("+56911112222")
                .name("usuario")
                .build();


        userService.createPrivateUser(admin);
        userService.createPublicUser(user);
    }
}
