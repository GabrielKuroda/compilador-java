import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Compiler {

    static final List<Character> ALPHABET = Arrays.asList('*','+','-','/','%','=','>','<','!','|','&', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'k', 'l', 'n', 'o', 'r', 's', 't', 'u', 'v', 'w', 'z');

    static final String[][]  RULES = {
            {"0F"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
            {" ","0F"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
            {" "," ","0F"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
            {" "," "," ","0F"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," ","0F"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," ","0F"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," ","0F"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," ","0F"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," ","0F"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," ","0F"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," ","0-1F"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," ","0-1F"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
            {" ","0-1F"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
            {" "," ","0-1F"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," ","0-1F"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," ","0","1F"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
            {" "," ","0"," "," ","1F"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
            {" ","0"," "," "," ","1F"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
            {"0"," "," "," "," ","1F"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
            {" "," "," ","0"," ","1F"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "," "," "," ","2","5","6F"," "," "," ","0"," ","3","1"," "," "," "," ","4"," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "," "," "," "," ","0","1-5F","2"," "," ","3"," "," ","4"," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","0"," "," "," "," "," "," "," ","3","1-2-4F"," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","1F"," "," ","0"," "," "," "," "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","0-3F"," "," "," "," "," ","1"," "," "," ","2"," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "," "," "," "," ","3F"," "," "," "," ","2"," "," "," ","1"," "," "," "," ","0"," "," "},
            {" "," "," "," "," "," "," "," "," "," "," ","2"," ","0"," "," "," "," ","1"," "," "," "," "," ","3F"," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","0"," "," ","1"," "," "," ","2F"," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "," ","3"," "," "," "," ","0"," "," "," "," ","1"," ","2"," "," ","4F"," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "," "," ","3"," ","0","5F"," ",""," "," "," ","4"," ","1"," "," "," ","2"," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "," ","3","0"," "," ","2"," "," "," "," ","4F"," "," "," ","1"," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "," ","1"," ","0"," ","3F"," "," "," "," "," "," "," "," "," ","2"," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "," "," "," ","0"," "," "," "," "," "," "," "," ","2","1"," ","3","4F"," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "," "," "," ","0"," ","7F"," "," "," ","4"," "," ","2-5","1"," "," ","3","6"," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","0"," "," "," "," "," ","1-3F"," "," ","2"," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","3F"," "," "," ","0","2","1"," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","1"," "," "," "," "," "," ","5F"," ","0-4"," ","2","3"," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","3","5F"," "," ","1"," "," "," ","4"," ","0"," "," "," "," ","2"},
            {" "," "," "," "," "," "," "," "," "," "," ","2"," ","5F"," "," "," "," "," ","4"," "," "," "," "," ","0","1-3"," "," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "," "," "," ","4"," "," "," "," "," "," "," "," "," "," ","2","0","1-5F","3"," "," "," "},
            {" "," "," "," "," "," "," "," "," "," "," "," "," ","4"," "," "," "," ","5F","2"," "," "," "," "," ","0","3"," "," ","1"," "},
            {" "," "," "," "," "," "," "," "," "," "," "," "," "," ","3F"," "," "," "," ","2"," "," "," ","1"," "," "," "," ","0"," "," "},
            {" "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","4F"," "," ","1","2"," ","3"," "," "," "," "," "," "," ","0"," "}
    };

    static Integer index = 1;
    static String input;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite 'sair' para sair");
        while (true) {
            index = 1;
            System.out.print("Digite uma palavra: ");
            input = scanner.nextLine();
            while (input.equals("")){
                System.out.println("Por favor, digite uma palavra v??lida");
                System.out.print("Digite uma palavra: ");
                input = scanner.nextLine();
            }

            if(input.equalsIgnoreCase("sair")) {
                break;
            }

            if (getPositions(input.charAt(0))) {
                System.out.println("Palavra v??lida");
            } else {
                System.out.println("Palavra inv??lida");
            }
        }
        scanner.close();
    }

    public static boolean getPositions(char initial) {
        Integer initialPosInAlpha = null;
        List<Integer> posInRules = new ArrayList<>();

        for(int i = 0; i < ALPHABET.size(); i++) {
            if(initial == ALPHABET.get(i)) {
                initialPosInAlpha = i;
                break;
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

    public static boolean validateChars(List<Integer> receivedPos, char character, int wordLength) {
        Integer initialPosInAlpha = null;
        List<Integer> posInRules = new ArrayList<>();
        for(int i = 0; i < ALPHABET.size(); i++){
            if(character == ALPHABET.get(i)){
                initialPosInAlpha = i;
                break;
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
        if(index+1 != wordLength) {
            index++;
            expected = validateChars(posInRules, input.charAt(index), wordLength);
        }

        return expected;
    }
}
