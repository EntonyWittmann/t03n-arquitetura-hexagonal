package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradapedido.dto;

public class PedidoItemDTO {

    private Integer sku;
    private Integer amount;

    public PedidoItemDTO() {
    }

    public PedidoItemDTO(Integer sku, Integer amount) {
        this.sku = sku;
        this.amount = amount;
    }

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
        this.sku = sku;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}