package com.gadiel.demos.model;

import java.util.Calendar;

public class Carro extends Veiculo {
	
	private String diaRodizio;
	private boolean rodizioAtivo;
	
	public Carro(Long id, Long usuarioId, String marca, String modelo, String ano, String valor) {
		super(id, usuarioId, marca, modelo, ano, valor);
		this.diaRodizio = setDiaRodizio();
		this.rodizioAtivo = setRodizioAtivo();
	}

	private String setDiaRodizio() {
		String ano = super.getAno();
		if(ano.endsWith("0") || ano.endsWith("1"))
			return "segunda-feira";
		else if(ano.endsWith("2") || ano.endsWith("3"))
			return "terça-feira";
		else if(ano.endsWith("4") || ano.endsWith("5"))
			return "quarta-feira";
		else if(ano.endsWith("6") || ano.endsWith("7"))
			return "quinta-feira";
		else
			return "sexta-feira";
	}

	private boolean setRodizioAtivo() {
		// TODO Auto-generated method stub
		
		Calendar calendar = Calendar.getInstance();
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		String dia;
		
		if(dayOfWeek == 1)
			dia = "domingo";
		else if(dayOfWeek == 2)
			dia = "segunda-feira";
		else if(dayOfWeek == 3)
			dia = "terça-feira";
		else if(dayOfWeek == 4)
			dia = "quarta-feira";
		else if(dayOfWeek == 5)
			dia = "quinta-feira";
		else if(dayOfWeek == 6)
			dia = "sexta-feira";
		else
			dia = "sábado";
		
		if(dia.equals(getDiaRodizio()))
			return true;
		else
			return false;
	}

	public String getDiaRodizio() {
		return diaRodizio;
	}

	public boolean isRodizioAtivo() {
		return rodizioAtivo;
	}

}
