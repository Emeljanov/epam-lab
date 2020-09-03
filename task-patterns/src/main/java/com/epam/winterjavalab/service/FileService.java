package com.epam.winterjavalab.service;

import com.epam.winterjavalab.entiteis.interfaces.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileService {
    private static final String LINE_DELIMETR = "------------ \n";
    private static final String PATH = "./orders.txt";
    private static final String DELIMETR = ";\n";
    private static final String TOTAL = "TOTAL ";

    public static void saveOrder(User user) {
        try(PrintWriter writer = new PrintWriter(new FileWriter(PATH,true))) {
            writer
                    .append(LINE_DELIMETR)
                    .append(user.getName())
                    .append(DELIMETR)
                    .append(user.getSecondName())
                    .append(DELIMETR)
                    .flush();

            user.getDishList().forEach(dish -> {
                    writer.append(dish.toString()).flush();
                    writer.append(DELIMETR);

            });
            writer.print(TOTAL);
            writer.print(user.getTotalCheck());
            writer.append(DELIMETR).append(LINE_DELIMETR).flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void clearOrderFile() {
        try {
            if (!Files.notExists(Paths.get(PATH))) {
                Files.delete(Paths.get(PATH));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
