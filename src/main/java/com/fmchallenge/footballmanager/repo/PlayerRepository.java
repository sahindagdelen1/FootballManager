package com.fmchallenge.footballmanager.repo;

import com.fmchallenge.footballmanager.entity.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
}
