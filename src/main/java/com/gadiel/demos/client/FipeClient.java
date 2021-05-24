package com.gadiel.demos.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "fipe", url = "https://parallelum.com.br/fipe/api/v1/carros")
public interface FipeClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "/marcas/{marcaId}/modelos/{modeloId}/anos/{anoId}")
	Map<String, String> retornaVeiculo(@PathVariable("marcaId") String marcaId, 
			@PathVariable("modeloId") String modeloId, @PathVariable("anoId") String anoId);

}
