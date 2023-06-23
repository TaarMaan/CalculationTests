package com.artamonov.tests.bek.service;

import com.artamonov.tests.bek.model.Numbers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OperationsService {
    public static double calculate(Numbers numbers, char op) {
        System.out.println("OperationService calculate");
        System.out.println(numbers);
        System.out.println(op);
        return switch (op) {
            case '+' -> plus(convert(numbers.getNum1(), numbers.getNum2(), numbers.getSystem()));
            case '-' -> minus(convert(numbers.getNum1(), numbers.getNum2(), numbers.getSystem()));
            case '*' -> multiplication(convert(numbers.getNum1(), numbers.getNum2(), numbers.getSystem()));
            case '/' -> division(convert(numbers.getNum1(), numbers.getNum2(), numbers.getSystem()));
            default -> 0.0;
        };
    }

    public static int plus(Numbers numbers) {
        System.out.println("plus operation");
        System.out.println(numbers.getNum1());
        System.out.println(numbers.getNum2());
        return numbers.getNum1() + numbers.getNum2();
    }

    public static int minus(Numbers numbers) {
        System.out.println("minus operation");
        System.out.println(numbers.getNum1());
        System.out.println(numbers.getNum2());
        return numbers.getNum1() - numbers.getNum2();
    }

    public static int multiplication(Numbers numbers) {
        System.out.println("multiplication operation");
        System.out.println(numbers.getNum1());
        System.out.println(numbers.getNum2());
        return numbers.getNum1() * numbers.getNum2();
    }

    public static double division(Numbers numbers) {
        System.out.println("division operation");
        System.out.println(numbers.getNum1());
        System.out.println(numbers.getNum2());
        return (double)numbers.getNum1() / numbers.getNum2();
    }

    public static Numbers convert(int num1, int num2, int system) {
        Numbers numbers = new Numbers();
        if (system == 10 || system == 0 || system == 2 || system == 16) {
            numbers.setNum1(num1);
            numbers.setNum2(num2);
        }
        return numbers;
    }
}
