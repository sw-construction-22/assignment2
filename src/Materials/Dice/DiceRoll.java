package Materials.Dice;

public class DiceRoll {
    public static void main(String[] args) {
        /**
         * Two things to comment on here:
         * - the array
         * - the array of integers
         *
         * 1) working with arrays is one option, however, in the parameters we have defined to use lists of dice
         * hence this line could look something like this
         * new ArrayList<>(Arrays.asList( new Dice(),  new Dice(),  new Dice(),  new Dice(),  new Dice(),  new Dice()));
         *
         * 2) in the integer array you are basically saving the values 0,0,0.., we have an element "Dice"
         * which is able to perform operations and do stuff. If you wanted to do it via an array you could have done it in this way
         * Dice[] q = new Dice[]{new Dice(),new Dice(),new Dice(),new Dice(),new Dice()};
         */
        int[] aDice = new int [] { 0, 0, 0, 0, 0, 0};// creates an array
        int roll =0;
        Dice die = new Dice();
        int x,y,w;
        int rerolla = 0, rerollb = 0;
        /**
         * try to avoid hard coded values in the for loop we have 6 dice, a better solution would be to define a constant
         * private final int NUMBER_OF_DICES = 6;
         * like this if the number of dice changes from 6 to 10 for whatever reason, you only have to change it at 1 place
         *
         * also you can initialize the variable in the for-loop itself
         * for (int x = 0; x < NUMBER_OF_DICE; x++)
         *
         * addition: if you used the list mentioned above, you could use a for each loop. This loop iterates through all the items in
         * the list
         * for (Dice diceFromList : diceList){
         *     diceFromList.roll()
         *  }
         *  like this you would have a list of rolled dice instead of rolling one dice and saving the value as int array
         */
        for (x = 0; x < 6; x++) {
            die.roll();
            aDice[x]= die.getValue(); // sets the dice values
        }


        /**
         * this output could get into the for loop before, and then you could replace the hard coded number with x and would
         * get the same result
         */
        System.out.println("Die 1: " + aDice[0]);
        System.out.println("Die 2: " + aDice[1]);
        System.out.println("Die 3: " + aDice[2]);
        System.out.println("Die 4: " + aDice[3]);
        System.out.println("Die 5: " + aDice[4]);
        System.out.println("Die 6: " + aDice[5]);

        do {
            rerolla = inputInt("How many dice you want to reroll? (0-6)"); // type the number of dice you want to reroll
            if (rerolla>0) {
                int[] reroll = new int[rerolla];
                for (y=0; y<rerolla;y++){
                    /** i assume you are using indices 0-5 for the input values here, not sure if this would be the correct approach, because as a user
                     * I would say I want die 1,2,5 and not 0,1,4
                     *
                     * again input validation is needed */
                    rerollb=inputInt("Which ones?"); // give the number of one die and then it will repeat itself as many times as the number of die you said you want to reroll
                    reroll[y]=rerollb;
                }
                for (w = 0; w < rerolla; w++) {
                    if (reroll[w] == 1) {
                        die.roll();
                        aDice[0] = die.getValue();
                    }
                    if (reroll[w] == 2) {
                        die.roll();
                        aDice[1] = die.getValue();
                    }
                    if (reroll[w] == 3) {
                        die.roll();
                        aDice[2] = die.getValue();
                    }
                    if (reroll[w] == 4) {
                        die.roll();
                        aDice[3] = die.getValue();
                    }
                    if (reroll[w] == 5) {
                        die.roll();
                        aDice[4] = die.getValue();
                    }
                    if (reroll[w] == 6) {
                        die.roll();
                        aDice[5] = die.getValue();
                    }
                }
                roll++;
                System.out.println("Die 1: " + aDice[0]);
                System.out.println("Die 2: " + aDice[1]);
                System.out.println("Die 3: " + aDice[2]);
                System.out.println("Die 4: " + aDice[3]);
                System.out.println("Die 5: " + aDice[4]);
                System.out.println("Die 6: " + aDice[5]);
            }
        }while ((roll<3)&&(rerolla>0)); //roll<3 means that the player has a maximum of 3 rerolls
        /** does the user only have 3 rerolls? This is not mentioned in the rule set */
    }

    /**
     * The parameter which gets passed into the method should always be in lower case / camel case
     * Upper case at the beginning is for classes
     */
    static int inputInt(String Prompt) {
        int result = 0;
        try {
            result = Integer.parseInt(input(Prompt).trim()); /** not sure whether this is the best approach I would not further encapsulate this into another func (comment below) */
        } catch (Exception e) {
            /**
             * dont do that. in case the user enters some random bs we need to provide a solution, e.g. if the user can
             * enter a new number or something
             *
             * also, what if the user wants to re-roll 28 dice? we need some cap. it is not possible to roll more than he has
             */
            result = 0;
        }
        return result;
    }
    static String input(String prompt) {
        String inputLine = "";
        System.out.print(prompt);
        try { /** input stream readers are a pain in the ass, I would suggest using a scanner and then you have the method scanner.nextInt() */
            java.io.InputStreamReader sys = new java.io.InputStreamReader(
                    System.in);
            java.io.BufferedReader inBuffer = new java.io.BufferedReader(sys);
            inputLine = inBuffer.readLine();
        } catch (Exception e) {
            /** validation is required, meaning the user should be able to re enter the stuff (once you write test cases you can see this) */
            String err = e.toString();
            System.out.println(err);
        }
        return inputLine;
    }
}
