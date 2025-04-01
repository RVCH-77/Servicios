package com.example.myapplication.Controllers;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.example.myapplication.model.Empleado;
import com.example.myapplication.model.Ticket;
import com.example.myapplication.services.APITicket;
import com.example.myapplication.services.ApiCategoria;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

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

    private APITicket apiServiceTicket;

    private Button btnRegistrar;

    private int idEmpleado = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_ticket);


        idSpinner = findViewById(R.id.idSpinner);
        txtDispositivo = findViewById(R.id.txtDispositivo);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        btnRegistrar = findViewById(R.id.btnRegistrar);




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.137.1:8080/holaRest2024/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create( ApiCategoria.class);
        obtenerCategorias();

        apiServiceTicket = retrofit.create(APITicket.class);



        btnRegistrar.setOnClickListener(view -> { save();
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void save() {
        String dispositivo = txtDispositivo.getText().toString().trim();
        String descripcion = txtDescripcion.getText().toString().trim();
        Categoria categoriaSeleccionada = (Categoria) idSpinner.getSelectedItem();

        if (dispositivo.isEmpty() || descripcion.isEmpty() || categoriaSeleccionada == null) {
            Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        Ticket nuevoTicket = new Ticket();

        Empleado empleado = new Empleado(idEmpleado); // Aquí deberías obtener el ID del empleado de tu lógica
        empleado.setIdEmpleado(1);
        nuevoTicket.setEmpleado(empleado);
        nuevoTicket.setCategoria(categoriaSeleccionada);
        nuevoTicket.setDescripcion(descripcion);
        nuevoTicket.setDispositivo(dispositivo);

        // Llamar a la API para guardar el ticket
        Call<JsonObject> call = apiServiceTicket.guardarTicket(new Gson().toJson(nuevoTicket));
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegistroTicket.this, "Ticket registrado exitosamente.", Toast.LENGTH_SHORT).show();
                    txtDispositivo.setText("");
                    txtDescripcion.setText("");
                    idSpinner.setSelection(0);
                    System.out.println(call.request().toString());
                } else {
                    Toast.makeText(RegistroTicket.this, "Error al registrar el ticket. Código: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(RegistroTicket.this, "Error en la conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
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
                System.out.println("Respuesta del servidor: " + response.body() + "");
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Toast.makeText(RegistroTicket.this, "Error en la conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace(); // Log del error

                // Mandar mensaje a la consola de Android Studio
                System.out.println("Error en la conexión: " + t.getMessage());

            }
        });

    }
}

