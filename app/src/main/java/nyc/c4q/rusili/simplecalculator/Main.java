package nyc.c4q.rusili.simplecalculator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import nyc.c4q.rusili.simplecalculator.Calculations.Recursion;

public class Main extends AppCompatActivity {

    int terms = 0;
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
        sDisplay = sDisplay2 = "";
        tvMain.setText(sDisplay);
        tvHistory.setText(sDisplay2);
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
        terms++;
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
                if (terms > 2) {
                    this.calculate(tvMain.getText().toString());
                    tvMain.setText(sDisplay);
                    sDisplay2 += "=" + sDisplay;
                    tvHistory.setText(sDisplay2);
                }
                terms = 0;
        }
    }

    private void calculate(String sInput) {
        Recursion rec = new Recursion();
        sDisplay = rec.start(sInput).toString();
    }

    public void onClickOp(View v) {
        Button b = (Button)v;
        char s = b.getText().charAt(0);
        addValue(s);
        terms++;

        tvHistory.setText(sDisplay2);
        scroll.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
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

