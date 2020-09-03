package com.epam.winterjavalab.cafe;
import com.epam.winterjavalab.entiteis.UsualUser;
import com.epam.winterjavalab.entiteis.interfaces.Dish;
import com.epam.winterjavalab.entiteis.interfaces.User;
import com.epam.winterjavalab.factory.PizzaDishFactory;
import com.epam.winterjavalab.factory.SaladDishFactory;
import com.epam.winterjavalab.factory.UserFactory;
import com.epam.winterjavalab.service.CheckPrinter;
import com.epam.winterjavalab.service.FileService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cafe {
    private static final String READY = "Cafe ready to work";
    private static final String CHOOSE_USER = "Choose user from the list(print name) or print 'new'";
    private static final String NEW = "new";
    private static final String FIX = "fix";
    private static final String CLOSE = "close";
    private static final String OPERATION_WITH_CLIENT = "Make new order, fix order or nothing? new/fix/nothing";
    private static final String WORK_ON = "New client or close cafe? new/close";
    private static final String WORK_WORK = "Cafe is free, new client come up!";
    private static final String CAFE_CLOSED ="Sorry, cafe closed in 1 min";
    private static final String FINISH_WITH_CLIENT = "finished with you, your check ->";

    private static volatile Cafe instance;
    private List<User> userList;

    private Cafe() {
        userList = new ArrayList<>();
    }

    public static Cafe getInstance() {
        if(instance == null) {
            synchronized (Cafe.class) {
                if(instance == null) {
                    instance = new Cafe();
                }
            }

        }
        return instance;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void startWork() {
        System.out.println(READY);
        try(Scanner scanner = new Scanner(System.in)) {
            User user = chooseUser(scanner);
            operationWithClient(user,scanner);
            workOn(scanner);
        }
    }
    private void makeDishOrder(Scanner scanner, User user) {

                  if(user.isVegatarian()) {
                    Dish dish = new SaladDishFactory().createDish(scanner);
                    user.addDish(dish);
                }
                else {
                    Dish dish = new PizzaDishFactory().createDish(scanner);
                    user.addDish(dish);
                }

                System.out.println("ok,wait");
               

    }
    private void fixDishOrder(Scanner scanner, User user) {
        user.getDishList().clear();
        makeDishOrder(scanner,user);
    }
    private User chooseUser(Scanner scanner) {
        userList
                .forEach(x -> System.out.println(x.getName()));
        System.out.println(CHOOSE_USER);
        User user = null;
            if(scanner.hasNextLine()) {
                String  name = scanner.nextLine();
                if(name.equals(NEW)) {
                    user = new UserFactory().createUsualUser(scanner);
                    userList.add(user);
                }
                else {
                    user = userList
                            .stream()
                            .filter(x -> x.getName().equals(name))
                            .findFirst()
                            .get();
                }
            }

        return user;
    }
    private void operationWithClient(User user, Scanner scanner) {
        System.out.println(OPERATION_WITH_CLIENT);
            String answer = null;
            if(scanner.hasNextLine()) {
                answer = scanner.nextLine();
                if(answer.equals(NEW)) {
                    makeDishOrder(scanner,user);
                    operationWithClient(user,scanner);
                }
                else if(answer.equals(FIX)) {
                    fixDishOrder(scanner,user);
                    operationWithClient(user,scanner);
                }
                else {
                    System.out.println(FINISH_WITH_CLIENT);
                    CheckPrinter.printCafeCheck(user);
                    FileService.saveOrder(user);
                }
            }
    }
    private void workOn(Scanner scanner) {
        System.out.println(WORK_ON);
        String answer = scanner.nextLine();
        if(answer.equals(NEW)) {
            System.out.println(WORK_WORK);
            startWork();
        }
        else System.out.println(CAFE_CLOSED);
    }
}
