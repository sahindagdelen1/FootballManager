package com.fmchallenge.footballmanager.service;

import com.fmchallenge.footballmanager.entity.Player;
import com.fmchallenge.footballmanager.model.Currency;
import com.fmchallenge.footballmanager.model.PlayerTransferFee;

import java.math.BigDecimal;

public interface TransferFeeService {
    PlayerTransferFee calculateAllTransferFees(Player player, Currency currency);

    Integer calculateAgeOfPlayer(Player player);

    Integer calculateExperienceOfPlayer(Player player);

    BigDecimal calculateTransferFeeAmountOfPlayer(Player player);

    BigDecimal calculateTeamCommision(BigDecimal transferFeeAmount);

    BigDecimal calculateContractValue(BigDecimal transferFeeAmount, BigDecimal teamCommision);
}
