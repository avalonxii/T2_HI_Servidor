package com.example.juan.repositories.poemas;

import com.example.juan.model.PoemasEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.ArrayList;
import java.util.List;

@Service
@ApplicationScope
public class PoemasService {

    private PoemasRepository poemasRepository;
    private List<PoemasEntity> listaPoemas;

    public PoemasService(PoemasRepository recomendaciones) {
        this.poemasRepository = recomendaciones;
        this.listaPoemas = new ArrayList<>();
    }

    public List<PoemasEntity> mostrarPoemas(){

        listaPoemas.clear();
        this.listaPoemas.addAll(poemasRepository.findAll());

        return this.listaPoemas;
    }

    //guardar poema
    public void guardarPoema(PoemasEntity poema){
        poemasRepository.save(poema);
    }

    //eliminar poema
    public void eliminarPoema(int id){
        poemasRepository.deleteById(id);
    }

    //actualziar poema
    public void actualizarPoema(PoemasEntity poema){
        poemasRepository.save(poema);
    }

}
