package org.example.domain.entities;

import java.time.LocalDate;

public class Mentoring extends Content {

    private final LocalDate dateInitial;
    private final LocalDate dateFinal;


    public Mentoring(String type, String title, String description, int duration, LocalDate dateInitial, LocalDate dateFinal) {
        super(type, title, description, duration);
        this.dateInitial = dateInitial;
        this.dateFinal = dateFinal;
    }

    @Override
    public double calculationXP() {
        return XP_STANDARD + 20d;
    }

    @Override
    public String typeContent() {
        return super.typeContent();
    }

    public LocalDate getDateInitial() {
        return dateInitial;
    }

    public LocalDate getDateFinal() {
        return dateFinal;
    }
}
