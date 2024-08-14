package io.com.invext.cartaoservice.service;

import java.util.List;

import io.com.invext.cartaoservice.domain.Atendente;

public interface AtendenteService {
	public Atendente getAtendenteDisponivel();
	public void liberarAtendente();
	public boolean consultarSeExisteAtendenteOcupado();
	public List<Atendente> listarAtendente();
}
