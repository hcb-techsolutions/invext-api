package io.com.invext.solicitacaoservice.service.impl;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.com.invext.solicitacaoservice.controller.dto.SolicitacaoDto;
import io.com.invext.solicitacaoservice.controller.enums.StatusSolicitacao;
import io.com.invext.solicitacaoservice.controller.enums.TipoSolicitacao;
import io.com.invext.solicitacaoservice.service.SolicitacaoService;

@Service
public class SolicitacaoServiceImpl implements SolicitacaoService {

	private RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.cartao.exchange}")
    private String cartaoExchange;

    @Value("${app.rabbitmq.cartao.routing-key}")
    private String cartaoRoutingKey;

    @Value("${app.rabbitmq.emprestimo.exchange}")
    private String emprestimoExchange;

    @Value("${app.rabbitmq.emprestimo.routing-key}")
    private String emprestimoRoutingKey;

    @Value("${app.rabbitmq.outro.exchange}")
    private String outroExchange;

    @Value("${app.rabbitmq.outro.routing-key}")
    private String outroRoutingKey;

    public SolicitacaoServiceImpl(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}
    
	@Override
	public SolicitacaoDto alocarSolicitacao(SolicitacaoDto solicitacaoDto) {
		var solicitacao = new SolicitacaoDto( 
							UUID.randomUUID(), 
							solicitacaoDto.tipoSolicitacao(), 
							solicitacaoDto.mensagem(), 
							StatusSolicitacao.CRIADA);
		if (solicitacao.tipoSolicitacao() == null) {
			var outroAssunto  = new SolicitacaoDto( 
								solicitacao.idSolicitacao(), 
								TipoSolicitacao.OUTRO, 
								solicitacao.mensagem(), 
								StatusSolicitacao.CRIADA);
			notificarTimeOutrosAssuntos(solicitacao);
			
			return outroAssunto;
		} else
			switch (solicitacao.tipoSolicitacao()) {
				case CARTAO -> notificarTimeCartoes(solicitacao);
		
				case EMPRESTIMO -> notificarTimeEmprestimos(solicitacao);
		
				case OUTRO -> notificarTimeOutrosAssuntos(solicitacao);
			}
		
		return solicitacao;
	}

    private void notificarTimeCartoes(SolicitacaoDto solicitacaoDto) {
        rabbitTemplate.convertAndSend(	cartaoExchange, 
        								cartaoRoutingKey, 
        								solicitacaoDto);
    }

    private void notificarTimeEmprestimos(SolicitacaoDto solicitacaoDto) {
        rabbitTemplate.convertAndSend(  emprestimoExchange, 
        								emprestimoRoutingKey, 
        								solicitacaoDto);
    }

    private void notificarTimeOutrosAssuntos(SolicitacaoDto solicitacaoDto) {
        rabbitTemplate.convertAndSend(  outroExchange, 
        								outroRoutingKey, 
        								solicitacaoDto);
    }

}
