package com.fmchallenge.footballmanager.repo;

import com.fmchallenge.footballmanager.entity.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
}
