import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int numErrors = 0;
        String[][] hangman = {
                {"  _______"},
                {" |      |"},
                {" |       "},
                {" |       "},
                {" |       "},
                {"¯¯¯¯"}
        };

        Scanner scan = new Scanner(System.in);
        System.out.print("Input a word or phrase: ");
        String input = scan.nextLine();
        String[] inputArr = new String[input.length()];
        for (int i = 0; i < input.length(); i ++ ) {
            inputArr[i] = input.substring(i, i + 1).toLowerCase();
        }
        String[] guesses = new String[inputArr.length];
        for (int i = 0; i < guesses.length; i ++) {
            if (inputArr[i].equals(" ")) {
                guesses[i] = " ";
            } else {
                guesses[i] = "_";
            }
        }

        while (!Arrays.equals(guesses, inputArr)) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            for (String[] hang : hangman) {
                for (String man : hang) {
                    System.out.print(man);
                }
                System.out.println();
            }
            for (String letter : guesses) {
                System.out.print(letter);
            }
            System.out.print("\nGuess a letter or full phrase:");
            String guess = scan.nextLine();
            if (input.toLowerCase().contains(guess.toLowerCase())) {
                for (int i = 0; i < input.length(); i ++) {
                    if (inputArr[i].equalsIgnoreCase(guess)) {
                        guesses[i] = guess;
                    }
                }
                if (input.equalsIgnoreCase(guess)) {
                    guesses = inputArr;
                    break;
                }
                
            } else {
                numErrors ++;
                String[] str = new String[1];
                if (numErrors == 1) {
                    str[0] =  " |      ◯";
                    hangman[2] = str;
                } else if (numErrors == 2) {
                    str[0] =  " |      |";
                    hangman[3] = str;
                } else if (numErrors == 3) {
                    str[0] =  " |     /|";
                    hangman[3] = str;
                } else if (numErrors == 4) {
                    str[0] = " |     /|\\";
                    hangman[3] = str;
                } else if (numErrors == 5) {
                    str[0] = " |     /";
                    hangman[4] = str;
                } else if (numErrors == 6) {
                    str[0] = " |     / \\";
                    hangman[4] = str;
                    guesses = inputArr;
                    break;
                }
            }
        }
        System.out.print("\nThe word was: ");
        for (String letter : guesses) {
            System.out.print(letter);
        }
        System.out.println();
        if (numErrors == 6) {
            for (String[] hang : hangman) {
                for (String man : hang) {
                    System.out.print(man);
                }
                System.out.println();
            }
            System.out.println("\nToo bad, you lose!");
        }
        System.out.print("\nCongratulations, You win!");

    }
}
