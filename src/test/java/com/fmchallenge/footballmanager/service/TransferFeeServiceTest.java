package com.fmchallenge.footballmanager.service;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import com.fmchallenge.footballmanager.entity.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;


@RunWith(SpringJUnit4ClassRunner.class)
public class TransferFeeServiceTest {

    TransferFeeService transferFeeService;

    @Before
    public void init() {
        transferFeeService = new TransferFeeServiceImpl();
    }


    @Test
    public void testCalculateTransferFee() {
        Player player = new Player();
        player.setId(1L);
        player.setName("Zlatan");
        player.setSurname("Ibrahimovic");
        player.setBirthDate(LocalDate.of(1982, Month.NOVEMBER, 21));
        player.setContractDate(LocalDate.of(2018, Month.MAY, 11));


        Integer age = transferFeeService.calculateAgeOfPlayer(player);
        Integer calculatedAge = Period.between(player.getBirthDate(), LocalDate.now()).getYears();
        assertEquals(calculatedAge, age);

        Integer experienceInMonths = transferFeeService.calculateExperienceOfPlayer(player);
        Integer experienceInMonthsExpected = Period.between(player.getContractDate(), LocalDate.now()).getMonths();
        assertEquals(experienceInMonthsExpected, experienceInMonths);

        BigDecimal contractValue = transferFeeService.calculateContractValue(new BigDecimal(55), new BigDecimal(11));
        assertEquals(new BigDecimal(66), contractValue);

        BigDecimal teamCommision = transferFeeService.calculateTeamCommision(new BigDecimal(55));
        assertEquals(new BigDecimal(5.5), teamCommision);

        BigDecimal transferFeeAmount = transferFeeService.calculateTransferFeeAmountOfPlayer(player);
        assertEquals(new BigDecimal(27), transferFeeAmount);
    }
}
