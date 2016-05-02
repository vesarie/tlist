package tlist.models;

public class Label {

    private final int id;
    private final int person;
    private String name;

    public Label(int id, int person, String name) {
        this.id = id;
        this.person = person;
        this.name = name;
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

    public void setName(String name) {
        this.name = name;
    }

}
