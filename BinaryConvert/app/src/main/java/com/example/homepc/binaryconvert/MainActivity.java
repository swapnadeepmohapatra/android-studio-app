package com.example.homepc.binaryconvert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    Button button;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String no = editText.getText().toString();
                int val = Integer.parseInt(no);
                String va = Integer.toBinaryString(val);
                editText.getText().clear();
                textView.setVisibility(View.VISIBLE);
                result = val + " -> " +va;
                textView.setText(result);
            }
        });
    }
}
