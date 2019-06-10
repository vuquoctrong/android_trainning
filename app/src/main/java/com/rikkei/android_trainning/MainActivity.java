package com.rikkei.android_trainning;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn0)
    Button btn0;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;
    @BindView(R.id.btn6)
    Button btn6;
    @BindView(R.id.btn7)
    Button btn7;
    @BindView(R.id.btn8)
    Button btn8;
    @BindView(R.id.btn9)
    Button btn9;
    @BindView(R.id.btnQ)
    Button btnQ;
    @BindView(R.id.btnW)
    Button btnW;
    @BindView(R.id.btnE)
    Button btnE;
    @BindView(R.id.btnR)
    Button btnR;
    @BindView(R.id.btnT)
    Button btnT;
    @BindView(R.id.btnY)
    Button btnY;
    @BindView(R.id.btnU)
    Button btnU;
    @BindView(R.id.btnI)
    Button btnI;
    @BindView(R.id.btnO)
    Button btnO;
    @BindView(R.id.btnP)
    Button btnP;
    @BindView(R.id.btnA)
    Button btnA;
    @BindView(R.id.btnS)
    Button btnS;
    @BindView(R.id.btnD)
    Button btnD;
    @BindView(R.id.btnF)
    Button btnF;
    @BindView(R.id.btnG)
    Button btnG;
    @BindView(R.id.btnH)
    Button btnH;
    @BindView(R.id.btnJ)
    Button btnJ;
    @BindView(R.id.btnK)
    Button btnK;
    @BindView(R.id.btnL)
    Button btnL;
    @BindView(R.id.btnZ)
    Button btnZ;
    @BindView(R.id.btnX)
    Button btnX;
    @BindView(R.id.btnC)
    Button btnC;
    @BindView(R.id.btnV)
    Button btnV;
    @BindView(R.id.btnB)
    Button btnB;
    @BindView(R.id.btnN)
    Button btnN;
    @BindView(R.id.btnM)
    Button btnM;
    @BindView(R.id.btnSmaller)
    Button btnSmaller;
    @BindView(R.id.btnBigger)
    Button btnBigger;
    @BindView(R.id.btnDEL)
    Button btnDEL;
    @BindView(R.id.tvData)
    TextView tvData;
    @BindView(R.id.btnUp)
    Button btnUp;
    @BindView(R.id.btnDown)
    Button btnDown;
    @BindView(R.id.btnLeft)
    Button btnLeft;
    @BindView(R.id.btnRight)
    Button btnRight;
    @BindView(R.id.btnEnter)
    Button btnEnter;


    private List<Button> buttons = new ArrayList<>();
    private List<Button> controls = new ArrayList<>();
    private String dpad = "DPAD_";
    private String keycode = "KEYCODE_";
    private String textDate = "";
    private Button array[];
    private int x = 0;
    private int y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();

        btn1.setBackgroundColor(Color.GREEN);

    }

    private void init() {
        buttons.add(btn0);
        buttons.add(btn1);
        buttons.add(btn2);
        buttons.add(btn3);
        buttons.add(btn4);
        buttons.add(btn5);
        buttons.add(btn6);
        buttons.add(btn7);
        buttons.add(btn8);
        buttons.add(btn9);
        buttons.add(btnQ);
        buttons.add(btnW);
        buttons.add(btnE);
        buttons.add(btnR);
        buttons.add(btnT);
        buttons.add(btnY);
        buttons.add(btnU);
        buttons.add(btnI);
        buttons.add(btnO);
        buttons.add(btnP);
        buttons.add(btnA);
        buttons.add(btnS);
        buttons.add(btnD);
        buttons.add(btnF);
        buttons.add(btnG);
        buttons.add(btnH);
        buttons.add(btnJ);
        buttons.add(btnK);
        buttons.add(btnL);
        buttons.add(btnZ);
        buttons.add(btnX);
        buttons.add(btnC);
        buttons.add(btnV);
        buttons.add(btnB);
        buttons.add(btnN);
        buttons.add(btnM);

        controls.add(btnUp);
        controls.add(btnDown);
        controls.add(btnLeft);
        controls.add(btnRight);
        controls.add(btnEnter);
        array = new Button[]{btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0,
                btnQ, btnW, btnE, btnR, btnT, btnY, btnU, btnI, btnO, btnP,
                btnA, btnS, btnD, btnF, btnG, btnH, btnJ, btnK, btnL, btnSmaller,
                btnZ, btnX, btnC, btnV, btnB, btnN, btnM, btnBigger, btnDEL, null};
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            for (int i = 0; i < buttons.size(); i++) {
                if ((keycode + buttons.get(i).getText().toString()).equals(KeyEvent.keyCodeToString(event.getKeyCode()))) {
                    textDate = textDate + buttons.get(i).getText().toString();
                    buttons.get(i).setBackgroundColor(Color.BLUE);
                    tvData.setText(textDate);
                }
            }
            for (int i = 0; i < controls.size(); i++) {
                if ((keycode + dpad + controls.get(i).getText().toString()).equals(KeyEvent.keyCodeToString(event.getKeyCode()))
                        || (keycode + dpad + "C" + controls.get(i).getText().toString()).equals(KeyEvent.keyCodeToString(event.getKeyCode()))) {
                    controls.get(i).setBackgroundColor(Color.BLUE);
                }
            }
            control(event);

        } else if (event.getAction() == KeyEvent.ACTION_UP) {

            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                //Do something here
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setBackgroundResource(R.color.btnColor);
                }
                for (int i = 0; i < controls.size(); i++) {
                    if ((keycode + dpad + controls.get(i).getText().toString()).equals(KeyEvent.keyCodeToString(event.getKeyCode()))
                            || (keycode + dpad + "C" + controls.get(i).getText().toString()).equals(KeyEvent.keyCodeToString(event.getKeyCode()))) {
                        controls.get(i).setBackgroundResource(R.color.btnColor);
                    }
                }
            }, 500);


        }
        return super.dispatchKeyEvent(event);

    }

    private void control(KeyEvent event) {
        switch (event.getKeyCode()) {
            case 19:
                up();
                break;
            case 20:
                down();
                break;
            case 21:
                left();
                break;
            case 22:
                right();
                break;
            case 23:
                getText();
                break;
            default:
                break;
        }
    }

    private void right() {
        if(x < array.length){
            x = x + 1;
        }
        focus();
    }

    private void left() {
        if(x>0){
            x = x -1;
        }
        focus();
    }

    private void down() {
        if(x < 30){
            x = x +10;
        }
        focus();

    }

    private void up() {
        if(x>9){
            x = x - 10;
        }
        focus();
    }

    private void focus(){
        array[x].setBackgroundColor(Color.BLUE);
    }
    private void getText(){
        textDate = textDate +array[x].getText();
        tvData.setText(textDate);
    }
}
