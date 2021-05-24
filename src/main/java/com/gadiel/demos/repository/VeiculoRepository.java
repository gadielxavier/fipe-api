package com.gadiel.demos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gadiel.demos.model.Veiculo;

@Repository 
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
	
	List<Veiculo> findAllByUsuarioId(Long userId);

}
