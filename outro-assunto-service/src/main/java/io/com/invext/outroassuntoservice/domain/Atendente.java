package io.com.invext.outroassuntoservice.domain;

import java.io.Serializable;

import io.com.invext.outroassuntoservice.enuns.Setor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Atendente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private Setor setor;
	private Integer qtdSolAtual;
}
