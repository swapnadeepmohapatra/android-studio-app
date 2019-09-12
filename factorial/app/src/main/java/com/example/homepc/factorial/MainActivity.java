package com.example.homepc.factorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText number;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int factorial = 1;
                int num = 0;
                if (!number.getText().toString().equals(""))
                    num = Integer.parseInt(number.getText().toString());
                if (num >= 20) {
                    Toast.makeText(MainActivity.this, "Cannot Resolve Because The Developer is Lazy and could not code further...", Toast.LENGTH_LONG).show();
                } else {
                    for (int i = 1; i <= num; i++) {
                        factorial = i * factorial;
                    }
                    String fac = "The Factorial of " + num + " is "+String.valueOf(factorial);

                    textView.setText(fac);
                    textView.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
