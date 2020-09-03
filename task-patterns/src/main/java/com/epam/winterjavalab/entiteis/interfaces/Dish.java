package com.epam.winterjavalab.entiteis.interfaces;

import com.epam.winterjavalab.entiteis.enums.Ingredient;

import java.util.List;

public interface Dish {
    void addIngredient(Ingredient ingredient);
    String getBase();
    int getBasePrice();
    List<Ingredient> getIngredients();
    int getPriceDish();

}
