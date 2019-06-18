package com.fmchallenge.footballmanager.service;

import com.fmchallenge.footballmanager.entity.Player;
import com.fmchallenge.footballmanager.model.Currency;
import com.fmchallenge.footballmanager.model.PlayerTransferFee;
import com.fmchallenge.footballmanager.model.TransferFee;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;


@Service
public class TransferFeeServiceImpl implements TransferFeeService {
    Integer TRANSFER_CONSTANT = 1000;

    @Override
    public PlayerTransferFee calculateAllTransferFees(Player player, Currency currency) {
        PlayerTransferFee playerTransferFee = new PlayerTransferFee();
        playerTransferFee.setPlayer(player);
        TransferFee transferFee = new TransferFee();
        playerTransferFee.setTransferFee(transferFee);
        transferFee.setCurrency(currency);

        BigDecimal transferFeeAmount = calculateTransferFeeAmountOfPlayer(player);
        transferFee.setTransferFeeAmount(transferFeeAmount);
        BigDecimal teamCommision = calculateTeamCommision(transferFeeAmount);
        transferFee.setTeamCommision(teamCommision);
        BigDecimal contractValue = calculateContractValue(transferFeeAmount, teamCommision);
        transferFee.setContractValue(contractValue);
        return playerTransferFee;
    }

    @Override
    public Integer calculateAgeOfPlayer(Player player) {
        return Period.between(player.getBirthDate(), LocalDate.now()).getYears();
    }


    @Override
    public Integer calculateExperienceOfPlayer(Player player) {
        return Period.between(player.getContractDate(), LocalDate.now()).getMonths();
    }

    @Override
    public BigDecimal calculateTransferFeeAmountOfPlayer(Player player) {
        Integer transferFeeInt = (calculateExperienceOfPlayer(player) * TRANSFER_CONSTANT) / calculateAgeOfPlayer(player);
        return BigDecimal.valueOf(transferFeeInt);
    }


    @Override
    public BigDecimal calculateTeamCommision(BigDecimal transferFeeAmount) {
        return transferFeeAmount.multiply(new BigDecimal(10)).divide(new BigDecimal(100));
    }

    @Override
    public BigDecimal calculateContractValue(BigDecimal transferFeeAmount, BigDecimal teamCommision) {
        return transferFeeAmount.add(teamCommision);
    }
}
