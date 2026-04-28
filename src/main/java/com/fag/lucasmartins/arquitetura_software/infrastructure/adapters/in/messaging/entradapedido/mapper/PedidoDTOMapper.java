package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradapedido.mapper;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoProdutoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.ProdutoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradapedido.dto.PedidoDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradapedido.dto.PedidoItemDTO;

import java.util.ArrayList;
import java.util.List;

public class PedidoDTOMapper {

    public static PedidoBO toBo(PedidoDTO pedidoDTO) {
        PedidoBO bo = new PedidoBO();
        bo.setCep(pedidoDTO.getZipCode());
        bo.setPessoa(mapearPessoa(pedidoDTO.getCustomerId()));
        bo.setItens(mapearItens(pedidoDTO.getOrderItems()));
        return bo;
    }

    private static PessoaBO mapearPessoa(Integer customerId) {
        PessoaBO pessoaBO = new PessoaBO();
        pessoaBO.setId(customerId);
        return pessoaBO;
    }

    private static List<PedidoProdutoBO> mapearItens(List<PedidoItemDTO> orderItems) {
        List<PedidoProdutoBO> itens = new ArrayList<>();
        for (PedidoItemDTO item : orderItems) {
            PedidoProdutoBO pedidoProdutoBO = new PedidoProdutoBO();

            ProdutoBO produtoBO = new ProdutoBO();
            produtoBO.setId(item.getSku());
            pedidoProdutoBO.setProduto(produtoBO);
            pedidoProdutoBO.setQuantidade(item.getAmount());
            itens.add(pedidoProdutoBO);
        }
        return itens;
    }

}