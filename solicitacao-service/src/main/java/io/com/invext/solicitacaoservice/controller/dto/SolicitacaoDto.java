package io.com.invext.solicitacaoservice.controller.dto;

import java.util.UUID;

import io.com.invext.solicitacaoservice.controller.enums.StatusSolicitacao;
import io.com.invext.solicitacaoservice.controller.enums.TipoSolicitacao;

public record SolicitacaoDto(
		UUID idSolicitacao, 
		TipoSolicitacao tipoSolicitacao, 
		String mensagem,
		StatusSolicitacao statusSolicitacao) {}
