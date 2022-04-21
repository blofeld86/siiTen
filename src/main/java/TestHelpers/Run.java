package TestHelpers;

public class Run {

    public static void main(String[] args) {

        String x = "There are 2 products.";
        String[] s = x.split(" ");
        for (String a : s) { System.out.println(a);}
        getValueFromText(x,3);
    }

    public static int getValueFromText(String text,int word){
        String[] array= text.split(" ");
        return (Integer.parseInt(array[word])-1);
    }
}
