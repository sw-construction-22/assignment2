package Materials.Dice;

public class DiceRoll {
    public static void main(String[] args) {
        int[] aDice = new int [] { 0, 0, 0, 0, 0, 0};// creates an array
        int roll =0;
        Dice die = new Dice();
        int x,y,w;
        int rerolla = 0, rerollb = 0;
        for (x = 0; x < 6; x++) {
            die.roll();
            aDice[x]= die.getValue(); // sets the dice values
        }


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
    }
    static int inputInt(String Prompt) {
        int result = 0;
        try {
            result = Integer.parseInt(input(Prompt).trim());
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }
    static String input(String prompt) {
        String inputLine = "";
        System.out.print(prompt);
        try {
            java.io.InputStreamReader sys = new java.io.InputStreamReader(
                    System.in);
            java.io.BufferedReader inBuffer = new java.io.BufferedReader(sys);
            inputLine = inBuffer.readLine();
        } catch (Exception e) {
            String err = e.toString();
            System.out.println(err);
        }
        return inputLine;
    }
}
