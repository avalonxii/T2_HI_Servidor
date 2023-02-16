package com.example.juan;

import com.example.juan.model.Login;
import com.example.juan.model.UsuariosEntity;
import com.example.juan.repositories.poemas.PoemasService;
import com.example.juan.repositories.usuarios.UsuariosService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

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

        model.addAttribute("usuarioActual", login.getLogin());

        return "index";
    }

    private void inicarLogin(UsuariosEntity user){this.login.setLogin(user);} //crea el login

    /*--------------------------------------------------------------- Funciones que tienen que ver con el Usuario ----------------*/
    @GetMapping("/loginRegister")
    public String peticionloginregister(ModelMap model) {

        model.addAttribute("loginRegister", new UsuariosEntity());

        return "loginRegister";
    }

    @PostMapping("/comprobarRegistro") //Comprobar si el Usuario existe, si no existe, se registra
    public String peticionComprobarRegistro(UsuariosEntity user) {

        //comprobar si el usuario esta registrado en la base de datos(comprobando si el email existe)
        //si no esta registrado, se registra
        if(usuarioService.findByEmail(user.getEmail())){
            System.out.println("El Correo: "+user.getEmail()+" ya existe");

            return "redirect:/loginRegister"; // lo reenvio a la ruta 'loginRegister'
        }
        else if(usuarioService.findByUsername(user.getNombre())) {
            System.out.println("El Nombre: "+user.getNombre()+" ya existe");

            return "redirect:/loginRegister";
        }
        else{
            System.out.println("El usuario no existe");
            usuarioService.guardarUsuario(user); //Guarda el Usuario en la base de datos
            inicarLogin(user);
            return "redirect:/"; // lo reenvio a la ruta '/'
        }
    }

    @RequestMapping("/comprobarLogin")//comprobar si el usuario existe, si no existe se le redirige a la pagina de registerLogin
    public String peticionComprobarLogin(UsuariosEntity user) {

        //comprobar si el usuario esta registrado en la base de datos (comprobando si el email existe)
        //que pueda introducir el email o el nombre del usuario //comprobar si la contraseña es correcta
        if(usuarioService.confirmarUsuario(user.getIniciar(), user.getPssw())){

            this.login.setLogin(usuarioService.findUsuario(user.getIniciar()));
            return "redirect:/"; // lo reenvio a la ruta '/'

        }
        else{
            System.out.println("El usuario no existe");
            return "redirect:/loginRegister"; // lo reenvio a la ruta 'loginRegister'
        }
    }

    @RequestMapping("/cerrarSesion")
    public void peticionCerrar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        this.login.setLogin(null);
        response.sendRedirect("/");
    }

    /*--------------------------------------------------------------  Funciones que tienen que ver con la Recomendacion ----------------*/



}
