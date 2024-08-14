package io.com.invext.outroassuntoservice.service;

import java.util.List;

import io.com.invext.outroassuntoservice.domain.Atendente;

public interface AtendenteService {
	public Atendente getAtendenteDisponivel();
	public void liberarAtendente();
	public boolean consultarSeExisteAtendenteOcupado();
	public List<Atendente> listarAtendente();
}
