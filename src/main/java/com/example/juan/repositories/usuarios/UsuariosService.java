package com.example.juan.repositories.usuarios;

import com.example.juan.model.UsuariosEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@Service
@ApplicationScope
public class UsuariosService {

    private UsuariosRepository usuariosRepository;

    public UsuariosService(UsuariosRepository usuarios ) {
        this.usuariosRepository = usuarios;
    }

    //Busca un suuario en especifico y comprueba su email y nombre si existe lo devuelve , si no existe devuelve null
    public UsuariosEntity findUsuario(String inicio){

        out:
        for(UsuariosEntity u: usuariosRepository.findAll()){
            if(u.getNombre().equals(inicio) || u.getEmail().equals(inicio)){
                return u;
            }
        }

        return null;
    }

    /*-------------- para Register --------------*/
    public Boolean findByEmail(String email){

        for(UsuariosEntity u: usuariosRepository.findAll()){
            if(u.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    public Boolean findByUsername(String username){

        for(UsuariosEntity u: usuariosRepository.findAll()){
            if(u.getNombre().equals(username)){
                return true;
            }
        }
        return false;
    }

    /*-------------- para Login --------------*/
    public boolean confirmarUsuario(String inicio, String pssw){

        BCryptPasswordEncoder encriptado = new BCryptPasswordEncoder();

        out:
        for(UsuariosEntity u: usuariosRepository.findAll()){
            if(u.getNombre().equals(inicio) || u.getEmail().equals(inicio)){

                if(encriptado.matches(pssw, u.getPssw())){
                    return true;
                }
                else{
                    break out;
                }
            }
        }

        return false;
    }

    public void guardarUsuario(UsuariosEntity user) {
        BCryptPasswordEncoder encriptado = new BCryptPasswordEncoder();
        user.setPssw(encriptado.encode(user.getPssw()));
        usuariosRepository.save(user);
    }

}
