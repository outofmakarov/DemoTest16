package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


public class MainActivity extends AppCompatActivity {



    private Button btnStart, btnStop;
    private MyThread myThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.start_btn);
        btnStop = findViewById(R.id.stop_btn);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myThread = new MyThread();
                myThread.isRunning = true;
                myThread.start();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myThread.isRunning = false;
            }
        });
    }
    class MyThread extends Thread{
        private boolean isRunning = true;

        @Override
        public void run() {
            int count = 0;
            super.run();
            FragmentManager fragmentManager = getSupportFragmentManager();
            while (isRunning){
                if (count % 2 == 0){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            fragmentManager.beginTransaction().replace(R.id.output_fragment, new ProceedingFragment()).commit();

                        }
                    });
                }
                else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            fragmentManager.beginTransaction().replace(R.id.output_fragment, new FirstFragment()).commit();

                        }
                    });
                }
                count++;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}