import java.util.HashMap;
import java.util.Scanner;

public class Hangman {

    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
    "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
    "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
    "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
    "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", 
    "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
    "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
    "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
    "wombat", "zebra"};

    public static String[] gallows = {"+---+\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|   |\n" +
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + //if you were wondering, the only way to print '\' is with a trailing escape character, which also happens to be '\'
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" +
    "/    |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + 
    "/ \\  |\n" +
    "     |\n" +
    " =========\n"};

    public static void main(String[] args) {

        /**
         * TO DO
         * Optimize code 
         * Remove extra comments
         * Add relevent comments 
         * Test code 
         */
        
        char guessedChar;
        String guessesWord = ""; 
        String missesString = "";
        boolean gameOver = false;
        int noWrongGuesses = 0;

        //select random word
        String targetString = randomWord();
        // create dictionary with key as letter of targetString and value as index
        HashMap<Character,Integer> hm = new HashMap<>();

        //this can also be a char array instead 
        String[] placeHolder = new String[targetString.length()]; 

        for(int i=0;i<targetString.length();i++){
            placeHolder[i]="_";
            hm.put(targetString.charAt(i), i);
        }
        Scanner scanner = new Scanner(System.in);
        printBlock(0,placeHolder,"");

        while(!gameOver){
            System.out.print("\nGuess:  ");
            guessedChar = scanner.next().charAt(0);
            String guessedStr = Character.toString(guessedChar);
            if(checkGuess(guessedStr,targetString)){
                
                //logic to put the guessedChar in the correct position and then convert it into a string--> use CharArray 
                placeHolder[hm.get(guessedChar)] = guessedStr;
                //this section has to be optimized 
                String temp = "";
                for(int i=0;i<placeHolder.length;i++){
                    temp+=placeHolder[i];
                }

                //if(targetString.equals(placeHolder.toString())) -> doesnt work 
                if(targetString.equals(temp)){
                    //print final stuff
                    System.out.println(gallows[noWrongGuesses]);
                    String tempStr="";
                    for(int i =0;i<targetString.length();i++){
                        tempStr = tempStr+ targetString.charAt(i) +" ";
                    }
                    System.out.println("Word:   "+tempStr);
                    System.out.println("\nGOOD WORK");
                    gameOver = true;
                }else{
                    //print updated place holder
                    printBlock(noWrongGuesses,placeHolder,missesString);
                }

            }else{
                noWrongGuesses++;
                if(noWrongGuesses == 6){
                    //print ending stuff 
                    System.out.println(gallows[noWrongGuesses]);
                    System.out.println("\nRIP !");
                    System.out.println("\nThe workd was: "+"'"+targetString+"'");
                    gameOver = true;
                }else{
                    missesString+=guessedChar;
                    //print updated place holder
                    printBlock(noWrongGuesses,placeHolder,missesString);
                }

            }

        }
        scanner.close();
        System.out.println("\nLearn The Part >>: ");

    }

    // returns a random word from the list of random words
   public static String randomWord(){
        int randomInt = (int)(Math.random()* words.length);
        return words[randomInt];
    }

    //checkGuess(): -> returns true if the user guessed a letter from the word correctly.
    public static boolean checkGuess(String guessedChar,String targetString){
        if(targetString.contains(guessedChar)){
            return true;
        }else{
            return false;
        }
        
    }

    public static void printBlock(int currGallow, String[] currWord, String missString){
        System.out.println(gallows[currGallow]);
        String tempStr="";
        for(int i =0;i<currWord.length;i++){
            tempStr = tempStr+ currWord[i] +" ";
        }
        System.out.println("Word:   "+tempStr);
        System.out.println("\nMisses:  "+missString);
    }

}





