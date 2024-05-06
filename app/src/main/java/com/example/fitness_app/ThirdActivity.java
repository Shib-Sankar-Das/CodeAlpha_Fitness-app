package com.example.fitness_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThirdActivity extends AppCompatActivity {

    String buttonvalue;
    Button startBtn;
    private CountDownTimer countDownTimer;
    TextView mtextview;
    private boolean MTimeRunning;
    private long MTimeLeftinmills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_third);

        Intent intent = getIntent();
        buttonvalue = intent.getStringExtra("value");

        int intvalue = Integer.valueOf(buttonvalue);


        switch (intvalue){

            case 1:
                setContentView(R.layout.activity_bow);
                break;

            case 2:
                setContentView(R.layout.activity_bridge);
                break;

            case 3:
                setContentView(R.layout.activity_chair);
                break;

            case 4:
                setContentView(R.layout.activity_child);
                break;

            case 5:
                setContentView(R.layout.activity_cobbler);
                break;

            case 6:
                setContentView(R.layout.activity_cow);
                break;

            case 7:
                setContentView(R.layout.activity_playji);
                break;

            case 8:
                setContentView(R.layout.activity_pauseji);
                break;

            case 9:
                setContentView(R.layout.activity_plank);
                break;

            case 10:
                setContentView(R.layout.activity_chrunches);
                break;

            case 11:
                setContentView(R.layout.activity_situp);
                break;

            case 12:
                setContentView(R.layout.activity_rotation);
                break;

            case 13:
                setContentView(R.layout.activity_twist);
                break;

            case 14:
                setContentView(R.layout.activity_windmill);
                break;

            case 15:
                setContentView(R.layout.activity_legup);
                break;

        }


        startBtn = findViewById(R.id.startbutton);
        mtextview = findViewById(R.id.time);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MTimeRunning)
                {
                    stoptimer();
                }
                else
                {
                    startTimer();
                }
                }
        });


    }

    private void stoptimer()
    {
        countDownTimer.cancel();
        MTimeRunning=false;
        startBtn.setText("START");
    }

    private void startTimer()
    {
        final CharSequence value1 = mtextview.getText();
        String num1 = value1.toString();
        String num2 = num1.substring(0,2);
        String num3 = num1.substring(3,5);


        final int number = Integer.valueOf(num2) * 60 + Integer.valueOf(num3);
        MTimeLeftinmills = number*1000;


        countDownTimer = new CountDownTimer(MTimeLeftinmills,1000) {
            @Override
            public void onTick(long l) {

                MTimeLeftinmills = l;
                updateTimer();

            }

            @Override
            public void onFinish() {

                int newvalue = Integer.valueOf(buttonvalue);
                if(newvalue<=7){

                    Intent intent = new Intent(ThirdActivity.this, ThirdActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value",String.valueOf(newvalue));
                    startActivity(intent);

                }

                else{

                    newvalue = 1;
                    Intent intent = new Intent(ThirdActivity.this, ThirdActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value",String.valueOf(newvalue));
                    startActivity(intent);

                }

            }
        }.start();
        startBtn.setText("Pause");
        MTimeRunning=true;
    }

    private void updateTimer()
    {
        int minutes = (int)MTimeLeftinmills/60000;
        int seconds = (int)MTimeLeftinmills%60000 / 1000;

        String timeLeftText="";
        if(minutes<10)
            timeLeftText="0";
        timeLeftText = timeLeftText+minutes+":";
        if (seconds<10)
            timeLeftText = "0";
        timeLeftText += seconds;
        mtextview.setText(timeLeftText);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}