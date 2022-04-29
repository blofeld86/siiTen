package Page.Objects;

import java.util.*;

public class CartConsistence {

    public static final List<CartConsistence> cartConsistenceList = new ArrayList<>();
    public static final List<CartConsistence> popUpCcList = new ArrayList<>();

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

    public void increaseQuantity(int by){ quantity+=by;}

    public void setName(String name) { this.name = name;}
    public void setPrice(double price) { this.price = price;}
    public void setQuantity(int quantity) { this.quantity = quantity;}
    public void setTotalOrderCost(double totalOrderCost) { this.totalOrderCost = totalOrderCost;}


    public String getName() { return name;}
    public double getPrice() { return price;}
    public int getQuantity() { return quantity;}
    public double getTotalOrderCost() { return totalOrderCost;}

    public static void addToCartConsistenceList(String cName, double cPrice, int cQuantity){
        cartConsistenceList.add(new CartConsistence(new CartConsistence.Builder()
                    .buildName(cName).buildPrice(cPrice).buildQuantity(cQuantity)));
            cartConsistenceList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        CartConsistence last = null;
          for (int i = 0; i < cartConsistenceList.size(); i++) {
              CartConsistence cartConsistence = cartConsistenceList.get(i);
              if(i==0){
                last = cartConsistence;
                continue;
              }
              if(last.getName().equals(cartConsistence.getName())){
                last.increaseQuantity(cartConsistence.getQuantity());
                cartConsistenceList.remove(cartConsistence);
                i--;
              }else{
                last = cartConsistence;
              }
          }
    }

    @Override
    public String toString() {
        return  name +" "+ price +" "+ quantity +" "+ totalOrderCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartConsistence)) return false;

        CartConsistence that = (CartConsistence) o;

        if (Double.compare(that.getPrice(), getPrice()) != 0) return false;
        if (getQuantity() != that.getQuantity()) return false;
        if (Double.compare(that.getTotalOrderCost(), getTotalOrderCost()) != 0) return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getName() != null ? getName().hashCode() : 0;
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getQuantity();
        temp = Double.doubleToLongBits(getTotalOrderCost());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
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
