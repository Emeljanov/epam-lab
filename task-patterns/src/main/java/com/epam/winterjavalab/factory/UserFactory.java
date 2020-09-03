package com.epam.winterjavalab.factory;

import com.epam.winterjavalab.entiteis.UsualUser;
import com.epam.winterjavalab.entiteis.interfaces.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import static com.epam.winterjavalab.factory.Constans.*;

public class UserFactory {
    public User createUsualUser(Scanner scanner) {
        User user = new UsualUser();
        System.out.println(NEW_USER);
        if(scanner.hasNextLine()) {
            user.setName(scanner.nextLine());
        }
        System.out.println(ENTER_SECONDNAME);
        if(scanner.hasNextLine()) {
            user.setSecondName(scanner.nextLine());
        }
        System.out.println(ENTER_PHONENUMBER);
        if(scanner.hasNextLine()) {
            user.setPhoneNumber(scanner.nextLine());
        }
        System.out.println(ENTER_BIRTHDAY);
        if(scanner.hasNextLine()) {
            String dateString = scanner.nextLine();
            String[] dateM = dateString.split(SPACE);
            LocalDate date = LocalDate.of(Integer.parseInt(dateM[0])
                    ,Integer.parseInt(dateM[1])
                    ,Integer.parseInt(dateM[2]));
            user.setBirthdate(date);
        }
        System.out.println(IS_VEGATARIN);
        if(scanner.hasNextLine()) {
            if(scanner.nextLine().equals(Y)) {
                user.setVegatarian(true);
            }
        }
        user.setDishList(new ArrayList<>());

        return user;
    }
}
