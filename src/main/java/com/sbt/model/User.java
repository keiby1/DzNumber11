package com.sbt.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by lushi on 01.02.2017.
 */
@Entity
@Table(name = "user_table")
public class User extends BaseEntity {
    @Column
    private String name;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Item> items;
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Bid bid;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
