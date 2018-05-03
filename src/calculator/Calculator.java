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
public class Calculator {

    private int pointerCounter;
    private int operatorCounter = 0;
    private int numberCounter = 1;
    private String previousLabel;
    private String sign="";
    private String labelValueArray[];
    String segno = "";
    private int flag = 0;

    private final String CE(String a) {
        pointerCounter = 0;
        String b[] = a.split("");
        int flag = 0;
        a = "";
        for (int i = 0; i < b.length; i++) {
            if (isOperator(b[i]) == false) {
                a = a + b[i];
            } else {
                a = a + b[i];
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            return "0";
        } else {
            return a;
        }
    }

    private final String backspace(String a) {
        String b[] = a.split("");
        for (int i = b.length - 1; i >= 0; i--) {
            if (isOperator(b[i]) == false) {
                a = "";
                for (int k = 0; k < b.length - 1; k++) {
                    a = a + b[k];
                }
                if (b[i].equals(".")) {
                    pointerCounter = 0;
                }
            } else {
                numberCounter = 1;
            }
            break;
        }
        if (a.length() == 0) {
            a = "0";
        }
        return a;
    }

    public final String valueChecker(String label, String ActionComand) {
        String labelArray[] = label.split("");
        if (ActionComand.equals("CE")) {
            return CE(label);
        } else if (ActionComand.equals("C")) {
            pointerCounter = 0;
            operatorCounter = 0;
            numberCounter = 1;
            sign="";
            return "0";
        } else if (ActionComand.equals("<-")) {
            return backspace(label);
        } else if (ActionComand.equals("=")) // precedenza
        {
            if (numberCounter > 1) {
                numberCounter--;
                operatorCounter--;
                return calcolo(label);
            } else {
                return label;
            }

        } 
        else if (ActionComand.equals(".")) {
            if (isOperator(labelArray[labelArray.length - 1]) || pointerCounter > 0) {
                return label;
            } else {
                pointerCounter++;
                return label + ActionComand;
            }
        } else if (label.equals("0") && isOperator(ActionComand) == false) 
        {
            return ActionComand;
        } else if (labelArray[labelArray.length - 1].equals(".") && isOperator(ActionComand)) {
            return label;
        } else if (ActionComand.equals("^")) 
        {
            if (isOperator(label.split("")[label.length() - 1])) {
                operatorCounter--;
                return afterPoint(Double.parseDouble(labelArray[0]) * Double.parseDouble(labelArray[0]) + "");
            }
            if (operatorCounter > 0) {
                segno = operatorSearch(label);
                labelValueArray = label.split("\\" + segno);
                return labelValueArray[0] + segno + afterPoint(Double.parseDouble(labelValueArray[1]) * Double.parseDouble(labelValueArray[1]) + ""); // operator divide 
            } else {
                return afterPoint(Double.parseDouble(label) * Double.parseDouble(label) + "");
            }
        } else if (isOperator(labelArray[labelArray.length - 1]) && isOperator(ActionComand)) 
        {
            label = "";
            for (int i = 0; i < labelArray.length - 1; i++) {
                label = label + labelArray[i];
            }
            label = label + ActionComand;
            sign=ActionComand;
            return label;
        } 
        else 
        {

            if (isOperator(ActionComand) && ActionComand != "^") 
            {
                if(operatorCounter<1 || sign.equals(""))
                {
                    sign=ActionComand;
                   
                }
                operatorCounter++;
                flag = 1;
                pointerCounter = 0;
                if (operatorCounter > 1) 
            {
                if(ActionComand.equals(sign) || ActionComand.equals("-") ||ActionComand.equals("+"))
                {
                  label = calcolo(label);
                  sign=ActionComand;
                  operatorCounter--;
                  numberCounter--;
                }
              else
                {
                  sign=ActionComand;
                  return label+ActionComand;   
                }
            }
            } 
            else if (flag != 0) 
            {
                numberCounter++;
                flag = 0;
            }
            
        }
        return label + ActionComand;
    }

    private final String calcolo(String a) {
        String result = "error";
        segno = operatorSearch(a);
        labelValueArray = a.split("\\" + segno);
        if (segno.equals("/")) {
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

        if (result.equals("error")); else {
            return afterPoint(result);
        }
        return "error";
    }

    private String afterPoint(String result) // calculate values after point
    {
        String[] resultArray = result.split("");
        flag = 0;
        int count = 0;
        for (int i = 0; i < resultArray.length; i++) {
            if (flag == 1) {
                count++;
            }
            if (resultArray[i].equals(".")) {
                flag = 1;
            }
        }
        if (count > 1) {
            return result;
        } else {
            if (result.split("")[result.length() - 1].equals("0")) {
                return (long) Double.parseDouble(result) + "";
            }
            return result;
        }
    }

    private final boolean isOperator(String c) {
        if (c.equals("+") || c.equals("^") || c.equals("-") || c.equals("*") || c.equals("/")) {
            return true;
        }

        return false;
    }

    private final String operatorSearch(String a) {
        labelValueArray = a.split("");
        for (int i = 0; i < labelValueArray.length; i++) {
            if (isOperator(labelValueArray[i]) == false); else {
                a = labelValueArray[i];
                break;
            }
        }
        return a;
    }

}
