package com.epam.winterjavalab.entiteis;

import com.epam.winterjavalab.entiteis.enums.Ingredient;
import com.epam.winterjavalab.entiteis.interfaces.Dish;

import java.util.ArrayList;
import java.util.List;

public class Salad implements Dish {
    private static final String SALAD_BASE = "Salad base";
    private static int basePrice = 13;

    private List<Ingredient> saladIngredients = new ArrayList<>();

    @Override
    public void addIngredient(Ingredient ingredient) {
        saladIngredients.add(ingredient);
    }

    @Override
    public String getBase() {
        return SALAD_BASE;
    }

    @Override
    public int getBasePrice() {
        return basePrice;
    }

    @Override
    public List<Ingredient> getIngredients() {
        return saladIngredients;
    }

    @Override
    public int getPriceDish() {
        int price = basePrice + saladIngredients
                .stream().mapToInt(Ingredient::getPrice).sum();
        return price;
    }
    @Override
    public String toString() {
        return getClass().getSimpleName() + getIngredients() + getPriceDish();
    }

}
