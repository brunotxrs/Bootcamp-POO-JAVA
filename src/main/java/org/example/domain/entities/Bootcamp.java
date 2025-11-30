package org.example.domain.entities;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

public class Bootcamp {
    private final String name;
    private final String description;

    private final LocalDate dateInitial;
    private final LocalDate dateFinal;

    private final Set<Content> contents = new LinkedHashSet<>();
    private final Set<Dev> devs = new LinkedHashSet<>();

    public Bootcamp(String name, String description, LocalDate dateInitial) {
        this.name = name;
        this.description = description;
        this.dateInitial = dateInitial;
        this.dateFinal = dateInitial.plusDays(45);
    }


    public String getName() {
        return name;
    }

    public boolean addContent(Content content){
        return this.contents.add(content);
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDateInitial() {
        return dateInitial;
    }

    public LocalDate getDateFinal() {
        return dateFinal;
    }

    public Set<Content> getContents() {
        return contents;
    }

    public Set<Dev> getDevs() {
        return devs;
    }


}
