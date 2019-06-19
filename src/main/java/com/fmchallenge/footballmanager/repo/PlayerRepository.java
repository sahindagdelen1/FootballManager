package com.fmchallenge.footballmanager.repo;

import com.fmchallenge.footballmanager.entity.Player;
import com.fmchallenge.footballmanager.entity.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
     List<Player> findPlayerByTeamId(Long teamId);
     List<Player> findPlayersByContractDateBetweenAndTeam_Id(LocalDate date1,LocalDate date2,Long teamId);
}
