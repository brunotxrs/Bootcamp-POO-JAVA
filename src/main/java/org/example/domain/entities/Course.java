package org.example.domain.entities;

public class Course extends Content{

    public Course(String type, String title, String description, int duration) {
        super(type, title, description, duration);
    }

    @Override
    public double calculationXP() {
        return this.getDuration() * XP_STANDARD;
    }

    @Override
    public String typeContent() {
        return super.typeContent();
    }
}
