import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Compiler {

    static final List<Character> ALPHABET = Arrays.asList('*','+','-','/','%','=','>','<','!','|','&', 'i', 'n', 'c', 'l', 'u', 'd', 'e');

    static final String[][]  RULES = {
            {"0F"," "," "," "," "," "," "," "," "," "," ", " "," "," "," "," "," "," "},
            {" ","0F"," "," "," "," "," "," "," "," "," ", " "," "," "," "," "," "," "},
            {" "," ","0F"," "," "," "," "," "," "," "," ", " "," "," "," "," "," "," "},
            {" "," "," ","0F"," "," "," "," "," "," "," ", " "," "," "," "," "," "," "},
            {" "," "," "," ","0F"," "," "," "," "," "," ", " "," "," "," "," "," "," "},
            {" "," "," "," "," ","0F"," "," "," "," "," ", " "," "," "," "," "," "," "},
            {" "," "," ",""," "," ","0F"," "," "," "," ", " "," "," "," "," "," "," "},
            {" "," "," ",""," "," "," ","0F"," "," "," ", " "," "," "," "," "," "," "},
            {" "," "," ",""," "," "," "," ","0F"," "," ", " "," "," "," "," "," "," "},
            {" "," "," ",""," "," "," "," "," ","0F"," ", " "," "," "," "," "," "," "},
            {" "," "," ",""," "," "," "," "," "," ","0-1F", " "," "," "," "," "," "," "},
            {" "," "," ",""," "," "," "," "," ","0-1F"," ", " "," "," "," "," "," "," "},
            {" ","0-1F"," ",""," "," "," "," "," "," "," ", " "," "," "," "," "," "," "},
            {" "," ","0-1F",""," "," "," "," "," "," "," ", " "," "," "," "," "," "," "},
            {" "," "," ",""," ","0-1F"," "," "," "," "," ", " "," "," "," "," "," "," "},
            {" "," "," "," ","0","1F"," "," "," "," "," ", " "," "," "," "," "," "," "},
            {" "," ","0"," "," ","1F"," "," "," "," "," ", " "," "," "," "," "," "," "},
            {" ","0"," "," "," ","1F"," "," "," "," "," ", " "," "," "," "," "," "," "},
            {"0"," "," "," "," ","1F"," "," "," "," "," ", " "," "," "," "," "," "," "},
            {" "," "," ","0"," ","1F"," "," "," "," "," ", " "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "," ","0","1","2","3","4","5","6F"}
    };

    static Integer index = 1;
    static String input;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            index = 1;
            System.out.print("Digite uma palavra: ");
            input = scanner.nextLine();
            if (getPositions(input.charAt(0))) {
                System.out.println("Sucesso");
            } else {
                System.out.println("Falha");
            }
        }
    }

    public static boolean getPositions(char initial) {
        Integer initialPosInAlpha = null;
        List<Integer> posInRules = new ArrayList<>();

        for(int i = 0; i < ALPHABET.size(); i++) {
            if(initial == ALPHABET.get(i)) {
                initialPosInAlpha = i;
            }
        }

        if(initialPosInAlpha == null) {
            return false;
        }

        for(int i = 0; i < RULES.length; i++) {
            String rules = RULES[i][initialPosInAlpha];

            if(input.length() != index) {
                if(rules.contains("0")) {
                    posInRules.add(i);
                }
            }
            else {
                if(rules.contains("0F")) {
                    return true;
                }
            }
        }

        if(posInRules.isEmpty()){
            return false;
        }

        return validateChars(posInRules, input.charAt(index), input.length());
    }

    public static boolean validateChars(List<Integer> receivedPos, char character, int wordLenght) {
        Integer initialPosInAlpha = null;
        List<Integer> posInRules = new ArrayList<>();
        for(int i = 0; i < ALPHABET.size(); i++){
            if(character == ALPHABET.get(i)){
                initialPosInAlpha = i;
            }
        }

        if(initialPosInAlpha == null) {
            return false;
        }

        for(int i = 0; i < receivedPos.size(); i++) {
            String rules = RULES[receivedPos.get(i)][initialPosInAlpha];

            if(input.length() != index+1) {
                if(rules.contains(index.toString())) {
                    posInRules.add(receivedPos.get(i));
                }
            } else {
                if(rules.contains(index.toString().concat("F"))){
                    return true;
                }
            }
        }

        if(posInRules.isEmpty()){
            return false;
        }

        boolean expected = false;
        if(index+1 != wordLenght) {
            index++;
            expected = validateChars(posInRules, input.charAt(index), wordLenght);
        }

        return expected;
    }
}
