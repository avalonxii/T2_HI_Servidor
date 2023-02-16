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

    public List<PoemasEntity> mostrarRecomendaciones(){

        listaPoemas.clear();
        for (PoemasEntity re: poemasRepository.findAll()) {
            this.listaPoemas.add(re);

        }

        return this.listaPoemas;
    }

}
