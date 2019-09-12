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

public class ImagesActivity extends AppCompatActivity {


    StorageReference mStorage;
    Button button;
    ImageView imageView;
    EditText editTextName;
    EditText editTextTitle;
    ProgressDialog progressDialog;
    DatabaseReference imagesRef;

    private static final int GALLERY = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);


        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);
        editTextName = findViewById(R.id.name);
        editTextTitle = findViewById(R.id.title);
        mStorage = FirebaseStorage.getInstance().getReference();
        imagesRef = FirebaseDatabase.getInstance().getReference("images");


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
            final StorageReference fileName = mStorage.child("images/" + editTextName.getText().toString() + "/" + editTextTitle.getText().toString() + ".png");

            fileName.putFile(Objects.requireNonNull(uri)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(ImagesActivity.this, "UPLOAD COMPLETE", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                    fileName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri downloadUrl) {

                            String key = imagesRef.child(name).push().getKey();

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("desc", editTextTitle.getText().toString());
                            hashMap.put("title", editTextTitle.getText().toString());
                            hashMap.put("url", downloadUrl.toString());

                            imagesRef.child(name).child(Objects.requireNonNull(key)).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(ImagesActivity.this, "Done", Toast.LENGTH_SHORT).show();
                                    editTextTitle.setText("");
                                    imageView.setImageDrawable(getDrawable(R.drawable.common_google_signin_btn_icon_dark));
                                }
                            });
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ImagesActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    public void plainData(View view) {
        Intent plainActIntent = new Intent(ImagesActivity.this, OnlyDataActivity.class);
        startActivity(plainActIntent);
    }
}
