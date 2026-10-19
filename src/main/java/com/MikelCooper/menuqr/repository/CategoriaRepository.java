package com.MikelCooper.menuqr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.MikelCooper.menuqr.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    Optional<Categoria> findByNombre(String nombre);
}


