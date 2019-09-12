package com.example.homepc.wallpaperadmin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    StorageReference mStorage;
    Button button;
    ImageView imageView;
    EditText editTextName;
    DatabaseReference categoriesRef;
    ProgressDialog progressDialog;

    private static final int GALLERY = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);
        editTextName = findViewById(R.id.name);
        mStorage = FirebaseStorage.getInstance().getReference();
        categoriesRef = FirebaseDatabase.getInstance().getReference("categories");

        progressDialog = new ProgressDialog(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");

                startActivityForResult(intent, GALLERY);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY) {
            progressDialog.setMessage("UPLOADING....");
            Uri uri = data.getData();
            progressDialog.show();

            imageView.setImageURI(uri);

            final String name = editTextName.getText().toString();
            final StorageReference fileName = mStorage.child("thumbnails/" + name + ".png");

            fileName.putFile(Objects.requireNonNull(uri)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(MainActivity.this, "UPLOAD COMPLETE", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                    fileName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri downloadUrl) {

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("desc", name);
                            hashMap.put("thumbnail", downloadUrl.toString());

                            categoriesRef.child(name).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                                    editTextName.setText("");
                                    imageView.setImageDrawable(getDrawable(R.drawable.fui_idp_button_background_facebook));
                                }
                            });
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    public void images(View view) {
        Intent imagesActIntent = new Intent(MainActivity.this, ImagesActivity.class);
        startActivity(imagesActIntent);
    }
}























