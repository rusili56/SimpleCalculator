package nyc.c4q.rusili.simplecalculator.Calculations;

/**
 * Created by rusili on 10/9/16.
 */
public class Calc {

    public static int ulate (int iInput, char cInput, StringBuilder sbInput){
        int answer = 0;

        switch (cInput){
            case '+':
                answer = iInput + Integer.parseInt(sbInput.toString()); break;
            case '-':
                answer = iInput - Integer.parseInt(sbInput.toString()); break;
            case '*':
                answer = iInput * Integer.parseInt(sbInput.toString()); break;
            case '/':
                answer = iInput / Integer.parseInt(sbInput.toString()); break;
        }
        return answer;
    }
}
