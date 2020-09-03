package com.epam.winterjavalab.entiteis.enums;

public enum PizzaIngredients implements Ingredient {
    PEPPER(5), BACON(10), HAM(10), TOMATO(7), PARMESAN(5),OREGANO(2);

    int price;

    PizzaIngredients(int price) {
        this.price = price;
    }
    @Override
    public int getPrice() {
        return price;
    }

}
