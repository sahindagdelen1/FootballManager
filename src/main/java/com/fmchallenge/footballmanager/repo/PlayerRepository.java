package com.fmchallenge.footballmanager.repo;

import com.fmchallenge.footballmanager.entity.Player;
import com.fmchallenge.footballmanager.entity.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
    List<Player> findPlayerByTeamId(Long teamId);

    @Query("select p from Player where p.contract")
    List<Player> findPlayersByTeamAndContractDate(Integer year, Long teamId);
}
