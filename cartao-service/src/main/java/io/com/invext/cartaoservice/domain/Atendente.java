package io.com.invext.cartaoservice.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import io.com.invext.cartaoservice.dto.SolicitacaoDto;
import io.com.invext.cartaoservice.enuns.Setor;
import io.com.invext.cartaoservice.enuns.StatusSolicitacao;
import io.com.invext.cartaoservice.enuns.TipoSolicitacao;
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
