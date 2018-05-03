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
public class sequenza {

    private Char head;
    private int elemento = 0;
    private String infix;
    private String postfix = "";
    private  int risultato;

    public sequenza(String infix) {
        this.infix = infix + ")";
        head = new Char('(');
        risultato=0;
    }

    public void convert() {
        while (head != null) {
            if (Character.isDigit(infix.charAt(elemento))) 
            {
                postfix = postfix + infix.charAt(elemento);
                elemento++;
            }
            
            else if (isOperator(head.getStack()) && isOperator(infix.charAt(elemento))) {
                if (precendence(head.getStack(), infix.charAt(elemento))) {
                    postfix = postfix + head.getStack();
                    head = head.pop();
                }
                newchar(infix.charAt(elemento));
                elemento++;
            } 
            else {
                if (infix.charAt(elemento) == ')') {
                    while (isOperator(head.getStack())) {
                        postfix = postfix + head.getStack();
                        head = head.pop();
                    }
                    head = head.pop();
                } else {
                    newchar(infix.charAt(elemento));
                }
                elemento++;
               }
        }
        System.out.println(postfix);
        calcRisultato();
    }

    private void calcRisultato() {
        elemento = 0;
        while (elemento < postfix.length()) {
            if (Character.isDigit(postfix.charAt(elemento))) {
                newchar(postfix.charAt(elemento));
            } else {
                int x = head.getStack() - '0';
                head = head.pop();
                int y = head.getStack() - '0';
                head = head.pop();
                calcolo(x, y, postfix.charAt(elemento));
                newchar((char) (risultato + '0'));
            }
            elemento++;
        }
        System.out.println(risultato);
    }

    private  void calcolo(int x, int y, char op) {
        if (op == '+') {
            risultato = x + y;
        }
        if (op == '*') {
            risultato = x * y;
        }
        if (op == '-') {
           risultato = x - y;
           if(risultato<0)
               risultato=-risultato;
        }
        if (op == '/') {
            if (x < y) {
                int temp = x;
                x = y;
                y = temp;
            }
            risultato = x / y;
        }

    }

    private void newchar(char c) {
        Char a = new Char(c);
        a.push(head);
        head = a;
    }

    private static boolean isOperator(char c) {
        if (c == '(' || c == ')') {
            return false;
        }
        return true;
    }

    public int getRisultato() {
        return risultato;
    }

    private static boolean precendence(char c, char c1)// da sistemare
    {
        if (c == '-' || c == '+') {
            return false;
        }
        return true;
    }

}
