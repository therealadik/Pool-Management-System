package com.example.pool.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Cansel slot DTO")
public class CancelRequest {

    @Schema(description = "Client id")
    private Long clientId;

    @Schema(description = "slot id")
    private Long orderId;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
