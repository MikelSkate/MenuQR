package com.MikelCooper.menuqr.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.MikelCooper.menuqr.model.Mesa;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long>{
    Optional<Mesa> findByNumero(int numero);
}
