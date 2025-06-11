package ru.cleancode.steeplechase.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.cleancode.steeplechase.model.Athlete;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Configuration
public class AppConfig {
    @Bean
    public List<Athlete> athletes() {
        Random random = new Random();
        return Arrays.asList(
                new Athlete(1, "Иван", 100 + random.nextInt(100)),
                new Athlete(2, "Петр", 100 + random.nextInt(100)),
                new Athlete(3, "Сергей", 100 + random.nextInt(100)),
                new Athlete(4, "Алексей", 100 + random.nextInt(100)),
                new Athlete(5, "Дмитрий", 100 + random.nextInt(100))
        );
    }
}
