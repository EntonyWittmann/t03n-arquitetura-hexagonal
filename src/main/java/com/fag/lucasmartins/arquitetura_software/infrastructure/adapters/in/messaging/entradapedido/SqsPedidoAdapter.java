package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradapedido;

import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.PedidoServicePort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradaestoque.exceptions.ConsumerSQSException;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradapedido.dto.PedidoDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradapedido.mapper.PedidoDTOMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SqsPedidoAdapter {

    private static final Logger log = LoggerFactory.getLogger(SqsPedidoAdapter.class);

    private final PedidoServicePort pedidoServicePort;

    public SqsPedidoAdapter(PedidoServicePort pedidoServicePort) {
        this.pedidoServicePort = pedidoServicePort;
    }

    @SqsListener("${queue.order-events}")
    public void listen(PedidoDTO dto) {
        try {
            log.info("Mensagem recebida: {}", dto.getCustomerId());

            PedidoBO pedidoBO = PedidoDTOMapper.toBo(dto);
            PedidoBO pedidoCriadoBO = pedidoServicePort.criarPedido(pedidoBO);

            log.info("Mensagem consumida com sucesso para o cliente {}. Pedido criado com o ID {}",
                    dto.getCustomerId(), pedidoCriadoBO.getId());
        } catch (Exception e) {
            log.error("Erro ao consumir pedido para o cliente {}", dto.getCustomerId(), e);
            throw new ConsumerSQSException("Erro ao consumir pedido para o cliente " + dto.getCustomerId(), e);
        }
    }

}
    