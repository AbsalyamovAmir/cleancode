package ru.cleancode.steeplechase.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.cleancode.steeplechase.model.Athlete;
import ru.cleancode.steeplechase.service.RaceService;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RaceServiceImpl implements RaceService {

    private static final int HURDLES_COUNT = 10;

    @Override
    public void conductRace(List<Athlete> athletes) {
        log.info("Забег начинается! Участники: {}", athletes.stream().map(Athlete::getName).collect(Collectors.joining(", ")));

        List<CompletableFuture<Void>> futures = athletes.stream()
                .map(athlete -> CompletableFuture.runAsync(() -> runRace(athlete)))
                .toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        Athlete winner = athletes.stream()
                .min(Comparator.comparingInt(Athlete::getSpeed))
                .orElseThrow();

        Athlete outsider = athletes.stream()
                .max(Comparator.comparingInt(Athlete::getSpeed))
                .orElseThrow();

        log.info("Результаты забега:\nПобедитель: {} (скорость {} мс/барьер)\n" +
                        "Аутсайдер: {} (скорость {} мс/барьер)",
                winner.getName(), winner.getSpeed(),
                outsider.getName(), outsider.getSpeed());
    }

    private void runRace(Athlete athlete) {
        for (int hurdle = 1; hurdle <= HURDLES_COUNT; hurdle++) {
            try {
                TimeUnit.MILLISECONDS.sleep(athlete.getSpeed());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            log.info("Спортсмен #{} {} пробежал барьер №{}\n",
                    athlete.getId(), athlete.getName(), hurdle);
        }
        log.info("Спортсмен №{} {} финишировал!\n",
                athlete.getId(), athlete.getName());
    }
}
