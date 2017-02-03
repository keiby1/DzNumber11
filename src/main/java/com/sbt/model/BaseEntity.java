package com.sbt.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by lushi on 01.02.2017.
 **/

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue
    private Integer id;
}
