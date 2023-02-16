package com.example.juan.repositories.poemas;

import com.example.juan.model.PoemasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PoemasRepository extends JpaRepository<PoemasEntity, Integer> {
    @Query("select p from PoemasEntity p where p.autor.id=?1")
    public List<PoemasEntity> mostrarMisPoemas(int id_user);
}
