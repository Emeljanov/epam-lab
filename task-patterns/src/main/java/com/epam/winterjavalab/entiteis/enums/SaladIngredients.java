package com.epam.winterjavalab.entiteis.enums;

public enum SaladIngredients implements Ingredient {
    CABBAGE(9), PEPPER(4), TOMATO(5), CARROT(12), VINEGAR(2);

    private int price;

    SaladIngredients(int price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
