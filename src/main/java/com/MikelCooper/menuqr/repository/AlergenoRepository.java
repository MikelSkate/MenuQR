package com.MikelCooper.menuqr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.MikelCooper.menuqr.model.Alergeno;

@Repository
public interface AlergenoRepository extends JpaRepository<Alergeno, Long>{
    Alergeno findByNombre(String nombre);
}
