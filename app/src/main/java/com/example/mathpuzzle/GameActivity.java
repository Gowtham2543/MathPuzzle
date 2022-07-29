package com.example.mathpuzzle;

import java.math.*;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    TextView l1b1, l1b2, l1b3, l1b4, l1b5, l2b1, l2b2, l2b3, l2b4, l2b5, l3b1, l3b2, l3b3, l3b4,
            l3b5, l4b1, l4b2, l4b3, l4b4, l4b5, l5b1, l5b2, l5b3, l5b4, l5b5;
    TextView ans1, ans2, ans3, ans4, ans5, ans6, ans7, ans8, ans9, ans10, pop;
    Button submit, reset;
    int[] operandArray = new int[10]; // all operands will go into this array
    int[] randomNumber = new int[10];
    int randomSize = 0;
    int life = 3;
    LayoutInflater inflater;
    View popupView;
    int width, height;
    boolean focusable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        for (int i = 0; i < 10; i++) {
            randomNumber[i] = -1;
        }
        l1b1 = findViewById(R.id.line1box1);
        l1b1.setOnClickListener(this);
        l1b2 = findViewById(R.id.line1box2);
        l1b2.setOnClickListener(this);
        l1b3 = findViewById(R.id.line1box3);
        l1b3.setOnClickListener(this);
        l1b4 = findViewById(R.id.line1box4);
        l1b4.setOnClickListener(this);
        l1b5 = findViewById(R.id.line1box5);
        l1b5.setOnClickListener(this);

        l2b1 = findViewById(R.id.line2box1);
        l2b1.setOnClickListener(this);
        l2b2 = findViewById(R.id.line2box2);
        l2b2.setOnClickListener(this);
        l2b3 = findViewById(R.id.line2box3);
        l2b3.setOnClickListener(this);
        l2b4 = findViewById(R.id.line2box4);
        l2b4.setOnClickListener(this);
        l2b5 = findViewById(R.id.line2box5);
        l2b5.setOnClickListener(this);

        l3b1 = findViewById(R.id.line3box1);
        l3b1.setOnClickListener(this);
        l3b2 = findViewById(R.id.line3box2);
        l3b2.setOnClickListener(this);
        l3b3 = findViewById(R.id.line3box3);
        l3b3.setOnClickListener(this);
        l3b4 = findViewById(R.id.line3box4);
        l3b4.setOnClickListener(this);
        l3b5 = findViewById(R.id.line3box5);
        l3b5.setOnClickListener(this);

        l4b1 = findViewById(R.id.line4box1);
        l4b1.setOnClickListener(this);
        l4b2 = findViewById(R.id.line4box2);
        l4b2.setOnClickListener(this);
        l4b3 = findViewById(R.id.line4box3);
        l4b3.setOnClickListener(this);
        l4b4 = findViewById(R.id.line4box4);
        l4b4.setOnClickListener(this);
        l4b5 = findViewById(R.id.line4box5);
        l4b5.setOnClickListener(this);

        l5b1 = findViewById(R.id.line5box1);
        l5b1.setOnClickListener(this);
        l5b2 = findViewById(R.id.line5box2);
        l5b2.setOnClickListener(this);
        l5b3 = findViewById(R.id.line5box3);
        l5b3.setOnClickListener(this);
        l5b4 = findViewById(R.id.line5box4);
        l5b4.setOnClickListener(this);
        l5b5 = findViewById(R.id.line5box5);
        l5b5.setOnClickListener(this);

        ans1 = findViewById(R.id.answer1);
        ans1.setOnClickListener(this);
        ans2 = findViewById(R.id.answer2);
        ans2.setOnClickListener(this);
        ans3 = findViewById(R.id.answer3);
        ans3.setOnClickListener(this);
        ans4 = findViewById(R.id.answer4);
        ans4.setOnClickListener(this);
        ans5 = findViewById(R.id.answer5);
        ans5.setOnClickListener(this);
        ans6 = findViewById(R.id.answer6);
        ans6.setOnClickListener(this);
        ans7 = findViewById(R.id.answer7);
        ans7.setOnClickListener(this);
        ans8 = findViewById(R.id.answer8);
        ans8.setOnClickListener(this);
        ans9 = findViewById(R.id.answer9);
        ans9.setOnClickListener(this);
        ans10 = findViewById(R.id.answer10);
        ans10.setOnClickListener(this);


        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        popupView = inflater.inflate(R.layout.popup_window, null);
        width = LinearLayout.LayoutParams.WRAP_CONTENT;
        height = LinearLayout.LayoutParams.WRAP_CONTENT;
        focusable = true;

        pop = popupView.findViewById(R.id.popupt);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(this);

        reset = findViewById(R.id.reset);
        reset.setOnClickListener(this);

        int[] operator = new int[5];
        for (int i = 0; i < 5; i++) {
            operator[i] = (int) (Math.random() * (4) + 1);
        }
        setOperator(l1b2, operator[0]);
        setOperator(l2b2, operator[1]);
        setOperator(l3b2, operator[2]);
        setOperator(l4b2, operator[3]);
        setOperator(l5b2, operator[4]);
        int[][] matrix = new int[5][4];
        for (int i = 0; i < 5; i++) {
            matrix[i][1] = operator[i];
            matrix[i][0] = (int) (Math.random() * (10) + 1); //56
            if (operator[i] == 4) {
                matrix[i][2] = (int) (Math.random() * (matrix[i][0]) + 1); //30
                int temp = (int) (matrix[i][0] / matrix[i][2]); //1.86 -> 1
                matrix[i][0] = (temp * matrix[i][2]) * ((int) (Math.random() * (5) + 1));
            } else {
                matrix[i][2] = (int) (Math.random() * (10) + 1);
            }
            matrix[i][3] = findOperator(matrix[i][0], matrix[i][2], matrix[i][1]);
        }
        setAns(l1b5, matrix[0][3]);
        setAns(l2b5, matrix[1][3]);
        setAns(l3b5, matrix[2][3]);
        setAns(l4b5, matrix[3][3]);
        setAns(l5b5, matrix[4][3]);
        int size = 0;
        for (int i = 0; i < 5; i++) {
            operandArray[size] = matrix[i][0];
            size++;
            operandArray[size] = matrix[i][2];
            size++;
        }
        setOperand(ans1);
        setOperand(ans2);
        setOperand(ans3);
        setOperand(ans4);
        setOperand(ans5);
        setOperand(ans6);
        setOperand(ans7);
        setOperand(ans8);
        setOperand(ans9);
        setOperand(ans10);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        String temp = new String();
        switch (v.getId()) {
            case R.id.answer1:
                temp = (String) ans1.getText();
                chooseAns(ans1, temp);
                break;
            case R.id.answer2:
                temp = (String) ans2.getText();
                chooseAns(ans2, temp);
                break;
            case R.id.answer3:
                temp = (String) ans3.getText();
                chooseAns(ans3, temp);
                break;
            case R.id.answer4:
                temp = (String) ans4.getText();
                chooseAns(ans4, temp);
                break;
            case R.id.answer5:
                temp = (String) ans5.getText();
                chooseAns(ans5, temp);
                break;
            case R.id.answer6:
                temp = (String) ans6.getText();
                chooseAns(ans6, temp);
                break;
            case R.id.answer7:
                temp = (String) ans7.getText();
                chooseAns(ans7, temp);
                break;
            case R.id.answer8:
                temp = (String) ans8.getText();
                chooseAns(ans8, temp);
                break;
            case R.id.answer9:
                temp = (String) ans9.getText();
                chooseAns(ans9, temp);
                break;
            case R.id.answer10:
                temp = (String) ans10.getText();
                chooseAns(ans10, temp);
                break;
            case R.id.submit:
                check();
                break;
            case R.id.reset:
                reset();
                break;
            default:

        }
    }


    //Checks whether the given input is valid and correct
    public void check() {
        if (l1b1.getText().toString().equals("") || l1b3.getText().toString().equals("") || l2b1.getText().toString().equals("") || l2b3.getText().toString().equals("") || l3b1.getText().toString().equals("") || l3b3.getText() == "" || l4b1.getText().toString().equals("") || l4b3.getText() == "" || l5b1.getText().toString().equals("") || l5b3.getText().toString().equals("")) {
            Toast.makeText(GameActivity.this, "Fill all the box", Toast.LENGTH_LONG).show();
        }
        else {

            final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
            Test view = new Test(getApplicationContext());

            if(checkAns(Integer.parseInt(l1b1.getText().toString()), Integer.parseInt(l1b3.getText().toString()), Integer.parseInt(l1b5.getText().toString()), l1b2.getText().toString()) && checkAns(Integer.parseInt(l2b1.getText().toString()), Integer.parseInt(l2b3.getText().toString()), Integer.parseInt(l2b5.getText().toString()), l2b2.getText().toString()) &&
                checkAns(Integer.parseInt(l3b1.getText().toString()), Integer.parseInt(l3b3.getText().toString()), Integer.parseInt(l3b5.getText().toString()), l3b2.getText().toString()) && checkAns(Integer.parseInt(l4b1.getText().toString()), Integer.parseInt(l4b3.getText().toString()), Integer.parseInt(l4b5.getText().toString()), l4b2.getText().toString()) && checkAns(Integer.parseInt(l5b1.getText().toString()),
                Integer.parseInt(l5b3.getText().toString()), Integer.parseInt(l5b5.getText().toString()), l5b2.getText().toString()))
            {

                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
                Intent intent = new Intent();
                intent.putExtra("Next Round", "Next Round");
                setResult(RESULT_OK, intent);
                finish();
            }
            else
            {
                if(life > 1)
                {
                    life--;
                    pop.setText("You have lost. You still have " + life + " lives");
                    popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                    popupView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            popupWindow.dismiss();
                            return true;
                        }
                    });
                    reset();
                }
                else
                {
                    pop.setText("You have lost");
                    popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                    Intent intent = new Intent(GameActivity.this, MainActivity.class);
                    startActivity(intent);
                    popupView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            popupWindow.dismiss();
                            return true;
                        }
                    });
                }
            }
        }
    }
    public boolean checkAns(int a, int b, int c, String s)
    {
        if(s.equals("+"))
        {
            if(a + b == c)
            {
                return true;
            }
            else
            {
                return  false;
            }
        }
        else if(s.equals("-"))
        {

            if(a - b == c)
            {
                //System.out.println(a + s + b + " " + c);
                return true;
            }
            else
            {
                return  false;
            }
        }
        else if(s.equals("*"))
        {

            if(a * b == c)
            {
                return true;
            }
            else
            {
                return  false;
            }
        }
        else
        {
            if(a / b == c)
            {
                return true;
            }
            else
            {
                return  false;
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    public void reset()
    {
        l1b1.setText("");
        l1b3.setText("");
        l2b1.setText("");
        l2b3.setText("");
        l3b1.setText("");
        l3b3.setText("");
        l4b1.setText("");
        l4b3.setText("");
        l5b1.setText("");
        l5b3.setText("");
        ans1.setBackgroundColor(Color.parseColor("#D3D3D3"));
        ans2.setBackgroundColor(Color.parseColor("#D3D3D3"));
        ans3.setBackgroundColor(Color.parseColor("#D3D3D3"));
        ans4.setBackgroundColor(Color.parseColor("#D3D3D3"));
        ans5.setBackgroundColor(Color.parseColor("#D3D3D3"));
        ans6.setBackgroundColor(Color.parseColor("#D3D3D3"));
        ans7.setBackgroundColor(Color.parseColor("#D3D3D3"));
        ans8.setBackgroundColor(Color.parseColor("#D3D3D3"));
        ans9.setBackgroundColor(Color.parseColor("#D3D3D3"));
        ans10.setBackgroundColor(Color.parseColor("#D3D3D3"));
    }
    int colorcode = 0;
    private void chooseAns(TextView t, String temp) {
        l1b1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (l1b1.getText().toString().equals("")) {
                    if(t.getBackground() instanceof  ColorDrawable)
                    {
                        ColorDrawable cd = (ColorDrawable) t.getBackground();
                        colorcode = cd.getColor();
                    }
                    if(colorcode == Color.parseColor("#D3D3D3"))
                    {
                        l1b1.setText(temp);
                        t.setBackgroundColor(Color.parseColor("#ADD8E6"));
                    }
                }
            }
        });
        l1b3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (l1b3.getText().toString().equals("")) {
                    if(t.getBackground() instanceof  ColorDrawable)
                    {
                        ColorDrawable cd = (ColorDrawable) t.getBackground();
                        colorcode = cd.getColor();
                    }
                    if(colorcode == Color.parseColor("#D3D3D3"))
                    {
                        l1b3.setText(temp);
                        t.setBackgroundColor(Color.parseColor("#ADD8E6"));
                    }
                }
            }
        });
        l2b1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (l2b1.getText().toString().equals("")) {
                    if(t.getBackground() instanceof  ColorDrawable)
                    {
                        ColorDrawable cd = (ColorDrawable) t.getBackground();
                        colorcode = cd.getColor();
                    }
                    if(colorcode == Color.parseColor("#D3D3D3"))
                    {
                        l2b1.setText(temp);
                        t.setBackgroundColor(Color.parseColor("#ADD8E6"));
                    }
                }
            }
        });
        l2b3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (l2b3.getText().toString().equals("")) {
                    if(t.getBackground() instanceof  ColorDrawable)
                    {
                        ColorDrawable cd = (ColorDrawable) t.getBackground();
                        colorcode = cd.getColor();
                    }
                    if(colorcode == Color.parseColor("#D3D3D3"))
                    {
                        l2b3.setText(temp);
                        t.setBackgroundColor(Color.parseColor("#ADD8E6"));
                    }
                }
            }
        });
        l3b1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (l3b1.getText().toString().equals("")) {
                    if(t.getBackground() instanceof  ColorDrawable)
                    {
                        ColorDrawable cd = (ColorDrawable) t.getBackground();
                        colorcode = cd.getColor();
                    }
                    if(colorcode == Color.parseColor("#D3D3D3"))
                    {
                        l3b1.setText(temp);
                        t.setBackgroundColor(Color.parseColor("#ADD8E6"));
                    }
                }
            }
        });
        l3b3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (l3b3.getText().toString().equals("")) {
                    if(t.getBackground() instanceof  ColorDrawable)
                    {
                        ColorDrawable cd = (ColorDrawable) t.getBackground();
                        colorcode = cd.getColor();
                    }
                    if(colorcode == Color.parseColor("#D3D3D3"))
                    {
                        l3b3.setText(temp);
                        t.setBackgroundColor(Color.parseColor("#ADD8E6"));
                    }
                }
            }
        });
        l4b1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (l4b1.getText().toString().equals("")) {
                    if(t.getBackground() instanceof  ColorDrawable)
                    {
                        ColorDrawable cd = (ColorDrawable) t.getBackground();
                        colorcode = cd.getColor();
                    }
                    if(colorcode == Color.parseColor("#D3D3D3"))
                    {
                        l4b1.setText(temp);
                        t.setBackgroundColor(Color.parseColor("#ADD8E6"));
                    }
                }
            }
        });
        l4b3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (l4b3.getText().toString().equals("")) {
                    if(t.getBackground() instanceof  ColorDrawable)
                    {
                        ColorDrawable cd = (ColorDrawable) t.getBackground();
                        colorcode = cd.getColor();
                    }
                    if(colorcode == Color.parseColor("#D3D3D3"))
                    {
                        l4b3.setText(temp);
                        t.setBackgroundColor(Color.parseColor("#ADD8E6"));
                    }
                }
            }
        });
        l5b1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (l5b1.getText().toString().equals("")) {
                    if(t.getBackground() instanceof  ColorDrawable)
                    {
                        ColorDrawable cd = (ColorDrawable) t.getBackground();
                        colorcode = cd.getColor();
                    }
                    if(colorcode == Color.parseColor("#D3D3D3"))
                    {
                        l5b1.setText(temp);
                        t.setBackgroundColor(Color.parseColor("#ADD8E6"));
                    }

                }
            }
        });
        l5b3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (l5b3.getText().toString().equals("")) {
                    if(t.getBackground() instanceof  ColorDrawable)
                    {
                        ColorDrawable cd = (ColorDrawable) t.getBackground();
                        colorcode = cd.getColor();
                    }
                    if(colorcode == Color.parseColor("#D3D3D3"))
                    {
                        l5b3.setText(temp);
                        t.setBackgroundColor(Color.parseColor("#ADD8E6"));
                    }
                }
            }
        });
    }

    public void setOperator(TextView t, int i) {
        String operator = new String();
        switch (i) {
            case 1:
                operator = "+";
                break;
            case 2:
                operator = "-";
                break;
            case 3:
                operator = "*";
                break;
            case 4:
                operator = "/";
                break;
            default:
        }
        t.setText(operator);
    }

    int findOperator(int a, int b, int o) {
        int ans = 0;
        switch (o) {
            case 1:
                ans = a + b;
                break;
            case 2:
                ans = a - b;
                break;
            case 3:
                ans = a * b;
                break;
            case 4:
                ans = a / b;
                break;
            default:

        }
        return ans;
    }

    public void setAns(TextView t, int i) {
        t.setText(Integer.toString(i));
    }
    public boolean search(int number) {
        for (int element : randomNumber) {
            if (element == number) {
                return true;
            }
        }
        return false;
    }
    public void setOperand(TextView t) {
        int number = 0;
        boolean flag = true;
        do {
            int pos = (int) (Math.random() * (10)); //generates a random number between 0 and 9
            if (search(pos) == false) {
                number = pos;
                randomNumber[randomSize] = pos;
                randomSize++; // + 1
                flag = false;
            }
        }
        while (flag);
        t.setText(Integer.toString(operandArray[number]));
    }
}