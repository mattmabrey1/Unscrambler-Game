package com.jetbrains;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

/*
   Matthew Mabrey CSC-250 Professor Niroomand
   Project #2 Word Unscrambler Game
   This project uses Main class and one additional class, "UnscramblerGame", to allow the user to
   play a word unscrambling game. The goal of the game is to guess a randomly chosen scrambled word.
   The user is presented a menu with 3 choices (swap letters, solve word, quit).
   The user has unlimited swaps to try to guess the word and if successful will print how many tries it took.
*/

public class Main {

    public static void main(String[] args) throws IOException {


        FileInputStream fis = new FileInputStream("C:\\Users\\m_mab\\IdeaProjects\\Project2\\src\\com\\jetbrains\\words.txt");
        Scanner inFileScan = new Scanner(fis);
        Scanner sysInScan = new Scanner(System.in);

        Random ran = new Random();          //Generate a random number between 0-109
        int r = ran.nextInt(110);   //because the words.txt has 110 lines in it
        boolean quit = false;


        for(int i = 0; i < r; i++)
        {
            inFileScan.next();              //Scan in lines 'r' number of times positioning the scanner to a random line
        }

        //Scanner now inputs the randomly chosen line as the random word
        UnscramblerGame game = new UnscramblerGame(inFileScan.next());

        fis.close();        //close FileInputStream
        inFileScan.close();     //close FileInputStream Scanner

        game.scrambleWord();  //scrambles the word before the game begins

        do{

            game.printMenu();
            quit = game.processInput(sysInScan);

        }while(!quit);      //keep printing the menu and processing input until quit is true



        sysInScan.close();
    }



}
