package io.github.swapnadeepmohapatra.instagramclone.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import io.github.swapnadeepmohapatra.instagramclone.Model.Message;
import io.github.swapnadeepmohapatra.instagramclone.R;

import static android.app.Activity.RESULT_OK;

public class NewPostFragment extends Fragment {

    private View newPostView;

    private static final int RC_PHOTO_PICKER = 2;

    private EditText postMessage;
    private ImageView postImage;
    private Button postButton;
    private Uri selectedImageUri;
    private String mUsername;

    private ProgressDialog progressDialog;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private DatabaseReference usersRef;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mChatPhotosStorageReference;
    private String mStatus;

    public NewPostFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        newPostView = inflater.inflate(R.layout.fragment_new_post, container, false);

        initialize();

        postButton = newPostView.findViewById(R.id.postBtn);
        postImage = newPostView.findViewById(R.id.postImage);
        postMessage = newPostView.findViewById(R.id.postTextMessage);

        postImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImage();
            }
        });

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (postMessage.getText().toString().trim().equals("")) {
                    Toast.makeText(getContext(), "All Fields are Compulsory", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.show();
                    post();
                }

            }
        });

        return newPostView;
    }

    private void initialize() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseStorage = FirebaseStorage.getInstance();
        usersRef = FirebaseDatabase.getInstance().getReference().child("Users");

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Posting...");
        progressDialog.setCanceledOnTouchOutside(false);

        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("messages");
        mMessagesDatabaseReference.keepSynced(true);
        mChatPhotosStorageReference = mFirebaseStorage.getReference().child("chat_photos");

        usersRef.child(Objects.requireNonNull(mFirebaseAuth.getCurrentUser()).getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("Name")) {
                    mUsername = Objects.requireNonNull(dataSnapshot.child("Name").getValue()).toString();
                    mStatus = Objects.requireNonNull(dataSnapshot.child("Status").getValue()).toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void post() {

        final StorageReference photoRef = mChatPhotosStorageReference.child(Objects.requireNonNull(selectedImageUri).getLastPathSegment());

        photoRef.putFile(selectedImageUri)
                .addOnSuccessListener(Objects.requireNonNull(getActivity()), new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //When the image has successfully uploaded, get its download URL
                        photoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                DateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
                                DateFormat tf = new SimpleDateFormat("HH:mm", Locale.UK);
                                String date = df.format(Calendar.getInstance().getTime());
                                String time = tf.format(Calendar.getInstance().getTime());

                                Message friendlyMessage = new Message(mUsername, Objects.requireNonNull(Objects.requireNonNull(mFirebaseAuth.getCurrentUser()).getPhotoUrl()).toString(), mStatus, postMessage.getText().toString(), uri.toString(), time, date);
                                mMessagesDatabaseReference.push().setValue(friendlyMessage).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            progressDialog.dismiss();
                                            Toast.makeText(getContext(), "Posted", Toast.LENGTH_SHORT).show();
                                            postImage.setImageDrawable(getResources().getDrawable(R.drawable.add_button));
                                            postMessage.setText("");
                                        }
                                    }
                                });


                            }
                        });
                    }
                });

    }

    private void loadImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK) {
            selectedImageUri = data.getData();
            postImage.setImageURI(selectedImageUri);
        }
    }
}
