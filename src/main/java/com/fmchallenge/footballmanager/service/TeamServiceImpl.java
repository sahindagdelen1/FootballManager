package com.fmchallenge.footballmanager.service;

import com.fmchallenge.footballmanager.entity.Team;
import com.fmchallenge.footballmanager.repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Override
    public Optional<Team> get(Long id) {
        return teamRepository.findById(id);
    }

    @Override
    public Optional<Team> upsert(Team team) {
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
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }
}