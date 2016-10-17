package nyc.c4q.rusili.simplecalculator.Calculations;

import java.util.LinkedList;
import java.util.Queue;

import nyc.c4q.rusili.simplecalculator.Calculations.Compare;

/**
 * Created by rusili on 10/7/16.
 */
public class Recursion {

    // Fields:
    boolean isParenth = false;
    boolean insideParenth = false;
    int size = 0;
    Queue<Character> cQueue = new LinkedList<>();
    StringBuilder answer2 = new StringBuilder();

    // Starts the recursion process:
    public StringBuilder start(String sInput) {
        this.toQueue(sInput);
        StringBuilder answer = this.recursion(this.getNum(), this.getC());
        return answer;
    }

    // Converts all main string into a queue:
    public Queue<Character> toQueue(String sInput) {
        for (int i = 0; i < sInput.length(); i++) cQueue.add(sInput.charAt(i));
        size = cQueue.size();
        return cQueue;
    }

    // Method to get the next number:
    public StringBuilder getNum() {
        StringBuilder num1 = new StringBuilder();
        Character op1 = 'a';
        char num2 = cQueue.peek();

        if (num2 == '(') {
            cQueue.remove();
            isParenth = true;
        }
        num1.append(cQueue.remove());

        if (cQueue.size() > 0) {
            op1 = cQueue.peek();
            while (!Compare.isOp(op1)) {
                num1.append(op1);
                cQueue.remove();
                op1 = cQueue.peek();
                if (cQueue.size() > 0) {
                    if (Compare.isOp(op1)) break;
                } else {
                    break;
                }
            }
        }
        return num1;
    }

    // Method to get the next operator:
    public char getC() {
        char c = 'a';
        if (cQueue.size() > 0) {
            c = cQueue.remove();
            if (c == ')') {
                isParenth = false;
                if (cQueue.size() > 0) c = cQueue.remove();
            }
        }
        return c;
    }

    // Main recursion method:
    public StringBuilder recursion(StringBuilder sbInput, char cInput) {
        int answer;
        answer = Integer.parseInt(sbInput.toString());
        StringBuilder num2;
        Character op1, op2;
        op1 = cInput;

        num2 = this.getNum();
        op2 = this.getC();

        if ((Compare.isHigher(op1, op2) == true || isParenth) && !insideParenth) {
            if (isParenth) insideParenth = true;
            answer2 = recursion(num2, op2);
            answer = Calc.ulate(answer, op1, answer2);
            answer2 = new StringBuilder();
            answer2.append(answer);
        } else {
            insideParenth = false;
            answer = Calc.ulate(answer, op1, num2);
            answer2 = new StringBuilder();
            answer2.append(answer);
            if (cQueue.size() > 0) recursion(answer2, op2);
        }
        return answer2;
    }
}
