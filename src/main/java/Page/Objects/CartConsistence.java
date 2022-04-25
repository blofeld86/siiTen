package Page.Objects;

import org.openqa.selenium.WebElement;

import java.util.*;

public class CartConsistence {

    public static List<CartConsistence> cartConsistenceList = new ArrayList<>();
    public static List<CartConsistence> verifyCartConsistenceList = new ArrayList<>();


    private String name;
    private double price;
    private int quantity;
    private double totalOrderCost;

    public CartConsistence(Builder cartConsistenceBuilder) {
        this.name = cartConsistenceBuilder.name;
        this.price = cartConsistenceBuilder.price;
        this.quantity = cartConsistenceBuilder.quantity;
        this.totalOrderCost = cartConsistenceBuilder.totalOrderCost;
    }


    public void setName(String name) { this.name = name;}
    public void setPrice(double price) { this.price = price;}
    public void setQuantity(int quantity) { this.quantity = quantity;}
    public void setTotalOrderCost(double totalOrderCost) { this.totalOrderCost = totalOrderCost;}


    public void getName() { this.name = name;}
    public void getPrice() { this.price = price;}
    public void getQuantity() { this.quantity = quantity;}
    public void getTotalOrderCost() { this.totalOrderCost = totalOrderCost;}

    public static void addToCartConsistenceList(String cName, double cPrice, int cQuantity, double total){
        for (int i=0;i<cartConsistenceList.size();i++){
            if(cartConsistenceList.get(i).name.equals(cName)){
                } else {
                cartConsistenceList.add(new CartConsistence(new CartConsistence.Builder()
                .buildName(cName).buildPrice(cPrice).buildQuantity(cQuantity)));
            }
//        if(!cartConsistenceList.contains(cName)){
//            cartConsistenceList.add(new CartConsistence(new CartConsistence.Builder()
//                .buildName(name).buildPrice(price).buildQuantity(quantity)));
//        }else {cartConsistenceList.
////            cartConsistenceList.
        }
    }

    @Override
    public String toString() {
        return  name +" "+ price +" "+ quantity +" "+ totalOrderCost;
    }

    public static class Builder {

        private String name;
        private double price;
        private int quantity;
        private double totalOrderCost;

        public Builder buildName(String name){
            this.name = name;
            return this;
        }

        public Builder buildPrice(double price){
            this.price = price;
            return this;
        }

        public Builder buildQuantity(int quantity){
            this.quantity = quantity;
            return this;
        }

        public Builder buildTotalOrderCost(double totalOrderCost){
            this.totalOrderCost = totalOrderCost;
            return this;
        }

        public CartConsistence build(){return new CartConsistence(this);}

        @Override
        public String toString() {
            return  name +" "+ price +" "+ quantity +" "+ totalOrderCost;
        }

    }
}
