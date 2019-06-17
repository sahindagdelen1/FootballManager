package com.fmchallenge.footballmanager.service;


import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.fmchallenge.footballmanager.entity.Player;
import com.fmchallenge.footballmanager.entity.Team;
import com.fmchallenge.footballmanager.repo.TeamRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
public class TeamServiceTest {

    @MockBean
    TeamRepository teamRepository;


    @InjectMocks
    private TeamServiceImpl teamService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testTeamGetSuccess() {
        Team team = new Team();
        List<Player> players = new ArrayList<>();
        team.setId(1L);
        team.setName("Manchester United");
        team.setPlayers(players);

        when(teamRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(team));

        Optional<Team> teamFromService = teamService.get(Long.valueOf(1));
        assertNotNull(teamFromService.get());
        assertEquals(Long.valueOf(1), teamFromService.get().getId());
        assertEquals("Manchester United", team.getName());
    }


    @Test
    public void testTeamGetFail() {
        when(teamRepository.findById(Long.valueOf(2))).thenReturn(Optional.empty());
        Optional<Team> teamFromService = teamService.get(Long.valueOf(2));
        assertFalse(teamFromService.isPresent());
    }

}
