package io.com.invext.cartaoservice.listener;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

import io.com.invext.cartaoservice.domain.Atendente;
import io.com.invext.cartaoservice.dto.SolicitacaoDto;
import io.com.invext.cartaoservice.service.AtendenteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Component
public class RabbitMqListener {

	private final AtendenteService service;

	@RabbitListener(queues = "${app.rabbitmq.cartao.queue}", 
					containerFactory = "simpleRabbitListenerContainerFactory")
	public void listen(SolicitacaoDto solicitacaoDto, Channel channel, 
			Message amqpMessage) throws InterruptedException, IOException {
		long deliveryTag = amqpMessage.getMessageProperties().getDeliveryTag();
		Atendente atendente = service.getAtendenteDisponivel();
        
		if (atendente == null) {
            log.warn("Sem atendente disponivel para solicitação de cartão: {}! Favor aguarde na fila de espera"); 
            channel.basicReject(deliveryTag, true);
            
            // Aguarde 15s para verificar se tem atendente disponivel.
            Thread.sleep(15000);

            return;
        }

        log.info("Solicitação de Cartão recebida: {}. Está sendo atendido (a) por: {}", 
        		solicitacaoDto.getIdSolicitacao(), atendente.getNome());
        atendente.setQtdSolAtual(atendente.getQtdSolAtual() + 1);
        channel.basicAck(deliveryTag, true);
    }

	// Os atendentes finaliza os atendimentos a cada 120s
    @Scheduled(fixedDelay = 120000)
    public void liberarAtendente() throws IOException {
    	if (service.consultarSeExisteAtendenteOcupado()) {
    		log.info("Atendentes finalizando atendimento de Cartões...");
    		service.liberarAtendente();
		}
    }
}
