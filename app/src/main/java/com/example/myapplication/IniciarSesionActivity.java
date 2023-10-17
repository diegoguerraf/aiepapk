package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class IniciarSesionActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private EditText correo;
    private EditText contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        auth = FirebaseAuth.getInstance();

        correo = findViewById(R.id.correo);
        contrasena = findViewById(R.id.contrasena);
    }


    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        //updateUI(currentUser);
    }

    public void iniciarSesion(View view){
        auth.signInWithEmailAndPassword(correo.getText().toString(), contrasena.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Log.d(TAG, "");
                            FirebaseUser user = auth.getCurrentUser();
                            Intent i = new Intent(getApplicationContext(), MapActivity.class);
                            startActivity(i);
                            //updateUI(user);
                        }else {
                            //Log.w(TAG,"",task.getException());
                            Toast.makeText(IniciarSesionActivity.this,"Usuario o contraseña incorrecta",Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }



}