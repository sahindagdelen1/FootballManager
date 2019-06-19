package com.fmchallenge.footballmanager.control;

import com.fmchallenge.footballmanager.entity.Team;
import com.fmchallenge.footballmanager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "teams")
public class TeamControl {

    @Autowired
    TeamService teamService;

    @GetMapping
    public ResponseEntity<List<Team>> getTeams() {
        return ResponseEntity.ok(teamService.getAll());
    }

    @GetMapping(value = "/{idParam}")
    public ResponseEntity<Team> getTeamById(@PathVariable String idParam) {
        if (idParam == null || idParam.equals("")) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Team> team = teamService.get(idParam);
        if (!team.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(team.get());
    }

    @PostMapping(value = "/")
    public ResponseEntity<Team> insertTeam(@RequestBody Team teamParameter) {
        Optional<Team> team = teamService.insert(teamParameter);
        if (!team.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(team.get());
    }


    @PutMapping(value = "/")
    public ResponseEntity<Team> updateTeam(@RequestBody Team teamParameter) {
        Optional<Team> team = teamService.update(teamParameter);
        if (!team.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(team.get());
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteTeam(@PathVariable String idParam) {
        teamService.delete(idParam);
        return ResponseEntity.ok().build();
    }
}
