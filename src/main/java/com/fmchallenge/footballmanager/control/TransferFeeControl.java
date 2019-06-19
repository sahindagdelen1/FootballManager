package com.fmchallenge.footballmanager.control;


import com.fmchallenge.footballmanager.entity.Player;
import com.fmchallenge.footballmanager.entity.Team;
import com.fmchallenge.footballmanager.model.Currency;
import com.fmchallenge.footballmanager.model.PlayerTransferFee;
import com.fmchallenge.footballmanager.service.PlayerService;
import com.fmchallenge.footballmanager.service.TransferFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "transferFee")
public class TransferFeeControl {

    @Autowired
    TransferFeeService transferFeeService;

    @Autowired
    PlayerService playerService;

    @GetMapping
    public ResponseEntity<PlayerTransferFee> calculatePlayerTransferFee(@RequestParam String playerId, @RequestParam Currency currency) {
        if (playerId == null || playerId.equals("")) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Player> playerOpt = playerService.get(playerId);
        if (!playerOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        PlayerTransferFee playerTransferFee = transferFeeService.calculateAllTransferFees(playerOpt.get(), currency);
        return ResponseEntity.ok(playerTransferFee);
    }
}
