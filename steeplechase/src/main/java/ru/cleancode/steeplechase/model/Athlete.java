package ru.cleancode.steeplechase.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Athlete {
    private int id;
    private String name;
    private int speed;
}
