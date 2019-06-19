package com.fmchallenge.footballmanager.service;

import com.fmchallenge.footballmanager.entity.Player;
import com.fmchallenge.footballmanager.entity.Team;
import com.fmchallenge.footballmanager.repo.PlayerRepository;
import com.fmchallenge.footballmanager.repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public List<Player> getAll() {
        Iterable<Player> playersIterable = playerRepository.findAll();
        List<Player> playerList = new ArrayList<>();
        if (playersIterable == null) {
            return playerList;
        }
        playersIterable.forEach(playerList::add);
        return playerList;
    }

    @Override
    public List<Player> getPlayersByTeamId(String teamId) {
        Iterable<Player> playersIterable = playerRepository.findPlayerByTeamId(Long.valueOf(teamId));
        List<Player> playerList = new ArrayList<>();
        if (playersIterable == null) {
            return playerList;
        }
        playersIterable.forEach(playerList::add);
        return playerList;
    }

    @Override
    public List<Player> getPlayersByContractDateBetweenAndTeamId(String year, String teamId) {
        LocalDate startDate = LocalDate.of(Integer.valueOf(year), Month.JANUARY, 1);
        LocalDate endDate = LocalDate.of((Integer.valueOf(year) + 1), Month.JANUARY, 1);
        return playerRepository.findPlayersByContractDateBetweenAndTeam_Id(startDate, endDate, Long.valueOf(teamId));
    }

    @Override
    public Optional<Player> get(String id) {
        return playerRepository.findById(Long.valueOf(id));
    }

    @Override
    public Optional<Player> update(Player player) {
        Optional<Player> existedPlayer = playerRepository.findById(player.getId());
        if (!existedPlayer.isPresent()) {
            return Optional.empty();
        }

        Player existedPlayerVal = existedPlayer.get();
        existedPlayerVal.setName(player.getName());
        existedPlayerVal.setSurname(player.getSurname());
        existedPlayerVal.setBirthDate(player.getBirthDate());
        existedPlayerVal.setContractDate(player.getContractDate());
        existedPlayerVal.setTeam(player.getTeam());

        Player savedPlayer = playerRepository.save(existedPlayerVal);
        return Optional.of(savedPlayer);
    }

    @Override
    public Optional<Player> insert(Player player) {
        Player savedPlayer = playerRepository.save(player);
        if (savedPlayer == null) {
            return Optional.empty();
        }
        return Optional.of(savedPlayer);
    }

    @Override
    public void delete(String id) {
        playerRepository.deleteById(Long.valueOf(id));
    }
}