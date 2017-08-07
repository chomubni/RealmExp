package com.example.ivan.realmexp;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ivan on 8/6/17.
 */

public class User extends RealmObject {
    //@PrimaryKey
    //private int id;
    @PrimaryKey
    private String id;

    private String firstName;
    private String secondName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}
