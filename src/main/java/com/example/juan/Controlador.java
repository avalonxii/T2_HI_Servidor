package com.example.juan;

import com.example.juan.model.Login;
import com.example.juan.model.PoemasEntity;
import com.example.juan.model.UsuariosEntity;
import com.example.juan.repositories.poemas.PoemasService;
import com.example.juan.repositories.usuarios.UsuariosService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

        //mostrar mensajito

        return "loginRegister";
    }

    @PostMapping("/comprobarRegistro") //Comprobar si el Usuario existe, si no existe, se registra
    public String peticionComprobarRegistro(ModelMap model, UsuariosEntity user) {

        //comprobar si el usuario esta registrado en la base de datos(comprobando si el email existe)
        //si no esta registrado, se registra
        if(usuarioService.findByEmail(user.getEmail())){


            return "redirect:/loginRegister"; // lo reenvio a la ruta 'loginRegister'
        }
        else if(usuarioService.findByUsername(user.getNombre())) {


            return "redirect:/loginRegister";
        }
        else{
            usuarioService.guardarUsuario(user); //Guarda el Usuario en la base de datos
            inicarLogin(user);
            return "redirect:/"; // lo reenvio a la ruta '/'
        }
    }

    @RequestMapping("/comprobarLogin")//comprobar si el usuario existe, si no existe se le redirige a la pagina de registerLogin
    public String peticionComprobarLogin(ModelMap model, UsuariosEntity user) {

        //comprobar si el usuario esta registrado en la base de datos (comprobando si el email existe)
        //que pueda introducir el email o el nombre del usuario //comprobar si la contraseña es correcta
        if(usuarioService.confirmarUsuario(user.getIniciar(), user.getPssw())){

            this.login.setLogin(usuarioService.findUsuario(user.getIniciar()));
            return "redirect:/"; // lo reenvio a la ruta '/'

        }
        else{
            return "redirect:/loginRegister"; // lo reenvio a la ruta 'loginRegister'
        }
    }

    @RequestMapping("/cerrarSesion")
    public void peticionCerrar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        this.login.setLogin(null);
        response.sendRedirect("/");
    }

    /*--------------------------------------------------------------  Funciones que tienen que ver con los Poemas----------------*/

    //formulario para crear poemas
    @RequestMapping("/crearPoema")
    public String crearPoema(ModelMap model) {

        model.addAttribute("usuarioActual", login.getLogin());
        model.addAttribute("poema", new PoemasEntity());

        return "crearPoema";
    }

    //guardar el poema en la base de datos
    @RequestMapping("/guardarPoema")
    private String guardarPoema(PoemasEntity poema) {

        poema.setAutor(login.getLogin());

        poemaService.guardarPoema(poema); //grada el poema

        return "redirect:/misPoemas";  //cambiar a ver tus poemas
    }

    //ver todas los poemas
    @RequestMapping("/verPoemas")
    public String verPoemas(ModelMap model){

        model.addAttribute("poemas", poemaService.mostrarPoemas());
        model.addAttribute("usuarioActual", login.getLogin());

        return "verPoemas";
    }

    @RequestMapping("/misPoemas")
    public String peticion7(ModelMap model) {

        System.out.println(login.getLogin().getId());

        model.addAttribute("poemas", poemaService.buscarPoemasAutor(login.getLogin().getId()));
        model.addAttribute("usuarioActual", login.getLogin());

        return "misPoemas";
    }

    @RequestMapping("/eliminarReco/{id}")
    public String peticion8(@PathVariable int id) {

        poemaService.eliminarPoema(id);

        return "redirect:/misRecomendaciones";
    }

}
