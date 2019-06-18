package com.fmchallenge.footballmanager.model;

import com.fmchallenge.footballmanager.entity.Player;

public class PlayerTransferFee {
    private Player player;
    private TransferFee transferFee;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public TransferFee getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(TransferFee transferFee) {
        this.transferFee = transferFee;
    }
}
