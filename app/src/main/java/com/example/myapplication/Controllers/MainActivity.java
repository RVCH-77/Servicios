package com.example.myapplication.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.model.Saludar;
import com.example.myapplication.services.LoginService;
import com.example.myapplication.services.config;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
Button btnLogin;

EditText txtUsuario;
EditText txtContrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtUsuario = findViewById(R.id.txtUsuario);
        txtContrasenia = findViewById(R.id.txtContrasenia);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(view -> {validar();
        });


    }

    private void probarServicio() {

        // Hacer una peticiob  http con retrofit

        String URL_BASE = "http://192.168.108.218:8080/holaRest2024/api/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE).
                addConverterFactory(GsonConverterFactory.create()).build();
        LoginService loginService = retrofit.create(LoginService.class);
        Call<Saludar> call = loginService.pedirSaludo();
        call.enqueue(new Callback<Saludar>() {
            @Override
            public void onResponse(Call<Saludar> call, Response<Saludar> response) {
                if (response.code() == 200) {
                    Saludar saludo = response.body();
                    System.out.println(saludo.getResult());
                    Toast.makeText(MainActivity.this, saludo.getResult(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Saludar> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error perro", Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());
        }

        });
    }


private  void validar(){

    String usuario = String.valueOf(txtUsuario.getText());
        String contrasenia = String.valueOf(txtContrasenia.getText());
        //construir el objeto Retrofit
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(config.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create()).build();
    LoginService loginService = retrofit.create(LoginService.class);

    Call<JsonObject> validar = loginService.validar(usuario,contrasenia);
    validar.enqueue(new Callback<JsonObject>() {
        @Override
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            Toast.makeText(MainActivity.this, "realizado", Toast.LENGTH_SHORT).show();

            System.out.println(response.code());
            System.out.println(response.body());
            //Valida si fue correcto el password
            JsonObject respuesta = response.body();
            if (respuesta != null) {
                if(respuesta.has("idUsurio")){
                    int id = respuesta.get("idUsurio").getAsInt();
                    if (id > 0){
                        Toast.makeText(MainActivity.this, "correcto", Toast.LENGTH_SHORT).show();
                        Intent principal = new Intent(MainActivity.this, Principal.class);
                        startActivity(principal);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "incorrecto", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }

        @Override
        public void onFailure(Call<JsonObject> call, Throwable t) {
       Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            System.out.println(t.getStackTrace());
        }
    });
}


}
