/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import com.sun.javafx.font.Metrics;
import java.awt.Font;
import static java.nio.file.Files.size;

/**
 *
 * @author haseeb
 */
public class Calculator {

    private int pointerCounter;
    private int operatorCounter = 0;
    private int numberCounter = 1;
    private int numberCounter1=0;
    private String previousLabel;
    private String sign = "";
    private String labelValueArray[];
    private String segno = "";
    private int flag = 0;
    private String Actioncommand = "";
    private boolean equalFlag=false;

    private final String CE(String a) 
    {
       pointerCounter = 0;
       numberCounter=0;
        String b[] = a.split("");
       int flag=-1;
        
        for(int i=b.length-1;i>0;i--)
        {
            if(isOperator(b[i])==false);
            else
            {
              flag=i;
              break;
            }
        }
        if (flag == -1) {
            return "0";
        } 
        else {
            return a.substring(0, flag+1);
        }
    }

    private final String backspace(String a) 
    {
        String b[] = a.split("");
        
         if(isOperator(b[b.length-1]));
         else
         {
              a=a.substring(0,a.length()-1);
         }
         if (a.length() == 0) {
            return "0";
        }
         else 
         {
            return a; 
         }
       }

    public final String valueChecker(String label, String ActionComand) 
    {
        if(equalFlag==true) // after equal checker
        {
           if(isOperator(ActionComand));
            else label="0";
            equalFlag=false;
        }
        if(label.equals("Infinity"))
        {
            label="0";
        }
        this.Actioncommand = ActionComand;
        if (ActionComand.equals("CE")) {
            return CE(label);
        } else if (ActionComand.equals("C")) {
            pointerCounter = 0;
            operatorCounter = 0;
            numberCounter = 1;
            sign = "";
            return "0";
        } else if (ActionComand.equals("<-")) 
        {
            return backspace(label);
        }
        else if (ActionComand.equals("=")) // precedenza
        {
            if (numberCounter >=2 && operatorCounter>0) 
            {
                equalFlag=true;
                return calcolo(label);
            } 
            else {
                return label;
            }
        } 
        else if (ActionComand.equals(".")) {
            if (isOperator(label.charAt(label.length() - 1) + "") || pointerCounter > 0) {
                return label;
            } else {
                pointerCounter++;
                return label + ActionComand;
            }
        } else if (isOperator(ActionComand)) // operatori
        {
            numberCounter1=0;
            return operatorChecker(label, ActionComand);
        } 
        else  if(numberCounter1<13) // numeri
        {
            numberCounter1++;
            if (label.equals("0")) 
            {
                return ActionComand;
            } 
            else {
                numberCounter++;
                return label + ActionComand;
            }
        }
        else return label;
    }

    private String operatorChecker(String label, String ActionComand) 
    {
        pointerCounter = 0; 
        previousLabel = "";
        String labelArray[] = label.split("");
        if (ActionComand.equals("x²")) 
        {
            if (isOperator(label.charAt(label.length() - 1) + "") || (label.charAt(label.length() - 1) + "").equals(".")) {
                return label;
            } else {
                label = "";
                for (String i: labelArray) 
                {
                    if (isOperator(i) == false) {
                        label = label + i;
                    } else {
                        previousLabel = previousLabel + label + i;
                        label = "";
                    }
                }
                return previousLabel + afterPoint(Double.parseDouble(label) * Double.parseDouble(label) + "");
            }
        } 
        else if ((label.charAt(label.length() - 1) + "").equals(".")  && isOperator(ActionComand)) //  !!.+!!
        {
            return label;
        } 
        
        else if (isOperator(label.charAt(label.length() - 1) + "") && isOperator(ActionComand)) // change operator 
        {
         
            label = "";
          for (int i = 0; i < labelArray.length - 1; i++) 
          {
                label = label + labelArray[i];
          }
            if (ActionComand.equals("-") && numberCounter > 1 || ActionComand.equals("+") && numberCounter > 1 || operatorCounter > 1) 
            {
                label = calcolo(label);
            }
            sign = ActionComand;
            label = label + ActionComand;
            return label;
        } 
        else {
            operatorCounter++;
            if (operatorCounter >= 2 && priority(sign, ActionComand) == 0 && numberCounter>1) 
            {
                sign = ActionComand;
                label = calcolo(label);
                label += ActionComand;
            }
            else 
            {
             
                numberCounter1=0;
                sign = ActionComand;
                label = label + ActionComand;
            }
        }

        return label;
    }

    private final String calcolo(String a) {
        String numero = "";
        String segno1 = "";
        String result = "error1";
        if (operatorCounter > 2 || Actioncommand.equals("=") && operatorCounter==2 ) 
        {
            numberCounter -= 2;
            operatorCounter--;
            segno1 = operatorSearch(a);
            String prova[] = a.split("\\" + segno1);
            numero = prova[0];
            a = prova[1];
            segno = operatorSearch(a);
            if (isOperator(a.charAt(0) + "") && segno.equals("-")) 
            {
                labelValueArray = a.substring(1, a.length()).split(segno);
                labelValueArray[0] = a.charAt(0) + labelValueArray[0];
            } else 
            {
                labelValueArray = a.split("\\" + segno);
            }
        } 
        else {
           
            if (Actioncommand.equals("=")) {
                operatorCounter = 0;
            } 
            else 
            {
                operatorCounter--;
            }
            segno = operatorSearch(a);
            if (isOperator(a.charAt(0) + "") && segno.equals("-")) 
            {
                labelValueArray = a.substring(1, a.length()).split(segno);
                labelValueArray[0] = a.charAt(0) + labelValueArray[0];
            } 
            else 
            {
                labelValueArray = a.split("\\" + segno);
            }
            numberCounter=1;
        }

        if (segno.equals("/")) 
        {
            result = (Double.parseDouble(labelValueArray[0]) / Double.parseDouble(labelValueArray[1])) + "";
        }
        if (segno.equals("+")) {
            result = (Double.parseDouble(labelValueArray[0]) + Double.parseDouble(labelValueArray[1])) + "";
        }
        if (segno.equals("-")) {
            result = (Double.parseDouble(labelValueArray[0]) - Double.parseDouble(labelValueArray[1])) + "";
        }
        if (segno.equals("*")) {
            result = (Double.parseDouble(labelValueArray[0]) * Double.parseDouble(labelValueArray[1])) + "";
        }
        if (result.equals("error1")); 
        else {
            if (Actioncommand.equals("=") && numero != "" || Actioncommand.equals("+") && numero != "" || Actioncommand.equals("-") && numero != "") {
                return result = afterPoint(calcolo(numero + segno1 + result));

            } else {
                return result = numero + segno1 + afterPoint(result);
            }
        }

        return result;
    }

    private String afterPoint(String result) // calculate values after point
    {
        
        String[] resultArray = result.split("");
       
        flag = 0;
        int count2=0;
        int count = 0;
        for (String i :  resultArray) 
        {
            if (flag == 1) 
            {
                count++;
            }
            if (i.equals(".")) 
            {
                flag = 1;
            }
            
        }
        if (count > 1) 
        {
            return result;
        } 
        else 
        {
            if (result.split("")[result.length() - 1].equals("0")) 
            {
                return (long) Double.parseDouble(result) + "";
            }
            return result;
        }
    }

    private final boolean isOperator(String c) {
        if (c.equals("+") || c.equals("x²") || c.equals("-") || c.equals("*") || c.equals("/")) {
            return true;
        }

        return false;
    }

    private final String operatorSearch(String a) {
        labelValueArray = a.split("");
        for (int i=1;i<labelValueArray.length;i++) 
        {
            if (isOperator(labelValueArray[i]) == false);
            else 
            {
                return labelValueArray[i];
            }
        }
        return "error";
    }

    private int priority(String sign, String ActionCommand) 
    {
        if (sign.equals(ActionCommand)) 
        {
            return 0;
        }
        else if (sign != ActionCommand && ActionCommand.equals("+") || ActionCommand.equals("-")) 
        {
            return 0;
        }
        else if ((sign.equals("/") || sign.equals("*"))  && (ActionCommand.equals("*") || ActionCommand.equals("/"))) // errore
        {
            return 0;
        } 
        else 
        {
            return 1;
        }
    }

    // migliorare grafica
    
    // CE problem operator
    
    
}