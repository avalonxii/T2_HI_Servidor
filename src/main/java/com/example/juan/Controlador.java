package com.example.juan;

import com.example.juan.model.Login;
import com.example.juan.model.UsuariosEntity;
import com.example.juan.repositories.poemas.PoemasService;
import com.example.juan.repositories.usuarios.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Controlador {
    private Login login;

    @Autowired private UsuariosService usuarioService;

    @Autowired private PoemasService poemaService;

    @Autowired public void setLogin(Login login) {
        this.login = login;
    }

    /*-----------------------------------------------------------------------------------------Ruta inicial-----------------------------*/
    @GetMapping("/")
    public String index(ModelMap model) {

        setLogin(login);

        return "index";
    }

    /*--------------------------------------------------------------- Funciones que tienen que ver con el Usuario ----------------*/
    @GetMapping("/loginRegister")
    public String peticionloginregister(ModelMap model) {

        model.addAttribute("loginRegister", new UsuariosEntity());

        return "loginRegister";
    }

//    @PostMapping("/comprobarRegistro") //Comprobar si el Usuario existe, si no existe, se registra
//    public String peticionComprobarRegistro(UsuariosEntity user) {
//
//        //comprobar si el usuario esta registrado en la base de datos(comprobando si el email existe)
//        //si no esta registrado, se registra
//        if(usuarioService.findByEmail(user.getEmail())){
//            System.out.println("El Correo: "+user.getEmail()+" ya existe");
//
//            return "redirect:/loginRegister"; // lo reenvio a la ruta 'loginRegister'
//        }
//        else{
//            System.out.println("El usuario no existe");
//            usuarioService.guardarUsuario(user); //Guarda el Usuario en la base de datos
//            inicarLogin(user);
//            return "redirect:/"; // lo reenvio a la ruta '/'
//        }
//    }


}
