package org.example.domain.entities;

public abstract class Content {

    private final String type;
    private final String title;
    private final String description;
    private final int duration;
    protected static final double XP_STANDARD = 10d;

    public Content(String type, String title, String description, int duration){
        this.type = type;
        this.title = title;
        this.description = description;
        this.duration = duration;
    }

    // methods

    public abstract double calculationXP();

    public String typeContent(){
        return String.format("""
                %s
                """, this.type);
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }


}
