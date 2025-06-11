package ru.cleancode.steeplechase;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.cleancode.steeplechase.model.Athlete;
import ru.cleancode.steeplechase.service.RaceService;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class SteeplechaseApplication implements CommandLineRunner {

	private final RaceService raceService;
	private final List<Athlete> athletes;

	public static void main(String[] args) {
		SpringApplication.run(SteeplechaseApplication.class, args);
	}

	@Override
	public void run(String... args) {
		raceService.conductRace(athletes);
	}
}
