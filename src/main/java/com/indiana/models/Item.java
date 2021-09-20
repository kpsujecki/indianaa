package com.indiana.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(
        name = "items"
)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<User> whoFound;

    private Date dateFound;

    @Lob
    private byte[] image;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getWhoFound() {
        return whoFound;
    }

    public void setWhoFound(List<User> whoFound) {
        this.whoFound = whoFound;
    }

    public Date getDateFound() {
        return dateFound;
    }

    public void setDateFound(Date dateFound) {
        this.dateFound = dateFound;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Item() {
    }

    public Item(String name, String description, User user, Date dateFound, byte[] image) {
        this.name = name;
        this.description = description;
        this.user = user;
        this.dateFound = dateFound;
        this.image = image;
    }
}
