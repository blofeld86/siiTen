package TestHelpers;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

public class Run {

    public static void main(String[] args) {

        System.out.println(getFullPrice("$35.90"));
        System.out.println(changeStringPercentToDouble("-20%"));
    }

    public static double changeStringPercentToDouble(String value){
        return getNumberFromString(value,0)/100.00;
    }

    public static double getFullPrice(String value){
        int first = getNumberFromString(value,0);
        int second = getNumberFromString(value,1);
        String result = Integer.toString(first)+"."+Integer.toString(second);
        return Double.parseDouble(result);
    }

    public static boolean verifyDiscount(double orgPrice, double actPrice, double discount){
        boolean result;
        if (actPrice == orgPrice * (1-discount)){ result = true;}
        else { result = false;}
        return result;
    }

    public static int getNumberFromString(String s, int positionOfNumber) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                String  num = "";
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num += s.charAt(i++);
                }
                list.add(Integer.parseInt(num));
            }
        }
        return list.get(positionOfNumber);
    }




    public static int getValueFromText(String text,int word){
        String[] array= text.split(" ");
        return (Integer.parseInt(array[word])-1);
    }
}
