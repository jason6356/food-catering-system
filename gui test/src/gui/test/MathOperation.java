/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.test;

/**
 *
 * @author Jason
 */
public class MathOperation {
    
    private int n1;
    private int n2;
    
    
    public String add(String a, String b){
        n1 = Integer.parseInt(a);
        n2 = Integer.parseInt(b);
        
        return Integer.toString(n1 + n2);
    }
    
    public String subtract(String a, String b){
        n1 = Integer.parseInt(a);
        n2 = Integer.parseInt(b);
        
        return Integer.toString(n1 - n2);
    }
    
    public String multiply(String a, String b){
        n1 = Integer.parseInt(a);
        n2 = Integer.parseInt(b);
        
        return Integer.toString(n1 * n2);
    }
    
    public String division(String a, String b){
        n1 = Integer.parseInt(a);
        n2 = Integer.parseInt(b);
        
        
        return n2 != 0 ? Integer.toString(n1 / n2) : "Invalid Expression";
    
    }
    
    
    
}
