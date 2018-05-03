/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 *
 * @author haseeb
 */
public class Char {
    private Char cr;
    private char stack;
    public Char(char c)
    {
       cr=null;
       stack=c;
    }
    public void push(Char cr) {
        this.cr = cr;
    }

    public void setStack(char stack) {
        this.stack = stack;
    }
    

    public Char pop() {
        return cr;
    }

    public char getStack() {
        return stack;
    }
    
    
    
    
}
