package com.gadiel.demos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gadiel.demos.model.Carro;
import com.gadiel.demos.model.Usuario;
import com.gadiel.demos.model.Veiculo;
import com.gadiel.demos.repository.UsuarioRepository;
import com.gadiel.demos.repository.VeiculoRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@GetMapping
	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getUser(@PathVariable Long id) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		if(usuario.equals(Optional.empty()) ) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "We were unable to find the specified resource.");
		}
		
		List<Veiculo> veiculosUsuario = veiculoRepository.findAllByUsuarioId(id);
		List<Carro> carros = new ArrayList<Carro>();
		
		for(Veiculo veiculo: veiculosUsuario) {
			Carro carro = new Carro(veiculo.getId(), veiculo.getUsuarioId(), veiculo.getMarca(),
					veiculo.getModelo(), veiculo.getAno(), veiculo.getValor());
			carros.add(carro);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("usuario", usuario);
		map.put("carros", carros);
		
		return ResponseEntity.status(HttpStatus.OK).body(map);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario adicionarUsuario (@Valid @RequestBody Usuario usuario, 
			BindingResult bindingResult){
		
		if (bindingResult.hasErrors()) { 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Erro de preenchimento de dados");
		}
		try {
			return usuarioRepository.save(usuario);	
		}
		catch(DataIntegrityViolationException ex){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Erro de preenchimento de dados", ex); 
		}
	}

}
