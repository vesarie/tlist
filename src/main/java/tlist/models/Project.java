package tlist.models;

public class Project {

    private final int id;
    private final int person;
    private String name;
    private int color;

    public Project(int id, int person, String name, int color) {
        this.id = id;
        this.person = person;
        this.name = name;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public int getPerson() {
        return person;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(int color) {
        this.color = color;
    }

}
