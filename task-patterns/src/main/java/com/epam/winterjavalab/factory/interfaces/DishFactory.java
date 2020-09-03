package com.epam.winterjavalab.factory.interfaces;

import com.epam.winterjavalab.entiteis.enums.Ingredient;
import com.epam.winterjavalab.entiteis.interfaces.Dish;
import com.epam.winterjavalab.factory.Constans;

import java.util.Scanner;

public interface DishFactory {
    Dish createDish(Scanner scanner);

    default void addIngredient(Dish dish, Ingredient ingredient, Scanner scanner){
        if (scanner.hasNextLine()) {
            if(scanner.nextLine().equals(Constans.Y)) dish.addIngredient(ingredient);
        }
    }
}
