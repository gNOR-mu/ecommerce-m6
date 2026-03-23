package com.bootcamp.mvp_m6.dto.user;

import com.bootcamp.mvp_m6.enums.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserPrivateRegisterDTO {

    @NotBlank(message = "El nombre no puede estar en blanco")
    private String name;

    private String cellphone;

    @NotNull
    @Email(message = "Correo inválido")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "La contraseña no puede estar en blanco")
    @Size(min = 8, message = "El largo mínimo para la contraseña son 8 caracteres")
    @Size(max = 100, message = "El largo máximo para la contraseña son 100 caracteres")
    private String password;

    @NotNull
    private Role role;
}
