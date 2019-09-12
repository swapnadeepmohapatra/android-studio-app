package com.example.homepc.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;




public class MainActivity extends AppCompatActivity {

    EditText et1,et2 ;
    Button b1,b2,b3,b4;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText)findViewById(R.id.firstnumber);

        et2 = (EditText)findViewById(R.id.secondnumber);
        b1 = (Button)findViewById(R.id.addition);
        b2 = (Button)findViewById(R.id.subtraction);
        b3 = (Button)findViewById(R.id.multiplication);
        b4 = (Button)findViewById(R.id.division);

        tv=(TextView)findViewById(R.id.result);

        b1.setOnClickListener(new View.onClickListener(){
            @Override
            public void onClick (View view){
                String n1=et1.getText().toString();
                String n2=et2.getText().toString();
                if(n1..equals)
});
});


    }
}
