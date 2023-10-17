package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrarseActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private EditText correoregistrarse;
    private EditText contrasenaregistrar;
    private EditText contrasenaconfirmacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        auth = FirebaseAuth.getInstance();

        correoregistrarse = findViewById(R.id.correoregistrarse);
        contrasenaregistrar = findViewById(R.id.contrasenaregistrarse);
        contrasenaconfirmacion = findViewById(R.id.contrasenaConfirmacion);
    }



    public void registrarUsuario(View view) {
        String email = correoregistrarse.getText().toString().trim();
        String contrasena = contrasenaregistrar.getText().toString();
        String confirmacion = contrasenaconfirmacion.getText().toString();

        if (contrasena.equals(confirmacion)) {
            auth.createUserWithEmailAndPassword(email, contrasena)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Usuario Creado", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = auth.getCurrentUser();
                                if (user != null) {
                                    // Si se crea el usuario correctamente, redirige a la actividad principal.
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    finish();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Fallo en la autenticación", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }
    }
}
