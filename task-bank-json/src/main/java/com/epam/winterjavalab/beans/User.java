package com.epam.winterjavalab.beans;

import com.epam.winterjavalab.beans.enums.Sex;
import com.epam.winterjavalab.constants.Constants;

import java.time.LocalDate;

public class User {
    private int id ;
    private String name;
    private String secondName;
    private Sex sex;
    private LocalDate birthday;

    public User() {
    }

    public User(int id, String name, String secondName, Sex sex, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.sex = sex;
        this.birthday = birthday;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getSecondName() {
        return secondName;
    }

    public Sex getSex() {
        return sex;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getFullName () {
        return secondName + Constants.SPACE + name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", sex=" + sex +
                '}';
    }
}
