package io.com.invext.outroassuntoservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import io.com.invext.outroassuntoservice.domain.Atendente;
import io.com.invext.outroassuntoservice.enuns.Setor;
import io.com.invext.outroassuntoservice.service.AtendenteService;

@Service
public class AtendenteServiceImpl implements AtendenteService {

	private List<Atendente> atendentes = new ArrayList<>();
	
	public AtendenteServiceImpl() {
		atendentes.add(new Atendente("Abner", Setor.OUTRO_ASSUNTO, 0));
		atendentes.add(new Atendente("Ada", Setor.OUTRO_ASSUNTO, 0));
	}
	
	@Override
	public Atendente getAtendenteDisponivel() {
		return atendentes.stream()
				.filter(atendente -> atendente.getQtdSolAtual() < 3)
				.findFirst()
				.orElse(null);
	}

	@Override
	public void liberarAtendente() {
		atendentes.stream()
			.filter(atendente -> atendente.getQtdSolAtual() > 0)
			.forEach( atendente -> atendente.setQtdSolAtual(0));
	}
	
	@Override
	public boolean consultarSeExisteAtendenteOcupado() {
		return atendentes.stream()
			.anyMatch(atendente -> atendente.getQtdSolAtual() > 0);
	}

	@Override
	public List<Atendente> listarAtendente() {
		return atendentes;
	}
}
