package com.eldar.tarjetascredito;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Ejercicio5 {
    public static void main(String[] args) {
        //Casos de prueba
        String[] myArray = {"FirstWord", "SecondWord", "THIRDWORD"};
        String result = joinAndConvertToLowerCase(myArray);
        System.out.println(result);

        String[] singleElementArray = new String[]{"SingleWord"};
        String result1 = joinAndConvertToLowerCase(singleElementArray);
        System.out.println(result1);

        String[] multipleElementsArray = new String[]{"FirstWord", "SecondWord", "ThirdWord"};
        String result2 = joinAndConvertToLowerCase(multipleElementsArray);
        System.out.println(result2);

        String[] spacesArray = new String[]{"Word With Space", "Another Word"};
        String result3 = joinAndConvertToLowerCase(spacesArray);
        System.out.println(result3);

        String[] uppercaseArray = new String[]{"UpperCaseWord", "ANOTHER_UPPERCASE"};
        String result4 = joinAndConvertToLowerCase(uppercaseArray);
        System.out.println(result4);

        String[] specialCharsArray = new String[]{"SpecialCharWord!", "Punctuation.,"};
        String result5 = joinAndConvertToLowerCase(specialCharsArray);
        System.out.println(result5);
    }

    public static String joinAndConvertToLowerCase(String[] array) {
        return Arrays.stream(array)
                .map(String::toLowerCase)
                .collect(Collectors.joining(" "));
    }
}