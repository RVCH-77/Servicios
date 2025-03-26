package com.example.myapplication.Controllers;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.model.Categoria;
import com.example.myapplication.services.ApiCategoria;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistroTicket extends AppCompatActivity {

    private Spinner idSpinner;
    private EditText txtDispositivo;
    private EditText txtDescripcion;
    private ApiCategoria apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_ticket);


        idSpinner = findViewById(R.id.idSpinner);
        txtDispositivo = findViewById(R.id.txtDispositivo);
        txtDescripcion = findViewById(R.id.txtDescripcion);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.137.1:8080/holaRest2024/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create( ApiCategoria.class);
        obtenerCategorias();



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void obtenerCategorias() {
        Call<List<Categoria>> call = apiService.obtenerCategorias();
        call.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Categoria> categorias = response.body();
                    ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(RegistroTicket.this,
                            android.R.layout.simple_spinner_item, categorias);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    idSpinner.setAdapter(adapter);
                } else {
                    // Manejo de respuestas no exitosas
                    Toast.makeText(RegistroTicket.this, "No se pudieron obtener categorías. Código: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Toast.makeText(RegistroTicket.this, "Error en la conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace(); // Log del error
            }
        });
    }
}