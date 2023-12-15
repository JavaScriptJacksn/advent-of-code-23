package advent.code.solutions.DayTwo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Game {

    private int id;
    private List<Round> rounds = new ArrayList<>();


    public Game(String header, String round){
        this.id = extractId(header);
        List<String> roundValues = Arrays.stream(round.split(";")).toList();
        System.out.println("ROUND VALUES: " + roundValues);
        roundValues.forEach(r -> rounds.add(parseRoundString(r)));
    }

    private int extractId(String header){
        return Integer.parseInt(header.replace(" ", "").substring(4));
    }

    private Round parseRoundString(String roundData){

        List<String> cubes = List.of(roundData.split(","));
        Round round = new Round();
        cubes.forEach(cube -> extractValues(cube.replace(" ",""), round));
        return round;

    }

    private void extractValues(String cube, Round round){
        if (cube.contains("green")){
            round.setGreen(Integer.parseInt(cube.substring(0, cube.indexOf("green"))));
        }
        if (cube.contains("red")){
            round.setRed(Integer.parseInt(cube.substring(0, cube.indexOf("red"))));
        }
        if (cube.contains("blue")){
            round.setBlue(Integer.parseInt(cube.substring(0, cube.indexOf("blue"))));
        }
        System.out.println("ADDED CUBE: " + cube + " TO ROUND: " + round);
    }
}
