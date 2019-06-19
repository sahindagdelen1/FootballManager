package com.fmchallenge.footballmanager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Player {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate contractDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private Team team;

    public Player() {
    }

    public Player(String name, String surname, LocalDate birthDate, LocalDate contractDate, Team team) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.contractDate = contractDate;
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
