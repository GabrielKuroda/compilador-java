import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Compiler {

    static final List<Character> ALPHABET = Arrays.asList('*','+','-','/','%','=','>','<','!','|','&');

    static final String[][]  RULES = {
            {"1F"," "," "," "," "," "," "," "," "," "," "},
            {" ","1F"," "," "," "," "," "," "," "," "," "},
            {" "," ","1F"," "," "," "," "," "," "," "," "},
            {" "," "," ","1F"," "," "," "," "," "," "," "},
            {" "," "," "," ","1F"," "," "," "," "," "," "},
            {" "," "," "," "," ","1F"," "," "," "," "," "},
            {" "," "," ",""," "," ","1F"," "," "," "," "},
            {" "," "," ",""," "," "," ","1F"," "," "," "},
            {" "," "," ",""," "," "," "," ","1F"," "," "},
            {" "," "," ",""," "," "," "," "," ","1F"," "},
            {" "," "," ",""," "," "," "," "," "," ","1-2F"},
            {" "," "," ",""," "," "," "," "," ","1-2F"," "},
            {" ","1-2F"," ",""," "," "," "," "," "," "," "},
            {" "," ","1-2F",""," "," "," "," "," "," "," "},
            {" "," "," ",""," ","1-2F"," "," "," "," "," "},
            {" "," "," "," ","1","F"," "," "," "," "," "},
            {" "," ","1"," "," ","F"," "," "," "," "," "},
            {" ","1"," "," "," ","F"," "," "," "," "," "},
            {"1"," "," "," "," ","F"," "," "," "," "," "},
            {" "," "," ","1"," ","F"," "," "," "," "," "},
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite um texto: ");
        String input = scanner.nextLine();
        scanner.close();
        List<Integer> positions = getPositions(input.charAt(0));
        for(int indexPos = 0; indexPos < positions.size(); indexPos++){
            for (int indexRule = 0; indexRule < RULES[positions.get(indexPos)].length; indexRule++){
                for(int indexInput = 0; indexInput < input.length(); indexInput ++){

                }
            }
        }
    }

    public static List<Integer> getPositions(char initial){
        int initialPosInAlpha = 0;
        List<Integer> posInRules = new ArrayList<>();
        for(int i = 0; i < ALPHABET.size(); i++){
            if(initial == ALPHABET.get(i)){
                initialPosInAlpha = i;
            }
        }
        for(int i = 0; i < RULES.length; i++){
            String s = RULES[i][initialPosInAlpha];
            String[] split = s.split("-");
            if(split[0].equals("1") || split[0].equals("1F")){
                posInRules.add(i);
            }
        }
        return posInRules;
    }
}
