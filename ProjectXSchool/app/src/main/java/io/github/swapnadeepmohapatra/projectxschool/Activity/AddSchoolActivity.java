package io.github.swapnadeepmohapatra.projectxschool.Activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import io.github.swapnadeepmohapatra.projectxschool.R;

public class AddSchoolActivity extends AppCompatActivity {

    private EditText schoolName, schoolAddress, schoolCity, schoolState, schoolCountry;
    private DatabaseReference schoolRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_school);

        schoolRef = FirebaseDatabase.getInstance().getReference().child("School Name");

        schoolName = findViewById(R.id.schoolNameEditText);
        schoolAddress = findViewById(R.id.addressEditText);
        schoolCity = findViewById(R.id.cityEditText);
        schoolState = findViewById(R.id.stateEditText);
        schoolCountry = findViewById(R.id.countryEditText);

        Button addBtn = findViewById(R.id.addSchoolBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSchoolInDatabase();
            }
        });
    }

    private void addSchoolInDatabase() {
        HashMap<String, String> map = new HashMap<>();
        map.put("Name", schoolName.getText().toString());
        map.put("Address", schoolAddress.getText().toString());
        map.put("City", schoolCity.getText().toString());
        map.put("State", schoolState.getText().toString());
        map.put("Country", schoolCountry.getText().toString());

        schoolRef.push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AddSchoolActivity.this, "Done", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
