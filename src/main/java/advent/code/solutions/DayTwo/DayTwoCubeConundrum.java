package advent.code.solutions.DayTwo;

import advent.code.solutions.Solution;

import javax.print.attribute.HashPrintJobAttributeSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class DayTwoCubeConundrum extends Solution {

    private final int MAX_GREEN = 13;
    private final int MAX_RED = 12;
    private final int MAX_BLUE = 14;

    @Override
    public void run() {

        List<String> input = readInputFile("src/main/resources/input-codes-day-2");
        List<Game> games = populateGames(input);


        System.out.println("TOTAL GAMES: " + games);
        List<Game> possibleGames = games.stream().filter(this::determinePossibility).toList();
        System.out.println("POSSIBLE GAMES: " + possibleGames);
        AtomicInteger total = new AtomicInteger();
        possibleGames.forEach(game -> total.set(total.get() + game.getId()));
        System.out.println("TOTAL ID SUM: " + total.get());

        int fewestCubes = getTotalPart2(games);
        System.out.println("FEWEST CUBES FOR GAMES: " + fewestCubes);

    }

    private List<Game> populateGames(List<String> input) {
        /*
        Game 1: 1 green, 6 red, 4 blue; 2 blue, 6 green, 7 red; 3 red, 4 blue, 6 green; 3 green; 3 blue, 2 green, 1 red
        delimiters : , ;
         */
        List<Game> games = new ArrayList<>();
        for (String game : input){
            String[] headerAndGames = game.split(":");
            String header = headerAndGames[0];
            String gamesString = headerAndGames[1];
            games.add(new Game(header, gamesString));
        }
        return games;
    }

    private boolean determinePossibility(Game game){
        for (Round round : game.getRounds()){
            if (round.getGreen() > MAX_GREEN
                    || round.getRed() > MAX_RED
                    || round.getBlue() > MAX_BLUE){
                return false;
            }
        }
        return true;
    }

    private int getTotalPart2(List<Game> games){
        AtomicInteger result = new AtomicInteger();
        games.forEach(game -> result.getAndAdd(determineFewestCubesAndMultiply(game)));
        return result.get();
    }

    private int determineFewestCubesAndMultiply(Game game) {
        int lowestGreen = 0;
        int lowestRed = 0;
        int lowestBlue = 0;
        List<Round> rounds = game.getRounds();

        for (Round round : rounds){
            lowestGreen = Math.max(round.getGreen(), lowestGreen);
            lowestRed = Math.max(round.getRed(), lowestRed);
            lowestBlue = Math.max(round.getBlue(), lowestBlue);
        }
        return lowestGreen * lowestRed * lowestBlue;
    }
}
