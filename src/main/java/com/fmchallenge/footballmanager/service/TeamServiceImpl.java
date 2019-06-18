package com.fmchallenge.footballmanager.service;

import com.fmchallenge.footballmanager.entity.Team;
import com.fmchallenge.footballmanager.repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Override
    public List<Team> getAll() {
        Iterable<Team> teamsIterable = teamRepository.findAll();
        List<Team> teamList = new ArrayList<>();
        if (teamsIterable == null) {
            return teamList;
        }
        teamsIterable.forEach(teamList::add);
        return teamList;
    }

    @Override
    public Optional<Team> get(String id) {
        return teamRepository.findById(Long.valueOf(id));
    }

    @Override
    public Optional<Team> update(Team team) {
        Optional<Team> existedTeam = teamRepository.findById(team.getId());
        if (!existedTeam.isPresent()) {
            return Optional.empty();
        }

        Team existedTeamVal = existedTeam.get();
        existedTeamVal.setName(team.getName());
        existedTeamVal.setPlayers(team.getPlayers());
        Team savedTeam = teamRepository.save(existedTeamVal);
        return Optional.of(savedTeam);
    }

    @Override
    public Optional<Team> insert(Team team) {
        Team savedTeam = teamRepository.save(team);
        if (savedTeam == null) {
            return Optional.empty();
        }
        return Optional.of(savedTeam);
    }

    @Override
    public void delete(String id) {
        teamRepository.deleteById(Long.valueOf(id));
    }
}