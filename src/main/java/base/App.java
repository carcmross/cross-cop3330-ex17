package base;

import java.util.Scanner;
import java.util.regex.Pattern;

/*
 *  UCF COP3330 Summer 2021 Assignment 1 Solution
 *  Copyright Marc-Anthony Cross
 */

/*
 * Sometimes you have to perform a more complex calculation based on some provided inputs and then use that result to
 * make a determination.
 *
 * Create a program that prompts for your weight, gender, total alcohol consumed (in ounces), and the amount of time
 * since your last drink. Calculate your blood alcohol content (BAC) using this formula
 *
 * BAC = (A × 5.14 / W × r) − .015 × H
 *
 * where
 *
 *     A is total alcohol consumed, in ounces (oz).
 *     W is body weight in pounds.
 *     r is the alcohol distribution ratio:
 *         0.73 for men
 *         0.66 for women
 *     H is number of hours since the last drink.
 *
 * Display whether or not it’s legal to drive by comparing the blood alcohol content to 0.08.
 * Example Output
 *
 * Enter a 1 is you are male or a 2 if you are female: 1
 * How many ounces of alcohol did you have? 3
 * What is your weight, in pounds? 175
 * How many hours has it been since your last drink? 1
 *
 * Your BAC is 0.049323
 * It is legal for you to drive.
 *
 *
 *
 * Enter a 1 is you are male or a 2 if you are female: 1
 * How many ounces of alcohol did you have? 5
 * What is your weight, in pounds? 175
 * How many hours has it been since your last drink? 1
 *
 * Your BAC is 0.092206
 * It is not legal for you to drive.
 *
 * Constraint
 *
 *     Prevent the user from entering non-numeric values.
 *
 * Challenges
 *
 *     Handle metric units.
 *     Look up the legal BAC limit by state and prompt for the state. Display a message that states whether or not
 *     it’s legal to drive based on the computed BAC.
 *     Develop this as a mobile application that makes it easy to record each drink, updating the BAC each time a
 *     drink is entered.
 *
 */

public class App {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        String gender = getGender();
        String oz = getOunces();
        while (isNum(oz) == false)
            oz = getOunces();
        String weight = getWeight();
        while (isNum(weight) == false)
            weight = getWeight();
        String hours_since = getHoursSinceLast();
        while (isNum(hours_since) == false)
            hours_since = getHoursSinceLast();

        String bac = calcBAC(gender, oz, weight, hours_since);
        String outputString = generateOutput(bac);
        System.out.println(outputString);
    }

    public static String generateOutput(String bac) {
        double bac_double = Double.parseDouble(bac);

        if (bac_double < 0.08)
            return String.format("\nYour BAC is %f.\nIt is legal for you to drive.", bac_double);
        else
            return String.format("\nYour BAC is %f.\nIt is not legal for you to drive.", bac_double);
    }

    public static String calcBAC(String gender, String oz, String weight, String hours_since) {
        int oz_int = Integer.parseInt(oz);
        int weight_int = Integer.parseInt(weight);
        int hours_int = Integer.parseInt(hours_since);

        if (gender.equals("1")) {
            double bac = (oz_int * 5.14/weight_int * 0.73) - (0.015 * hours_int);
            return String.valueOf(bac);
        }
        else if (gender.equals("2")) {
            double bac = (oz_int * 5.14/weight_int * 0.66) - (0.015 * hours_int);
            return String.valueOf(bac);
        }
        return "";
    }

    public static boolean isNum(String input) {
        String regex = "[0-9]+";
        return Pattern.matches(regex, input);
    }

    public static String getHoursSinceLast() {
        System.out.print("How many hours has it been since your last drink? ");
        return in.nextLine();
    }

    public static String getWeight() {
        System.out.print("What is your weight, in pounds? ");
        return in.nextLine();
    }

    public static String getOunces() {
        System.out.print("How many ounces of alcohol did you have? ");
        return in.nextLine();
    }

    public static String getGender() {
        System.out.print("Enter a 1 if you are male and a 2 if you are female: ");
        return in.nextLine();
    }
}
