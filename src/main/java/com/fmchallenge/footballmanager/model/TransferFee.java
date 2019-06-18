package com.fmchallenge.footballmanager.model;

import java.math.BigDecimal;

public class TransferFee {
    private BigDecimal transferFeeAmount;
    private BigDecimal teamCommision;
    private BigDecimal contractValue;
    private Currency currency;

    public BigDecimal getTransferFeeAmount() {
        return transferFeeAmount;
    }

    public void setTransferFeeAmount(BigDecimal transferFeeAmount) {
        this.transferFeeAmount = transferFeeAmount;
    }

    public BigDecimal getTeamCommision() {
        return teamCommision;
    }

    public void setTeamCommision(BigDecimal teamCommision) {
        this.teamCommision = teamCommision;
    }

    public BigDecimal getContractValue() {
        return contractValue;
    }

    public void setContractValue(BigDecimal contractValue) {
        this.contractValue = contractValue;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
