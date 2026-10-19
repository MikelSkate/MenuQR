package com.MikelCooper.menuqr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.MikelCooper.menuqr.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{}
