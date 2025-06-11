package ru.cleancode.steeplechase.service;

import ru.cleancode.steeplechase.model.Athlete;

import java.util.List;

public interface RaceService {

    void conductRace(List<Athlete> athletes);
}
