package TestHelpers;

import javax.sound.midi.Soundbank;
import java.util.*;

public class Run {

    public static void main(String[] args) {

//        System.out.println(getFullPrice("$35.90"));
//        System.out.println(changeStringPercentToDouble("-20%"));

//        List<Integer> abc = new ArrayList<>();
//        abc.add(1);
//        abc.add(2);
//        abc.add(3);
//        abc.add(4);
//        System.out.println(abc.size());

        List<String> axc = new ArrayList<>();
        axc.add("MUG TODAY IS A GOOD DAY");
        axc.add("THE ADVENTURE POSTER");
        axc.add("TODAY POSTER");
        axc.add("THE ADVENTURE POSTER");
        axc.add("BROWN BEAR CUSHION");

        System.out.println(axc.contains("BROWN BEAR CUSHION"));

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
