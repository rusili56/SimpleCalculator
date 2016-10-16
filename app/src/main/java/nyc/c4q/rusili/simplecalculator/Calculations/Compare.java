package nyc.c4q.rusili.simplecalculator.Calculations;

/**
 * Created by rusili on 10/8/16.
 */
public class Compare {

    public static boolean isHigher(char cThis, char cStack){
        int iThis = 0, iStack = 0;

        switch (cThis) {
            case '*':
            case '/':
                iThis = 1;
                break;
            case '^':
                iThis = 2;
                break;
            case '(':
                iThis = 3;
                break;
        }

        switch (cStack) {
            case '*':
            case '/':
                iStack = 1;
                break;
            case '^':
                iStack = 2;
                break;
            case '(':
                iStack = 3;
                break;
        }

        if (iThis < iStack){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isOp (char cInput){
        if (cInput == '-' || cInput == '+' || cInput == '*' || cInput == '/' || cInput == ')'){
            return true;
        } else {
            return false;
        }
    }
}
