package com.example.myapplication.Controllers;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Ticket;
import com.example.myapplication.services.APITicket;
import com.example.myapplication.services.ApiClient;
import com.example.myapplication.services.config;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class lista_Ticket extends AppCompatActivity {


    private RecyclerView rvTickets;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_ticket);

        rvTickets = findViewById(R.id.rvTickets);
        progressBar = findViewById(R.id.progressBar);

        // Configurar RecyclerView
        rvTickets.setLayoutManager(new LinearLayoutManager(this));
        rvTickets.setHasFixedSize(true);

        cargarTickets();
    }

    private void cargarTickets() {
        progressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = ApiClient.getClient(config.URL_BASE);
        APITicket apiTicket = retrofit.create(APITicket.class);

        Call<List<Ticket>> call = apiTicket.obtenerTodosLosTickets();
        call.enqueue(new Callback<List<Ticket>>() {
            @Override
            public void onResponse(Call<List<Ticket>> call, Response<List<Ticket>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    // Usa tu adaptador existente o crea uno nuevo
                    TicketAdapter adapter = new TicketAdapter(response.body());
                    rvTickets.setAdapter(adapter);
                } else {
                    Toast.makeText(lista_Ticket.this,
                            "No se pudieron cargar los tickets", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Ticket>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText( lista_Ticket.this,
                        "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}