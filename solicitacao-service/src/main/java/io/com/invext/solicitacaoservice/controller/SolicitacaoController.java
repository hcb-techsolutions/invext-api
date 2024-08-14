package io.com.invext.solicitacaoservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.com.invext.solicitacaoservice.controller.dto.SolicitacaoDto;
import io.com.invext.solicitacaoservice.service.SolicitacaoService;

@RestController
@RequestMapping("/api/v1/solicitacao")
public class SolicitacaoController {

	private SolicitacaoService solicitacaoService;
	
	public SolicitacaoController(SolicitacaoService solicitacaoService) {
		super();
		this.solicitacaoService = solicitacaoService;
	}

	@PostMapping
	public ResponseEntity<SolicitacaoDto> criarSolicitacao(@RequestBody SolicitacaoDto solicitacaoDto) {
		return ResponseEntity.ok(solicitacaoService.alocarSolicitacao(solicitacaoDto));
	}
}
