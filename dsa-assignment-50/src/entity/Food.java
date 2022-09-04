package entity;

import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jason
 */
public class Food {
    
    private String foodID;
    private String name;
    private double price;
    private static int totalFoodCount = 1;
    
    public Food() {
        this("",0.0);
    }

    public Food(String name, double price) {
        this.foodID = generateFoodID();
        this.name = name;
        this.price = price;
        totalFoodCount++;
    }

    public Food(String foodID, String name, double price) {
        this.foodID = foodID;
        this.name = name;
        this.price = price;
    }
    
    /*
        Ultility Methods
    */
    
    private String generateFoodID(){
        
        if(totalFoodCount < 10)
            return "FD00" + totalFoodCount;
        else if(totalFoodCount < 100)
            return "FD0" + totalFoodCount;
        else
            return "FD" + totalFoodCount;
        
    }
    
    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static int getTotalFoodCount() {
        return totalFoodCount;
    }

    public static void setTotalFoodCount(int totalFoodCount) {
        Food.totalFoodCount = totalFoodCount;
    }

    @Override
    public String toString() {
        return String.format("Food ID - %s\nFood Name - %s\nFood Price - %.2f\n", foodID, name, price);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Food other = (Food) obj;
        if (!Objects.equals(this.foodID, other.foodID)) {
            return false;
        }
        return true;
    }

    
}
