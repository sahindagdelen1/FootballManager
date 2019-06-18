package com.fmchallenge.footballmanager.service;

import com.fmchallenge.footballmanager.entity.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    List<Team> getAll();

    Optional<Team> get(String id);

    Optional<Team> insert(Team team);

    Optional<Team> update(Team team);

    void delete(String id);
}
