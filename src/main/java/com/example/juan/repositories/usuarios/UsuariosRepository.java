package com.example.juan.repositories.usuarios;

import com.example.juan.model.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<UsuariosEntity, Integer> {
}
