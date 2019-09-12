package com.example.homepc.wallpaperadmin;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;

public class OnlyDataActivity extends AppCompatActivity {

    EditText editTextOnlyName;
    EditText editTextOnlyTitle;
    Button btn;
    DatabaseReference imagesRef;

    String mname;
    String mtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_data);

        btn = findViewById(R.id.button3);
        editTextOnlyName = findViewById(R.id.onlyDataName);
        editTextOnlyTitle = findViewById(R.id.onlyDataTitle);
        imagesRef = FirebaseDatabase.getInstance().getReference("images");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDataToImageNode();
            }
        });
    }

    private void sendDataToImageNode() {

        mname = editTextOnlyName.getText().toString();
        mtitle = editTextOnlyTitle.getText().toString();

        String mKey = imagesRef.child(mname).push().getKey();

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("desc", mtitle);
        hashMap.put("title", mtitle);
        hashMap.put("url", "");

        imagesRef.child(mname).child(mKey).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(OnlyDataActivity.this, "Done", Toast.LENGTH_SHORT).show();
                editTextOnlyTitle.setText("");
            }
        });
    }
}
