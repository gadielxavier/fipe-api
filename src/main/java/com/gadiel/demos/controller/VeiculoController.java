package com.gadiel.demos.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gadiel.demos.client.FipeClient;
import com.gadiel.demos.model.Veiculo;
import com.gadiel.demos.repository.VeiculoRepository;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
	
	@Autowired
    FipeClient fipeClient;
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@GetMapping
	public List<Veiculo> listar () {
		return veiculoRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Veiculo adicionarVeiculo (@RequestParam("usuarioId") Long usuarioId, @RequestParam("marcaId") String marcaId, 
			@RequestParam("modeloId") String modeloId, @RequestParam("anoId") String anoId) {
		
		Map<String, String> map = fipeClient.retornaVeiculo(marcaId, modeloId, anoId);
		
		Veiculo veiculo = new Veiculo(usuarioId, map.get("Marca"), map.get("Modelo"), 
				map.get("AnoModelo"), map.get("Valor"));
		
		try {
			return veiculoRepository.save(veiculo);	
		}
		catch(DataIntegrityViolationException ex){
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, "Erro de preenchimento de dados", ex); 
		}
	}

}
