package com.fmchallenge.footballmanager.service;

import com.fmchallenge.footballmanager.entity.Player;
import com.fmchallenge.footballmanager.repo.PlayerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class PlayerServiceTest {

    @MockBean
    PlayerRepository playerRepository;

    @InjectMocks
    PlayerServiceImpl playerService;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testPlayerGetSuccess() {
        Player player = new Player();
        player.setId(1L);
        player.setName("Zlatan");
        player.setSurname("Ibrahimovic");
        player.setBirthDate(LocalDate.of(1982, Month.NOVEMBER, 21));
        player.setContractDate(LocalDate.of(2019, Month.MAY, 11));

        when(playerRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(player));

        Optional<Player> playerFromService = playerService.get("1");
        assertNotNull(playerFromService.get());
        assertEquals(Long.valueOf(1), playerFromService.get().getId());
        assertEquals("Zlatan", playerFromService.get().getName());
    }

    @Test
    public void testPlayerGetFail() {
        when(playerRepository.findById(Long.valueOf(2))).thenReturn(Optional.empty());
        Optional<Player> playerFromService = playerService.get("2");
        assertFalse(playerFromService.isPresent());
    }


    @Test
    public void testPlayerGetAllSuccess() {
        List<Player> playerList = new ArrayList<>();
        Player player = new Player();
        player.setId(1L);
        player.setName("Zlatan");
        player.setSurname("Ibrahimovic");
        player.setBirthDate(LocalDate.of(1982, Month.NOVEMBER, 21));
        player.setContractDate(LocalDate.of(2019, Month.MAY, 11));
        playerList.add(player);
        player = new Player();
        player.setId(2L);
        player.setName("Thierry");
        player.setSurname("Henry");
        player.setBirthDate(LocalDate.of(1985, Month.JULY, 14));
        player.setContractDate(LocalDate.of(2019, Month.JUNE, 21));
        playerList.add(player);

        when(playerRepository.findAll()).thenReturn(playerList);

        List<Player> players = playerService.getAll();
        assertNotNull(players);
        assertEquals(2, players.size());
    }

    @Test
    public void testPlayerGetAllFail() {
        List<Player> playerList = new ArrayList<>();
        when(playerRepository.findAll()).thenReturn(playerList);

        List<Player> teams = playerService.getAll();
        assertEquals(0, teams.size());
    }


    @Test
    public void testPlayerInsertSuccess() {
        Player player = new Player();
        player.setName("Zlatan");
        player.setSurname("Ibrahimovic");
        player.setBirthDate(LocalDate.of(1982, Month.NOVEMBER, 21));
        player.setContractDate(LocalDate.of(2019, Month.MAY, 11));

        Player savedPlayer = new Player();
        savedPlayer.setId(221L);
        savedPlayer.setName("Zlatan");
        savedPlayer.setSurname("Ibrahimovic");
        savedPlayer.setBirthDate(LocalDate.of(1982, Month.NOVEMBER, 21));
        savedPlayer.setContractDate(LocalDate.of(2019, Month.MAY, 11));


        when(playerRepository.save(player)).thenReturn(savedPlayer);

        Optional<Player> saved = playerService.insert(player);
        assertNotNull(saved.get());
        assertTrue(saved.isPresent());
        assertEquals("Zlatan", saved.get().getName());
        assertEquals("Ibrahimovic", saved.get().getSurname());
        assertNotNull(saved.get().getId());
        assertEquals("221", saved.get().getId().toString());
    }

    @Test
    public void testPlayerInsertFail() {
        Player player = new Player();
        player.setName("Zlatan");
        player.setSurname("Ibrahimovic");
        player.setBirthDate(LocalDate.of(1982, Month.NOVEMBER, 21));
        player.setContractDate(LocalDate.of(2019, Month.MAY, 11));

        when(playerRepository.save(player)).thenReturn(null);

        Optional<Player> savedPlayer = playerService.insert(player);
        assertFalse(savedPlayer.isPresent());
    }

    @Test
    public void testPlayerUpdateSuccess() {
        Player player = new Player();
        player.setId(221L);
        player.setName("Zlatan");
        player.setSurname("Ibrahimovic");
        player.setBirthDate(LocalDate.of(1982, Month.NOVEMBER, 21));
        player.setContractDate(LocalDate.of(2019, Month.MAY, 11));

        Player savedPlayer = new Player();
        savedPlayer.setId(221L);
        savedPlayer.setName("Robert");
        savedPlayer.setSurname("Pires");
        savedPlayer.setBirthDate(LocalDate.of(1982, Month.NOVEMBER, 21));
        savedPlayer.setContractDate(LocalDate.of(2019, Month.MAY, 11));

        when(playerRepository.findById(221L)).thenReturn(Optional.of(player));
        when(playerRepository.save(player)).thenReturn(savedPlayer);


        Optional<Player> updatedPlayer = playerService.update(player);
        assertNotNull(updatedPlayer.get());
        assertTrue(updatedPlayer.isPresent());
        assertEquals("Robert", updatedPlayer.get().getName());
        assertNotNull(updatedPlayer.get().getId());
        assertEquals("221", updatedPlayer.get().getId().toString());
    }

    @Test
    public void testPlayerUpdateFail() {
        Player player = new Player();
        player.setId(221L);
        player.setName("Zlatan");
        player.setSurname("Ibrahimovic");
        player.setBirthDate(LocalDate.of(1982, Month.NOVEMBER, 21));
        player.setContractDate(LocalDate.of(2019, Month.MAY, 11));

        when(playerRepository.findById(221L)).thenReturn(Optional.empty());
        when(playerRepository.save(player)).thenReturn(null);


        Optional<Player> savedPlayer = playerService.update(player);
        assertEquals(Optional.empty(), savedPlayer);
        assertFalse(savedPlayer.isPresent());
    }


    @Test
    public void testPlayerDeleteSuccess() {
        when(playerService.get("66")).thenReturn(Optional.empty());
        playerService.delete("66");
        assertEquals(Optional.empty(), playerService.get("66"));
    }

    @Test
    public void testPlayerDeleteFail() {
        Player player = new Player();
        player.setId(221L);
        player.setName("Robert");
        player.setSurname("Pires");
        player.setBirthDate(LocalDate.of(1982, Month.NOVEMBER, 21));
        player.setContractDate(LocalDate.of(2019, Month.MAY, 11));

        when(playerRepository.findById(221L)).thenReturn(Optional.of(player));
        playerService.delete("221");
        assertEquals(Optional.of(player), playerService.get("221"));
    }

}
