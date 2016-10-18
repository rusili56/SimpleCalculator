package nyc.c4q.rusili.simplecalculator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import nyc.c4q.rusili.simplecalculator.Calculations.Recursion;

public class Main extends AppCompatActivity {

    // Fields:
    boolean leftParenth = false;
    int terms = 0;
    private String sDisplay = "";
    private String sHistory = "";
    private TextView tvDisplay, tvHistory;
    private HorizontalScrollView scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vertical);
        scroll = (HorizontalScrollView) findViewById(R.id.headerscroll);
        tvHistory = (TextView) findViewById(R.id.headerdisplay);
        tvDisplay = (TextView) findViewById(R.id.displaynumbers);
        this.cantClickOP();
    }

    // Recursion calculation starts here:
    private void calculate(String sInput) {
        Recursion rec = new Recursion();
        sDisplay = rec.start(sInput).toString();
    }

    // onClick for the numpad
    public void onClickNum(View v) {
        Button b = (Button)v;
        int i = Integer.parseInt(b.getText().toString());
        addValue(i);
        this.canClickOP();
        terms++;
    }

    // onClick for the 4 Operations.
    public void onClickOp(View v) {
        Button b = (Button)v;
        char s = b.getText().charAt(0);
        addValue(s);
        this.cantClickOP();
        terms++;

        tvHistory.setText(sHistory);
        scroll.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
    }

    // onClick for the other buttons
    public void onClickUtil(View v) {
        Button b = (Button)v;
        String s = b.getText().toString();
        switch (s){
            case "( )":
                if (leftParenth == false){
                    addValue('(');
                    leftParenth = true;
                }
                else {
                    addValue(')');
                    leftParenth = false;
                }
                break;
            case ".":
                if (!sDisplay.contains(".")) addValue('.');
                break;
            case "DEL":
                if(sHistory.length()>0 && sDisplay.length()>0) {
                    this.del();
                }
                else{
                    sHistory = "0";
                    sDisplay = "0";
                }
                break;
            case "":
                this.ce();
                break;
            case "=":
                if (terms > 2 && !leftParenth) {
                    this.calculate(tvDisplay.getText().toString());
                    tvDisplay.setText(sDisplay);
                    sHistory += "=" + sDisplay;
                    tvHistory.setText(sHistory);
                    terms = 0;
                } else {
                    if (terms < 2){
                        Toast toast = Toast.makeText(this, "Invalid expression", Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (!leftParenth){
                        Toast toast = Toast.makeText(this, "Open parenthesis", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
        }
    }

    // Method to clear everything
    public void ce() {
        sDisplay = sHistory = "";
        tvDisplay.setText(sDisplay);
        tvHistory.setText(sHistory);
        this.cantClickOP();
    }

    // Appends the input (int) to the String of the main display
    public void addValue(int input) {
        // Avoids 0 spam
        if (sDisplay.length() > 0 || input != 0) {
            sDisplay += Integer.toString(input);
            tvDisplay.setText(sDisplay);
            sHistory += input;
            tvHistory.setText(sHistory);
        }
    }

    // Appends the input (char) to the String of the main display
    public void addValue(char input) {
        sDisplay += input;
        tvDisplay.setText(sDisplay);
        sHistory += input;
        tvHistory.setText(sHistory);
    }

    // Deletes the last character of the main display
    public void del() {
        sDisplay = sDisplay.substring(0, (sDisplay.length() - 1));
        sHistory = sHistory.substring(0, (sHistory.length() - 1));
        tvDisplay.setText(sDisplay);
        tvHistory.setText(sHistory);
    }

    // Methods to disable/enable certain buttons.
    public void cantClickOP(){
        Button temp = (Button) findViewById(R.id.buttonplus);
        temp.setClickable(false);
        temp.setFocusable(false);
        temp.setEnabled(false);
        temp.setTextColor(Color.parseColor("#767676"));
        temp = (Button) findViewById(R.id.buttonminus);
        temp.setClickable(false);
        temp.setFocusable(false);
        temp.setEnabled(false);
        temp.setTextColor(Color.parseColor("#767676"));
        temp = (Button) findViewById(R.id.buttonmultiply);
        temp.setClickable(false);
        temp.setFocusable(false);
        temp.setEnabled(false);
        temp.setTextColor(Color.parseColor("#767676"));
        temp = (Button) findViewById(R.id.buttondivide);
        temp.setClickable(false);
        temp.setFocusable(false);
        temp.setEnabled(false);
        temp.setTextColor(Color.parseColor("#767676"));
    }

    public void canClickOP(){
        Button temp = (Button) findViewById(R.id.buttonplus);
        temp.setClickable(true);
        temp.setFocusable(true);
        temp.setEnabled(true);
        temp.setTextColor(Color.parseColor("#dcdcdc"));
        temp = (Button) findViewById(R.id.buttonminus);
        temp.setClickable(true);
        temp.setFocusable(true);
        temp.setEnabled(true);
        temp.setTextColor(Color.parseColor("#dcdcdc"));
        temp = (Button) findViewById(R.id.buttonmultiply);
        temp.setClickable(true);
        temp.setFocusable(true);
        temp.setEnabled(true);
        temp.setTextColor(Color.parseColor("#dcdcdc"));
        temp = (Button) findViewById(R.id.buttondivide);
        temp.setClickable(true);
        temp.setFocusable(true);
        temp.setEnabled(true);
        temp.setTextColor(Color.parseColor("#dcdcdc"));
    }


//--------------------------------------------------------------

    // Method just to clear the main display. Not used atm.
    public void clear() {
        sDisplay = "";
        tvDisplay.setText(sDisplay);
    }
}

