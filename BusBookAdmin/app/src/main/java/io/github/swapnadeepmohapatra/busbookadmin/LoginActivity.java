package io.github.swapnadeepmohapatra.busbookadmin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText pass;
    Button button;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = findViewById(R.id.email_sign_in_button);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);

        firebaseAuth = FirebaseAuth.getInstance();

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(email.getText().equals(" ") || pass.getText().equals(" "))) {
//                    Toast.makeText(LoginActivity.this, email.getText().toString() + " " + pass.getText().toString(), Toast.LENGTH_SHORT).show();
                    firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("", "createUserWithEmail:success");
                                        FirebaseUser user = firebaseAuth.getCurrentUser();
                                        Toast.makeText(LoginActivity.this,"Welcome Admin! -> "+ user.getEmail(), Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(LoginActivity.this, task.getException().toString(), Toast.LENGTH_LONG).show();
                                        email.setText("");
                                        pass.setText("");
                                    }
                                }
                            });
                } else {
                    Toast.makeText(LoginActivity.this, "Enter Proper Password and Mail ID", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

