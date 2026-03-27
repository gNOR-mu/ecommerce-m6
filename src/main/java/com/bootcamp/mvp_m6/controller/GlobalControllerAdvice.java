package com.bootcamp.mvp_m6.controller;

import com.bootcamp.mvp_m6.model.User;
import com.bootcamp.mvp_m6.service.CartService;
import com.bootcamp.mvp_m6.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(basePackages = "com.bootcamp.mvp_m6.controller")
public class GlobalControllerAdvice {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    /*Inyecta el nombre de la aplicación desde el properties*/
    @Value("${app.app-name}")
    private String appName;

    /**
     * Obtiene el nombre "comercial" de la aplicación
     *
     * @return nombre comercial de la app
     */
    @ModelAttribute("appName")
    public String populateAppName() {
        return appName;
    }

    /**
     * Inyecta la URI activa
     * @param request
     * @return URI actual
     */
    @ModelAttribute("requestURI")
    public String getRequestURI(HttpServletRequest request) {
        return request.getRequestURI();
    }

    @ModelAttribute("cartItemCount")
    public int globalCartItemCount(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return 0;
        }


        try {
            User user = userService.getByEmail(userDetails.getUsername());
            return cartService.getCarItemCount(user);
        } catch (Exception e) {
            //TODO cambiar a log
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
