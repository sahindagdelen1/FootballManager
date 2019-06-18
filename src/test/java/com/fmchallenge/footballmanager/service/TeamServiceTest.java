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

        Optional<Team> teamFromService = teamService.get("1");
        assertNotNull(teamFromService.get());
        assertEquals(Long.valueOf(1), teamFromService.get().getId());
        assertEquals("Manchester United", team.getName());
    }


    @Test
    public void testTeamGetFail() {
        when(teamRepository.findById(Long.valueOf(2))).thenReturn(Optional.empty());
        Optional<Team> teamFromService = teamService.get("2");
        assertFalse(teamFromService.isPresent());
    }


    @Test
    public void testTeamGetAllSuccess() {
        List<Team> teamList = new ArrayList<>();
        Team team = new Team();
        team.setId(1L);
        team.setName("Manchester United");
        List<Player> players = new ArrayList<>();
        team.setPlayers(players);
        teamList.add(team);
        team = new Team();
        team.setId(2L);
        team.setName("Fenerbahce");
        teamList.add(team);

        when(teamRepository.findAll()).thenReturn(teamList);

        List<Team> teams = teamService.getAll();
        assertNotNull(teams);
        assertEquals(2, teams.size());
    }


    @Test
    public void testTeamGetAllFail() {
        List<Team> teamList = new ArrayList<>();
        when(teamRepository.findAll()).thenReturn(teamList);

        List<Team> teams = teamService.getAll();
        assertEquals(0, teams.size());
    }


    @Test
    public void testTeamInsertSuccess() {
        Team team = new Team();
        team.setName("Inter");
        team.setPlayers(new ArrayList<>());

        Team saved = new Team();
        saved.setId(99L);
        saved.setName("Inter");
        saved.setPlayers(new ArrayList<>());

        when(teamRepository.save(team)).thenReturn(saved);

        Optional<Team> savedTeam = teamService.insert(team);
        assertNotNull(savedTeam.get());
        assertTrue(savedTeam.isPresent());
        assertEquals("Inter", savedTeam.get().getName());
        assertNotNull(savedTeam.get().getId());
        assertEquals("99", savedTeam.get().getId().toString());
    }


    @Test
    public void testTeamInsertFail() {
        Team team = new Team();
        team.setName("Inter");
        team.setPlayers(new ArrayList<>());

        when(teamRepository.save(team)).thenReturn(null);

        Optional<Team> savedTeam = teamService.insert(team);
        assertFalse(savedTeam.isPresent());
    }


    @Test
    public void testTeamUpdateSuccess() {
        Team team = new Team();
        team.setId(88L);
        team.setName("Inter");
        team.setPlayers(new ArrayList<>());

        Team saved = new Team();
        saved.setId(88L);
        saved.setName("Inter");
        saved.setPlayers(new ArrayList<>());

        when(teamRepository.findById(88L)).thenReturn(Optional.of(team));
        when(teamRepository.save(team)).thenReturn(saved);


        Optional<Team> savedTeam = teamService.update(team);
        assertNotNull(savedTeam.get());
        assertTrue(savedTeam.isPresent());
        assertEquals("Inter", savedTeam.get().getName());
        assertNotNull(savedTeam.get().getId());
        assertEquals("88", savedTeam.get().getId().toString());
    }


    @Test
    public void testTeamUpdateFail() {
        Team team = new Team();
        team.setId(77L);
        team.setName("Inter Milan");
        team.setPlayers(new ArrayList<>());

        Team saved = new Team();
        saved.setId(77L);
        saved.setName("Inter");
        saved.setPlayers(new ArrayList<>());

        when(teamRepository.findById(77L)).thenReturn(Optional.empty());
        when(teamRepository.save(team)).thenReturn(null);


        Optional<Team> savedTeam = teamService.update(team);
        assertEquals(Optional.empty(), savedTeam);
        assertFalse(savedTeam.isPresent());
    }

    @Test
    public void testTeamDeleteSuccess() {
        Team team = new Team();
        team.setId(66L);
        team.setName("Inter Milan");
        team.setPlayers(new ArrayList<>());

        when(teamRepository.findById(66L)).thenReturn(Optional.empty());
        teamService.delete("66");
        assertEquals(Optional.empty(), teamService.get("66"));
    }

    @Test
    public void testTeamDeleteFail() {
        Team team = new Team();
        team.setId(66L);
        team.setName("Inter Milan");
        team.setPlayers(new ArrayList<>());

        when(teamRepository.findById(66L)).thenReturn(Optional.of(team));
        teamService.delete("66");
        assertEquals(Optional.of(team), teamService.get("66"));
    }

}
