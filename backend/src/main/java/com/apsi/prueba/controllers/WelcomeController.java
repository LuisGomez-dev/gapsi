package com.apsi.prueba.controllers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    @Value("${app.candidato}")
    private String candidatoNombre;

    @Value("${app.version}")
    private String appVersion;

    @GetMapping("/candidato")
    public String getCandidatoNombre() {
        return candidatoNombre;
    }

    @GetMapping("/version")
    public String getAppVersion() {
        return appVersion;
    }
}