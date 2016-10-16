package nyc.c4q.rusili.simplecalculator.Calculations;

/**
 * Created by rusili on 10/9/16.
 */
public class Calc {

    public static int ulate (int iInput, char cInput, StringBuilder sbInput2){
        int answer = 0;

        switch (cInput){
            case '+':
                answer = iInput + Integer.parseInt(sbInput2.toString());
                break;
            case '-':
                answer = iInput - Integer.parseInt(sbInput2.toString());
                break;
            case '*':
                answer = iInput * Integer.parseInt(sbInput2.toString());
                break;
            case '/':
                answer = iInput / Integer.parseInt(sbInput2.toString());
                break;
        }
        return answer;
    }
}
