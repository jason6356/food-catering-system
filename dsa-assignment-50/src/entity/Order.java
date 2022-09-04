/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.*;
import java.util.Objects;

/**
 *
 * @author Jason
 */
public class Order {
    
    private String orderID;
    private ListInterface<Food> orderedItems;
    private double totalAmt;
    private static int orderCount = 1;

    public Order() {
        this.orderID = generateOrderID();    
        this.totalAmt = 0.0;
        this.orderedItems = new ArrayList<>();
        orderCount++;
    }

    /*
    
        Utilility Method to generate Order ID
    
    */
    private String generateOrderID(){
        if(orderCount < 10)
            return "OD00" + orderCount;
        else if(orderCount < 100)
            return "OD0" + orderCount;
        else
            return "OD" + orderCount;
    }
    
    /*
     * 
     * Operation Methods 
     */
    
    public void addItemToOrder(Food item){
        orderedItems.add(item);    
    }
    
    public void removeItemFromOrder(Food item){
        
        if(!orderedItems.contains(item)){
            System.out.println("The item is not ordered from the list");
            return;
        }
        
        boolean found = false;
        int positionToDel = 0;
        
        for(int i = 1; i <= orderedItems.getNumberOfEntries(); i++)
            if(orderedItems.getEntry(i).equals(item)){
                found = true;
                positionToDel = i;
            }
        
        if(found){
            orderedItems.remove(positionToDel);
            System.out.println("Item Deleted Successfully!");
        }
        
    }
    
    public String getOrderDetails(){
    
        
        String result = String.format("Order ID - %s\nOrdered Items\n=========\n", this.orderID);
        
        result += orderedItems.toString();
        
        
        result += String.format("Total Amount - %.2f", calculateTotalAmount());
        
        return result;
        
    }
    
    public String getBriefDetails(){
        String result = String.format("%s %.2f", this.orderID, this.calculateTotalAmount());
        return result;
    }
    
    public double calculateTotalAmount(){
        
        double total = 0;
        for(int i = 1; i <=orderedItems.getNumberOfEntries(); i++)
            total += orderedItems.getEntry(i).getPrice();
        
        return total;
    }    
    //Getter and Setters

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
    public double getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(double totalAmt) {
        this.totalAmt = totalAmt;
    }

    public static int getOrderCount() {
        return orderCount;
    }

    public static void setOrderCount(int orderCount) {
        Order.orderCount = orderCount;
    }

    public ListInterface<Food> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(ListInterface<Food> orderedItems) {
        this.orderedItems = orderedItems;
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
        final Order other = (Order) obj;
        if (!Objects.equals(this.orderID, other.orderID)) {
            return false;
        }
        return true;
    }
}
