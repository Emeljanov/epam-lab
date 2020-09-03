package com.epam.winterjavalab;
import com.epam.winterjavalab.cafe.Cafe;
import com.epam.winterjavalab.service.FileService;

public class CafeRunner {
    public static void main(String[] args) {
        FileService.clearOrderFile();
        Cafe cafe = Cafe.getInstance();
        cafe.startWork();
    }
}
