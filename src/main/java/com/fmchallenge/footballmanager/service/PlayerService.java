package com.fmchallenge.footballmanager.service;

import com.fmchallenge.footballmanager.entity.Player;
import com.fmchallenge.footballmanager.entity.Team;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    List<Player> getAll();

    List<Player> getPlayersByTeamId(String teamId);

    Optional<Player> get(String id);

    Optional<Player> insert(Player player);

    Optional<Player> update(Player player);

    void delete(String id);
}
