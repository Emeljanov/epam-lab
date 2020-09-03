package com.epam.winterjavalab.factory;

import com.epam.winterjavalab.entiteis.Salad;
import com.epam.winterjavalab.entiteis.enums.Ingredient;
import com.epam.winterjavalab.entiteis.enums.SaladIngredients;
import com.epam.winterjavalab.entiteis.interfaces.Dish;
import com.epam.winterjavalab.factory.interfaces.DishFactory;
import static com.epam.winterjavalab.factory.Constans.*;
import java.util.Scanner;

public class SaladDishFactory implements DishFactory {
    @Override
    public Dish createDish(Scanner scanner) {
        Salad salad = new Salad();
        System.out.println(MAKE_SALAD);
        System.out.println(ADD_CABBAGE);
        addIngredient(salad,SaladIngredients.CABBAGE,scanner);
        System.out.println(ADD_PEPPER);
        addIngredient(salad,SaladIngredients.PEPPER,scanner);
        System.out.println(ADD_TOMATO);
        addIngredient(salad,SaladIngredients.TOMATO,scanner);
        System.out.println(ADD_CARROT);
        addIngredient(salad,SaladIngredients.CARROT,scanner);
        System.out.println(ADD_VINEGAR);
        addIngredient(salad,SaladIngredients.VINEGAR,scanner);
        return salad;
    }

}
