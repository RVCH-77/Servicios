package com.example.myapplication.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class Principal extends AppCompatActivity {

    ImageButton btnRegistroTicket;

    ImageButton btnLista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);


        btnRegistroTicket = findViewById(R.id.idRegistroTicket);

        btnRegistroTicket.setOnClickListener(view -> {
            Intent registroTicket = new Intent(Principal.this, RegistroTicket.class);
            startActivity(registroTicket);
        });

        btnLista = findViewById(R.id.idLista);

        btnLista.setOnClickListener(view -> {
            Intent listaTicket = new Intent(Principal.this, lista_Ticket.class);
            startActivity(listaTicket);
        });









        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}