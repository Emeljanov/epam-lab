package com.epam.winterjavalab.service;

import com.epam.winterjavalab.entiteis.interfaces.User;


public class CheckPrinter {
    private static String STRING_FOMAT = "%14s | %5s \n";
    private static String TOTAL_PRICE = "TOTAL PRICE";
    private static String DISH_PRICE = "Dish price";
    private static String DISH_TOT = "-";
    private static String DISH_SEP = "-------";
    private static String USER_TOTAL = "----------------------";
    private static String TO_NEXT_LINE = "\n";
    public static void printCafeCheck(User user) {
        System.out.println(user.getName());
        user.getDishList()
                .forEach(d -> {
                    System.out.println(d.getClass().getSimpleName());
                    System.out.printf(STRING_FOMAT, d.getBase(), d.getBasePrice());
                    d.getIngredients()
                            .forEach(i -> System.out.printf(STRING_FOMAT,i,i.getPrice()));
                    System.out.println(DISH_TOT);
                    System.out.printf(STRING_FOMAT,DISH_PRICE,d.getPriceDish());
                    System.out.println(DISH_SEP);
                });
        System.out.println(USER_TOTAL);
        System.out.printf(STRING_FOMAT, TOTAL_PRICE,user.getTotalCheck());

    }

}
