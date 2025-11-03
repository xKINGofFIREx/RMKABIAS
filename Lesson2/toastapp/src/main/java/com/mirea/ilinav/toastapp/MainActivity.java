package com.mirea.ilinav.toastapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText et;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.et);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count();
            }
        });
    }
    private void count(){
        String inputText = et.getText().toString();
        int charCount = inputText.length();

        String message = "СТУДЕНТ № 6 ГРУППА БИСО-02-21" + "\n" + "Количество символов - "+ charCount;

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}