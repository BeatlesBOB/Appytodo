package com.example.testfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Map;

public class Registre extends AppCompatActivity {

    private static final String TAG ="";
    EditText user, password, confirmPass;
    private FirebaseAuth mAuth;
    private  FirebaseFirestore db;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);
        mAuth = FirebaseAuth.getInstance();
        user = (EditText) findViewById(R.id.txtUser);
        password = (EditText) findViewById(R.id.txtPassword);
        confirmPass = (EditText) findViewById(R.id.txtPassword2);

        db = FirebaseFirestore.getInstance();

    }

    public void Annuler (View view){
        Intent intent = new Intent(Registre.this, MainActivity.class);
        startActivity(intent);
    }

    public void Registre (View view) {
        String User = user.getText().toString();
        String pass = password.getText().toString();
        String pass2 = confirmPass.getText().toString();
        if ((User.length() != 0) && (pass.length() != 0) && (pass2.length() != 0))
        {

            if (pass.equals(pass2)) {

                mAuth.createUserWithEmailAndPassword(User, pass)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                    Toast.makeText(Registre.this, "Creation reussi.",
                                            Toast.LENGTH_SHORT).show();



                                    if (user != null) {
                                        // Name, email address, and profile photo Url
                                        String email = user.getEmail();


                                        // The user's ID, unique to the Firebase project. Do NOT use this value to
                                        // authenticate with your backend server, if you have one. Use
                                        // FirebaseUser.getIdToken() instead.
                                        String uid = user.getUid();
                                        Log.d("uid",uid);

                                        Map<String, Object> User = new HashMap<>();
                                        User.put("firstName", "");
                                        User.put("lastName", email);
                                        User.put("age", "22");

                                        db.collection("users").document(uid)
                                                .set(User)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Log.d(TAG, "DocumentSnapshot successfully written!");
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w(TAG, "Error writing document", e);
                                                    }
                                                });
                                    }

                                    Intent intent = new Intent(Registre.this, MainActivity.class);
                                    startActivity(intent);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Registre.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }

                                // ...
                            }
                        });

            } else {
                Toast.makeText(Registre.this, "Ereur dans la confirmation de mot de passe.",
                        Toast.LENGTH_SHORT).show();
                password.setText("");
                confirmPass.setText("");
            }
        }
    }

    private void updateUI(FirebaseUser user) {
    }
}
