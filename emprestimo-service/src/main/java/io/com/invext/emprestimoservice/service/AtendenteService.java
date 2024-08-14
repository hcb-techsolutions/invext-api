package io.com.invext.emprestimoservice.service;

import java.util.List;

import io.com.invext.emprestimoservice.domain.Atendente;

public interface AtendenteService {
	public Atendente getAtendenteDisponivel();
	public void liberarAtendente();
	public boolean consultarSeExisteAtendenteOcupado();
	public List<Atendente> listarAtendente();
}
