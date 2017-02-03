package com.sbt.model;

import javax.persistence.*;

/**
 * Created by lushi on 01.02.2017.
 */
@Entity
public class Item extends BaseEntity {
    @Column
    private String name;
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
