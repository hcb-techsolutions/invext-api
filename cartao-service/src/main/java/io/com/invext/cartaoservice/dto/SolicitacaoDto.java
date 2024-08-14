package io.com.invext.cartaoservice.dto;

import java.util.UUID;

import io.com.invext.cartaoservice.enuns.StatusSolicitacao;
import io.com.invext.cartaoservice.enuns.TipoSolicitacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitacaoDto {
	
	private UUID idSolicitacao; 
	private TipoSolicitacao tipoSolicitacao; 
	private String mensagem;
	private StatusSolicitacao statusSolicitacao; 
}
