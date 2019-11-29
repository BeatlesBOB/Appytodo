package com.example.testfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testfirebase.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText user, password;
    private static  final String TAG = " ";
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        user = (EditText) findViewById(R.id.textEmail);
        password = (EditText) findViewById(R.id.textPassword);

        mDatabase = FirebaseDatabase.getInstance().getReference();


    }

    public void Registre (View view){
        Intent intent = new Intent(MainActivity.this, Registre.class);
        startActivity(intent);
    }

    public void Connexion (View view) {

        String utilisateur = user.getText().toString();
        String passe = password.getText().toString();

        if ((utilisateur.length() != 0 ) && (passe.length() != 0))
        {

            mAuth.signInWithEmailAndPassword(utilisateur, passe)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                                Toast.makeText(MainActivity.this, "Authentication succeess.",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }

                            // ...
                        }
                    });

        }
    }


    private void updateUI(FirebaseUser user) {
    }


}
