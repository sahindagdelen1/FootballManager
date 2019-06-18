package com.fmchallenge.footballmanager.control;

import com.fmchallenge.footballmanager.entity.Player;
import com.fmchallenge.footballmanager.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "players")
public class PlayerControl {

    @Autowired
    PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<Player>> getAll() {
        return ResponseEntity.ok(playerService.getAll());
    }

    @GetMapping
    public ResponseEntity<List<Player>> getByTeamId(@RequestParam String teamId) {
        return ResponseEntity.ok(playerService.getPlayersByTeamId(teamId));
    }

    @GetMapping
    public ResponseEntity<Player> getByPlayerId(@RequestParam String playerId) {
        return ResponseEntity.ok(playerService.get(playerId).get());
    }

    @PostMapping(value = "/")
    public ResponseEntity<Player> insertTeam(@RequestBody Player playerParameter) {
        Optional<Player> player = playerService.insert(playerParameter);
        if (!player.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(player.get());
    }


    @PutMapping(value = "/")
    public ResponseEntity<Player> updatePlayer(@RequestBody Player playerParameter) {
        Optional<Player> player = playerService.update(playerParameter);
        if (!player.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(player.get());
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteTeam(@PathVariable String idParam) {
        playerService.delete(idParam);
        return ResponseEntity.ok().build();
    }
}
