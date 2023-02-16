package com.example.juan.repositories.poemas;

import com.example.juan.model.PoemasEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

@Service
@ApplicationScope
public class PoemasService {

    private PoemasRepository poemasRepository;
    private List<PoemasEntity> listaPoemas;



}
