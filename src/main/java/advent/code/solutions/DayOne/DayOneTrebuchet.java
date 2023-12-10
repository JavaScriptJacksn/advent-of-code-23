package advent.code.solutions.DayOne;

import advent.code.solutions.Solution;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class DayOneTrebuchet implements Solution {

    private int totalNum = 0;

    private final List<String> STRING_NUMBERS = Arrays.asList(
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine"
    );

    public void  run(){

        /*
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet

        should output 12, 28, 16, 77
         */

        List<String> inputCodes = readInputCodes("src/main/resources/input-codes-day1");
        System.out.println("INPUT CODES: " + inputCodes);

        inputCodes.stream()
                .map(this::getPossibleCoordinates)
                .map(this::generateValidCoords).filter(Objects::nonNull)
                .forEach(result -> totalNum = totalNum + Integer.parseInt(result));

        System.out.println("RESULT = " + getTotalNum());

    }

    private List<NumberIndex> getPossibleCoordinates(String code){
        System.out.println("PARSING CODE: " + code);
        List<NumberIndex> possibleCoords = new ArrayList<>();
        char[] charArray = code.toCharArray();

        for (int i = 0; i < charArray.length; i++){

            char c = code.charAt(i);

            // if number, add
            if (isNumber(String.valueOf(c))){
                System.out.println("TOKEN IS NUMBER: " + c);
                possibleCoords.add(new NumberIndex(String.valueOf(c), i));
            }
        }

        possibleCoords.addAll(getPlainTextNumbers(code));
        return possibleCoords;
    }

    private List<NumberIndex> getPlainTextNumbers(String code){
        List<NumberIndex> result = new ArrayList<>();
        for (String num : STRING_NUMBERS){
            if (code.contains(num)){
                System.out.println("MATCHED:" + code);
                result.add(new NumberIndex(getIntForString(num), code.indexOf(num)));
                result.add(new NumberIndex(getIntForString(num), code.lastIndexOf(num)));
            }
        }
        return result;
    }

    private String getIntForString(String number){
        switch (number){
            case"one" -> {
                return "1";
            }
            case"two" -> {
                return "2";
            }
            case"three" -> {
                return "3";
            }
            case"four" -> {
                return "4";
            }
            case"five" -> {
                return "5";
            }
            case"six" -> {
                return "6";
            }
            case"seven" -> {
                return "7";
            }
            case"eight" -> {
                return "8";
            }
            case"nine" -> {
                return "9";
            }
            default -> {
                return null;
            }
        }
    }

    private String generateValidCoords(List<NumberIndex> possibleCoords){
        if (possibleCoords.isEmpty()){
            return null;
        }

        NumberIndex first = possibleCoords.get(0);
        NumberIndex last = possibleCoords.get(0);

        for (NumberIndex coord: possibleCoords){
            if (coord.getIndex() < first.getIndex()){
                first = coord;
            }
            if (coord.getIndex() > last.getIndex()){
                last = coord;
            }
        }

        return first.getNumber() + last.getNumber();
    }

    private Boolean isNumber(String token){
        try {
            Integer.parseInt(token);
            return true;
        } catch (Exception ignored){
        }
        return false;
    }

    private List<String> readInputCodes(String filePath){
        Path path = Paths.get(filePath);
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("ERROR: " + e);
        }
        return Collections.emptyList();
    }

    public int getTotalNum(){
        return this.totalNum;
    }
}
