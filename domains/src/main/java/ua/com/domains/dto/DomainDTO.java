package ua.com.domains.dto;

public class DomainDTO {

    private int id;
    private String name;

    public DomainDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public DomainDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
