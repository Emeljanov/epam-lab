package com.epam.winterjavalab.entiteis;
import com.epam.winterjavalab.entiteis.enums.Ingredient;
import com.epam.winterjavalab.entiteis.enums.PizzaIngredients;
import com.epam.winterjavalab.entiteis.interfaces.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pizza implements Dish {
    private static final String BASE = "Pizza base";
    private static int basePrice = 25;
    private List<Ingredient> pizzaIngredients = new ArrayList<>();

    @Override
    public void addIngredient(Ingredient ingredient) {
        pizzaIngredients.add(ingredient);
    }

    @Override
    public String getBase() {
        return BASE;
    }

    @Override
    public int getBasePrice() {
        return basePrice;
    }

    @Override
    public List<Ingredient> getIngredients() {
        return pizzaIngredients;
    }

    @Override
    public int getPriceDish() {
        int price = basePrice + pizzaIngredients
                .stream().mapToInt(Ingredient::getPrice).sum();
        return price;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + getIngredients() + getPriceDish();
    }
}
