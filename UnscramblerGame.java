package com.jetbrains;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


//Unscrambler Class that has all of the game methods for scrambling the word, processing input,
// swapping letter indexes, printing menu, and checking the game over conditions

public class UnscramblerGame {

    private String unscrambledWord;
    private ArrayList<Character> scrambledWord;
    private int wordLength;
    private int numberOfSwaps;  //Index to keep track of how many guesses it takes them to solve the word


    public UnscramblerGame(String word){

        unscrambledWord = word;
        wordLength = word.length();
        numberOfSwaps = 0;
        scrambledWord = new ArrayList<>();

        for(int i = 0; i < wordLength; i++){
            scrambledWord.add(word.charAt(i)); //Converts the String word to an ArrayList<Character> for easy index swapping
        }


    }

    //Method that randomly scrambles the word by swapping two random indexes 100 times
    public void scrambleWord(){

        Random rand = new Random();

        for(int i = 0; i < 100; i++)
        {
            int r1 = rand.nextInt(wordLength);  //get random index 1
            int r2 = rand.nextInt(wordLength);  //get random index 2

            char temp = scrambledWord.get(r1);
            scrambledWord.set(r1, scrambledWord.get(r2));   // swap Characters at index 1 and index 2
            scrambledWord.set(r2, temp);

        }

    }

    //Method that returns the ArrayList of Characters as a string because the toString method includes '[', ']', and ','
    public String scrambledWordToString(){

        String scrambledString = scrambledWord.toString();

        scrambledString = scrambledString.replaceAll(", ", "").substring(1, wordLength + 1);
        //toString of ArrayList<Character> returns [c, h, a, r, a, c, t, e, r, s] so it must be modified

        return scrambledString;
    }

    //Method that prints the scrambled word, the indexes of each letter, and the menu options
    public void printMenu(){

        for(int i = 0; i < wordLength + 2; i++){
            System.out.print("-");          //print the bar that encloses the indexes and scrambled  word
        }

        System.out.println();
        System.out.print(" ");

        for(int i = 0; i < wordLength; i++){
            System.out.print(i);            //print word indexes
        }

        System.out.println();
        System.out.println(" " + scrambledWordToString());      //print scrambled word


        for(int i = 0; i < wordLength + 2; i++){
            System.out.print("-");      //print the bar that encloses the indexes and scrambled  word
        }


        System.out.println();
        System.out.println();

        System.out.println("Enter 1 to swap letters.");
        System.out.println("Enter 2 to solve.");
        System.out.println("Enter 3 to quit.");
        System.out.println();

    }

    //Method that processes the menu and index choices of the user and returns a boolean 'quit'= true if the game is over
    //There is error checking for invalid menu choice
    public boolean processInput(Scanner scan){

        int choice = scan.nextInt();
        int index1;
        int index2;
        boolean quit = false;


        switch (choice){

            case 1: // Option 1 Swap word
                System.out.println("Enter the indexes separated by spaces");
                index1 = scan.nextInt();
                index2 = scan.nextInt();

                swapLetters(index1, index2);

                if(areWordsEqual()){           //If the unscrambledWord == scrambledWord the user wins and game is over
                    gameOverMsg(true);   //call gameOverMsg with true because they correctly guessed word and won
                    quit = true;
                }
                break;

            case 2: // Option 2 Solve Word
                gameOverMsg(false);     //call gameOverMsg with false because they clicked solve and lost
                quit = true;
                break;
            case 3: // Option 3 Quit
                quit = true;
                break;
            default: //A number that wasn't 1-3 was input
                System.out.println("Invalid menu choice. Try again.");
                break;
        }

        return quit;
    }

    //Method that swaps letters at the indexes input by the user. Error checking for invalid indexes
    public void swapLetters(int index1, int index2){

        //if the indexes are within bounds swap them and increase numberOfSwaps counter
        if(index1 >= 0 && index1 < wordLength && index2 >= 0 && index2 < wordLength){

            char temp = scrambledWord.get(index1);
            scrambledWord.set(index1, scrambledWord.get(index2));
            scrambledWord.set(index2, temp);

            numberOfSwaps++;
        }
        else
        {
            System.out.println("Invalid swap indexes. Try again.\n");

        }
    }

    //Method that returns true if the unscrambled word and scrambled word are now equal after swaps
    public boolean areWordsEqual(){

        boolean wordsEqual = false;

        if(unscrambledWord.equals(scrambledWordToString()))
        {
            wordsEqual = true;
        }

        return wordsEqual;
    }

    //Method that displays a game over message based on if they won or if they just chose the "solve" option
    public void gameOverMsg(boolean won){

        if(won) {
            System.out.println("Congratulations! You unscrambled the word " + unscrambledWord + " in " + numberOfSwaps + " steps.");
        }
        else{
            System.out.println("Game Over! The word to solve was: " +  unscrambledWord);
        }

    }

}
