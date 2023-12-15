package advent.code;

import advent.code.solutions.DayOne.DayOneTrebuchet;
import advent.code.solutions.DayTwo.DayTwoCubeConundrum;
import advent.code.solutions.Solution;

import java.util.Arrays;
import java.util.List;

public class MainApp {

    public static void main (String[] args){

        List<Solution> solutionsToRun = Arrays.asList(
//                new DayOneTrebuchet(),
                new DayTwoCubeConundrum()
        );

        solutionsToRun.forEach(Solution::run);
    }

}
