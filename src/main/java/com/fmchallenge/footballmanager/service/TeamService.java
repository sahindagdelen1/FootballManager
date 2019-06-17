package com.fmchallenge.footballmanager.service;

import com.fmchallenge.footballmanager.entity.Team;

import java.util.Optional;

public interface TeamService {
    Optional<Team> get(Long id);

    Optional<Team> upsert(Team team);

    void delete(Long id);
}
