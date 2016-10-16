package nyc.c4q.rusili.simplecalculator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

public class Vertical extends AppCompatActivity {

    private boolean lastequals = false;
    private String op = "";
    private String sNumber1, sNumber2, sAnswer;
    private String sDisplay = "";
    private String sDisplay2 = "";
    private TextView tvMain, tvHistory;
    private HorizontalScrollView scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vertical);
        scroll = (HorizontalScrollView) findViewById(R.id.headerscroll);
        tvHistory = (TextView) findViewById(R.id.headerdisplay);
        tvMain = (TextView) findViewById(R.id.displaynumbers);
    }
    public void ce() {
        lastequals = false;
        sNumber1 = sNumber2 = sAnswer = sDisplay = sDisplay2 = "";
        tvMain.setText(sDisplay);
        tvHistory.setText(sDisplay2);
        this.cantClickOP();
        this.canClickEquals();
    }

    public void clear() {
        sDisplay = "";
        tvMain.setText(sDisplay);
    }

    public void addValue(int input) {
        sDisplay += Integer.toString(input);
        tvMain.setText(sDisplay);
        sDisplay2 += input;
        tvHistory.setText(sDisplay2);
    }

    public void addValue(char input) {
        sDisplay += input;
        tvMain.setText(sDisplay);
        sDisplay2 += input;
        tvHistory.setText(sDisplay2);
    }

    public void del() {
        sDisplay = sDisplay.substring(0, (sDisplay.length() - 1));
        sDisplay2 = sDisplay2.substring(0, (sDisplay2.length() - 1));
        tvMain.setText(sDisplay);
        tvHistory.setText(sDisplay2);
    }
    public void onClickNum(View v) {
        Button b = (Button)v;
        int i = Integer.parseInt(b.getText().toString());
        addValue(i);
        this.canClickOP();
        this.canClickEquals();
    }

    public void onClickUtil(View v) {
        Button b = (Button)v;
        String s = b.getText().toString();
        switch (s){
            case ".":
                if (!sDisplay.contains(".")) {
                    addValue('.');
                }
                break;
            case "DEL":
                this.del();
                break;
            case "":
                this.ce();
                break;
            case "=":
                sNumber2 = sDisplay;
                Log.d("sDisplay", sDisplay);
                if (op.equals("/")) {
                    sAnswer = Double.toString(Double.parseDouble(sNumber1) / Double.parseDouble(sNumber2));
                } else if (op.equals("*")) {
                    sAnswer = Double.toString(Double.parseDouble(sNumber1) * Double.parseDouble(sNumber2));
                } else if (op.equals("-")) {
                    sAnswer = Double.toString(Double.parseDouble(sNumber1) - Double.parseDouble(sNumber2));
                } else if (op.equals("+")) {
                    sAnswer = Double.toString(Double.parseDouble(sNumber1) + Double.parseDouble(sNumber2));
                }
                // Converts double to int if answer ends in ".0"
                if (Double.parseDouble(sAnswer) == Math.floor(Double.parseDouble(sAnswer))){
                    sAnswer = Integer.toString((int) Double.parseDouble(sAnswer));
                }

                tvMain.setText(sAnswer);
                if (lastequals) {
                    sDisplay2 += " " + op + " " + sNumber2;
                }
                lastequals = true;
                sNumber1 = sAnswer;
                sDisplay2 += " = " + sAnswer;
                tvHistory.setText(sDisplay2);

                scroll.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                this.canClickOP();
                break;
        }
    }

    public void onClickOp(View v) {
        Button b = (Button)v;
        String s = b.getText().toString();

        if (!lastequals) {
            sNumber1 = sDisplay;
        }
        tvHistory.setText(sDisplay2);
        scroll.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
        lastequals = false;
        clear();
        switch (s){
            case "+":
                op = "+";
                sDisplay2 += " + ";
                break;
            case "-":
                op = "-";
                sDisplay2 += " - ";
                break;
            case "*":
                op = "*";
                sDisplay2 += " * ";
                break;
            case "/":
                op = "/";
                sDisplay2 += " / ";
                break;
        }
        this.cantClickOP();
        this.cantClickEquals();;
        tvHistory.setText(sDisplay2);
    }

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

    public void cantClickEquals(){
        Button temp = (Button) findViewById(R.id.buttonequals);
        temp.setClickable(false);
        temp.setFocusable(false);
        temp.setEnabled(false);
        temp.setTextColor(Color.parseColor("#767676"));
    }

    public void canClickEquals(){
        Button temp = (Button) findViewById(R.id.buttonequals);
        temp.setClickable(true);
        temp.setFocusable(true);
        temp.setEnabled(true);
        temp.setTextColor(Color.parseColor("#76B3FF"));
    }
}

