package com.epam.winterjavalab.entiteis.interfaces;

import java.time.LocalDate;
import java.util.List;

public abstract class User {
    private String name;
    private String secondName;
    private LocalDate birthdate;
    private String phoneNumber;
    private boolean isVegatarian;
    private List<Dish> dishList;

    public User() {
    }

    public User(String name, String secondName, LocalDate birthdate, String phoneNumber, boolean isVegatarian, List<Dish> dishList) {
        this.name = name;
        this.secondName = secondName;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.isVegatarian = isVegatarian;
        this.dishList = dishList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setVegatarian(boolean vegatarian) {
        isVegatarian = vegatarian;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isVegatarian() {
        return isVegatarian;
    }

    public List<Dish> getDishList() {
        return dishList;
    }
    public void addDish(Dish dish) {
        dishList.add(dish);
    }
    public int getTotalCheck() {
        return dishList
                .stream()
                .mapToInt(Dish::getPriceDish)
                .sum();
    }


}
