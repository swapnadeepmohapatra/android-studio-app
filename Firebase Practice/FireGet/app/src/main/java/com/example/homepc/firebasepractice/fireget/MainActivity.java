package com.example.homepc.firebasepractice.fireget;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView name, phone, email, message;
    String mName, mPhone, mEmail, mMessage;
    Button button;
    HashMap<String, String> map = new HashMap<>();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = database.getReference();
    DatabaseReference reference = rootRef.child("Message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editText);
        phone = findViewById(R.id.editText4);
        email = findViewById(R.id.editText3);
        message = findViewById(R.id.editText2);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mName = name.getText().toString();
                mEmail = email.getText().toString();
                mMessage = message.getText().toString();
                mPhone = phone.getText().toString();

                map.put("Name", mName);
                map.put("Phone", mPhone);
                map.put("Email", mEmail);
                map.put("Message", mMessage);

                reference.push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}
