package com.epam.winterjavalab.factory;

import com.epam.winterjavalab.entiteis.Pizza;
import com.epam.winterjavalab.entiteis.enums.PizzaIngredients;
import com.epam.winterjavalab.entiteis.interfaces.Dish;
import com.epam.winterjavalab.factory.interfaces.DishFactory;
import static com.epam.winterjavalab.factory.Constans.*;

import java.util.Scanner;

public class PizzaDishFactory implements DishFactory {

    @Override
    public Dish createDish(Scanner scanner) {

        Pizza pizza = new Pizza();
        System.out.println(MAKE_PIZZA);
        System.out.println(ADD_PEPPER);
        addIngredient(pizza,PizzaIngredients.PEPPER,scanner);
        System.out.println(ADD_BACON);
        addIngredient(pizza, PizzaIngredients.BACON,scanner);
        System.out.println(ADD_HAM);
        addIngredient(pizza,PizzaIngredients.HAM,scanner);
        System.out.println(ADD_TOMATO);
        addIngredient(pizza,PizzaIngredients.TOMATO,scanner);
        System.out.println(ADD_PARMESAN);
        addIngredient(pizza,PizzaIngredients.PARMESAN,scanner);
        System.out.println(ADD_OREGANO);
        addIngredient(pizza,PizzaIngredients.OREGANO,scanner);
        return pizza;
    }
}
