package advent.code.solutions;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class DayOneTrebuchet implements Solution {

    private int totalNum = 0;

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

    private List<String> getPossibleCoordinates(String code){
        List<String> possibleCoords = new ArrayList<>();
        List<String> codeList = new ArrayList<>();

        for (char c: code.toCharArray()){
            codeList.add(String.valueOf(c));
        }

        codeList.stream()
                .filter(this::isNumber)
                .peek(str -> System.out.println("MATCHED TOKEN: " + str))
                .forEach(str -> possibleCoords.add(String.valueOf(str)));
        return possibleCoords;
    }

    private Boolean isNumber(String token){
        try {
            Integer.parseInt(token);
            return true;
        } catch (Exception ignored){
        }
        return false;
    }

    private String generateValidCoords(List<String> possibleCoords){
        if (possibleCoords.isEmpty()){
            return null;
        }
        String firstNum = possibleCoords.get(0);
        String lastNum = possibleCoords.get(possibleCoords.size()-1);
        System.out.println("RETURNING VALUE: " + firstNum + "" + lastNum);
        return firstNum + lastNum;
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
