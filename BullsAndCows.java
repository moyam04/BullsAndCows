//Manuel Moya Valdivia 260510582

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class BullsAndCows{

    public static void main(String[] args) {

        playBullsAndCows((int) (Math.random()*5500));
    }

    //Game
    public static void playBullsAndCows(int insertCoin){

    //====Calling scanner, setting number of guesses to 1 and getting the secret array
        Scanner gameInput = new Scanner(System.in);
        int numOfGuesses = 1;
        final int[] secretDigits = generateSecretDigits(insertCoin);


    //Welcome banner, invoking the printMessage method
        printMessage("banner",1);
        System.out.print("For instructions, please enter your name: ");
        String name = gameInput.nextLine();
        printMessage(name,2);

        int bulls = 0;
        int cows;

        while (bulls != 4){

    //After 5 attempts, asking the player if they want to quit after every turn.
            if(numOfGuesses>5){
                System.out.println();
                System.out.print("Do you want to quit? y/n: ");
                String answer = gameInput.nextLine();
                if (answer.charAt(0) == 'y') {
                    System.out.println();
                    System.out.println("The secret number is " + Arrays.toString(secretDigits));
                    printMessage(""+numOfGuesses, 10);
                    bulls=4;
                    break;
                }
            }
    //Getting the player's input and catching IllegalArguments
            System.out.print("Guess #" + numOfGuesses + ", Enter a four-digit number: ");
            String guess = gameInput.nextLine();


            try {
                guessArray(guess);
                int[] returnedGuessArray = guessArray(guess);
            }
            catch (NumberFormatException a){
                System.out.println("Numbers! Not letters!");
                System.out.println("You just wasted a turn!");
                System.out.println();

                numOfGuesses++;
                continue;
            }

            catch (IllegalArgumentException a){
                System.out.println("Your guess must be positive.");
                System.out.println();

                numOfGuesses++;
                continue;
            }

            try {
                guessArray(guess);
                int[] returnedGuessArray = guessArray(guess);
                getNumOfBulls(secretDigits,returnedGuessArray);
            }

            catch (IllegalArgumentException a){
                System.out.println("It must be a 4-digit number. Try again.");
                System.out.println();

                numOfGuesses++;
                continue;
            }

            try{
                guessArray(guess);
                int[] returnedGuessArray = guessArray(guess);
                getNumOfCows(secretDigits,returnedGuessArray);
            }

            catch (IllegalArgumentException b){
                System.out.println("Each digit should be unique.");
                System.out.println();

                numOfGuesses++;
                continue;
            }

    //If no arguments are raised, continue with game.
            int[] returnedGuessArray = guessArray(guess);
            cows = getNumOfCows(secretDigits,returnedGuessArray);
            bulls = getNumOfBulls(secretDigits,returnedGuessArray);

            System.out.println("Bulls: " + bulls);
            System.out.println("Cows: " + cows);


    //The following conditions will trigger different messages to make the game more interactive.
    //All conditions below invoke the printMessage method.
            if(cows==0 && bulls == 0){
                printMessage("message",3);
            }
            if(cows==3){
                printMessage("message",4);
            }
            if(cows==4){
                printMessage("message",5);
            }
            if(bulls==2 && numOfGuesses>=5){
                printMessage(""+numOfGuesses,6);
            }
            if(bulls==2 && numOfGuesses<5){
                printMessage("message",7);
            }
            if(bulls==3){
                printMessage("message",8);
            }
            if(bulls == 4){
                printMessage(""+numOfGuesses,9);
            }

            System.out.println();
            numOfGuesses++;
        }

    //I included these block of code so that the game does not have to be run from the start again.
    //If input is 'y', the program will generate a new random and loop itself.
        System.out.println();
        System.out.print("Would you like to play again? y/n: ");
        String anotherGame = gameInput.nextLine();

            if (anotherGame.charAt(0)=='y')
                playBullsAndCows((int) (Math.random() * 5500));

        gameInput.close(); //<<--Closing scanner

    }
    //Additional method 1
    //Method to convert the player's guess into array
    //Because of difficulties with leading zeroes in Integer, the input must be String first.
    public static int [] guessArray(String guess) throws NumberFormatException{

    //Trying and catching exceptions in case the player inputs letters signs
        try {
            int test= Integer.parseInt(guess);
        }
        catch (NumberFormatException a){
            throw new NumberFormatException ("Numbers! Not letters!");
        }

        int digitGuess = Integer. parseInt(guess);

    //Throwing exception for negative numbers
        if (digitGuess<0)
            throw new IllegalArgumentException("Your guess cannot be negative.");

    //If exceptions are not met, generate guessArray
        int []guessArray = new int[guess.length()];

            for (int j = guess.length(); j>0; j--){
                guessArray[j-1] = digitGuess%10;
                digitGuess = digitGuess/10;
        }
            return guessArray;

    }

    //Additional method 2
    //Method to determine if digits are repeated
    //The input is the player's guess in array form and loops itself, adding 1 to "int count" each time it finds itself.
    //Arithmetically, each element should find itself only once per iteration.
    //Therefore, if count>4, at least one digit has been repeated.
    //This method will be invoked in getBulls and getCows
    public static int repeatedDigits(int[] array){
    int count = 0;
        for (int k : array) {
            for (int i : array) {
                if (k == i) {
                    count++;
                }
            }
        }
            return count;
    }

    //Additional method 4
    //Method to print messages
    public static void printMessage(String s, int x){

        switch (x){
            case 1:
                System.out.println();
                for (int i = 0; i<50; i ++){
                            System.out.print("* ");
                }
                System.out.println("");
                System.out.println("   ####     ##  ##   ##     ##      ####      ##   ##       ####     ####     ##      ##     ####");
                System.out.println("   ##  ##   ##  ##   ##     ##    ##          ###  ##     ##       ##    ##   ##      ##   ##");
                System.out.println("   ####     ##  ##   ##     ##      ####      ## # ##     ##       ##    ##   ##  ##  ##     ####");
                System.out.println("   ##  ##   ##  ##   ##     ##        ##      ##  ###     ##       ##    ##   ##  ##  ##       ##");
                System.out.println("   ####      ####    ####   ####  ####        ##   ##       ####     ####       ##  ##     ####");
                for (int i = 0; i<50; i ++){
                    System.out.print("* ");
                }
                System.out.println();
                System.out.println();break;

            case 2:
                System.out.println();
                System.out.println("Hi, "+ s + ".");
                System.out.println("Welcome to Bulls and Cows. The only game where you need a lotta bull!");
                System.out.println();
                System.out.println("INSTRUCTIONS:");
                System.out.print("\tA positive four-digit number has been generated. Each digit in the number is unique." );
                System.out.println(" Examples: 1245, 0468, 9180" );
                System.out.println("\tYou'll be asked to guess this number. For each digit you guess in the exact location, you'll score one bull.");
                System.out.print("\tYou'll score a cow if the digit is not in the exact location. ");
                System.out.println("Got it?" + " Let's get some bull!");
                System.out.println();break;

            case 3:
                System.out.println("Well, that's disappointing."); break;
            case 4:
                System.out.println("Holy cow!");break;
            case 5:
                System.out.println("You've rounded your cattle!");break;
            case 6:
                System.out.println("I would be impressed if this wasn't attempt number "+s+"!");break;
            case 7:
                System.out.println("Keep it going!");break;
            case 8:
                System.out.println("That's a lotta bull! I can smell it from here.");break;
            case 9:
                System.out.println("Yeeeeeha! Bullseye!");
                System.out.println("You guessed the secret number after " +s+ " attempts.");
                System.out.println("Thank you for playing.");break;
            case 10:
                System.out.println("Better luck next time.");
                System.out.println("Number of attempts: "+s);
                System.out.println("Thank you for playing.");break;
        }

    }


    //Method 1a
    public static boolean contains(int [] integers, int integer){
        boolean result = false;
        for(int i: integers){
            if (integer == i) {
                result = true;
            }
        }
        return result;
    }


    //Method 1b
    public static int[] generateSecretDigits(int seed){

        Random random1 = new Random(seed);
        int[]secretNumberArray = new int[4];

        //<<-----these integers are created for cleaner coding for the while loop conditions
        int num0 = 0;
        int num1 = 0;
        int num2 = 0;
        //<<-----these integers are created for cleaner coding for the while loop conditions

        //Assigning first and second indexes
        while (secretNumberArray[0]== secretNumberArray[1]){
            secretNumberArray[0] = random1.nextInt(10);
            secretNumberArray[1] = random1.nextInt(10);
        }

        //Assigning third index
        num0 = secretNumberArray[0];
        num1 = secretNumberArray[1];
        secretNumberArray[2] = random1.nextInt(10);
        while (num0 == secretNumberArray[2] || num1 == secretNumberArray[2]){
            secretNumberArray[2] = random1.nextInt(10);
        }

        //Assigning last index
        num2 = secretNumberArray[2];
        secretNumberArray[3] = random1.nextInt(10);
        while (num0 == secretNumberArray[3] || num1 == secretNumberArray[3] || num2 == secretNumberArray[3]){
            secretNumberArray[3] = random1.nextInt(10);
        }
        return secretNumberArray;
    }

    //method c
    public static int getNumOfBulls(int[]secret, int[] guess)throws IllegalArgumentException {

    //Throwing exception if the length of the array is not 4.
        if (guess.length != 4)
            throw new IllegalArgumentException ("It must be a 4-digit number");

        int bulls = 0;

        for(int i = 0; i<4; i++){
                if (secret[i]==guess[i])
                    bulls ++;
        }
        return bulls;

    }


    //method d
    public static int getNumOfCows(int[]secret, int[] guess) throws IllegalArgumentException{

    //Throwing exception if there are digits in the array that are repeated, invoking repeatedDigits method.
        if (repeatedDigits(guess)>4)
            throw new IllegalArgumentException ("Each digit must be unique");

        int sum = 0;
        for(int i = 0; i < guess.length; i++){
            if (contains(guess, secret[i]))
                sum++;
        }

        return sum - getNumOfBulls(secret,guess);
    }

}