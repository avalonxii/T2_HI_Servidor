package com.example.juan.model;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class Login {
    private UsuariosEntity login;

    public Login() {
        this.login = null;
        // Sin inicio de sesión por el momento.
    }

    public UsuariosEntity getLogin() {
        return login;
    }

    public void setLogin(UsuariosEntity login) {
        this.login = login;
    }
}
