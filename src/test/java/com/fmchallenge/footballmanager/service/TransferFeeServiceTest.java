package com.fmchallenge.footballmanager.service;

import com.fmchallenge.footballmanager.entity.Player;
import com.fmchallenge.footballmanager.repo.PlayerRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class TransferFeeServiceTest {

    @MockBean
    PlayerRepository playerRepository;

    @InjectMocks
    TransferFeeServiceImpl transferFeeService;

    Integer TRANSFER_CONSTANT = 1000;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    public void testCalculateTransferFee() {
        Player player = new Player();
        player.setId(1L);
        player.setName("Zlatan");
        player.setSurname("Ibrahimovic");
        player.setBirthDate(LocalDate.of(1982, Month.NOVEMBER, 21));
        player.setContractDate(LocalDate.of(2019, Month.MAY, 11));

    }
}
