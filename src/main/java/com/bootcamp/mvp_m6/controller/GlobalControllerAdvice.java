package com.bootcamp.mvp_m6.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(basePackages = "com.bootcamp.mvp_m6.infrastructure.adapters.input.web")
public class GlobalControllerAdvice {

    /*Inyecta el nombre de la aplicación desde el properties*/
    @Value("${app.app-name}")
    private String appName;

    /**
     * Obtiene el nombre "comercial" de la aplicación
     * @return nombre comercial de la app
     */
    @ModelAttribute("appName")
    public String populateAppName(){
        return appName;
    }
}
