package com.example.myapplication.Controllers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Ticket;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {

    private List<Ticket> tickets;

  public TicketAdapter(List<Ticket>tickets){this.tickets=tickets;}

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_ticket, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        Ticket ticket = tickets.get(position);
        holder.bind(ticket);
    }

    @Override
    public int getItemCount() {
        return tickets != null ? tickets.size() : 0;
    }

    public static class TicketViewHolder extends RecyclerView.ViewHolder {
        private TextView tvId, tvDispositivo, tvDescripcion, tvEstado;

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvIdTicket);
            tvDispositivo = itemView.findViewById(R.id.tvDispositivo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvEstado = itemView.findViewById(R.id.tvEstado);
        }

        public void bind(Ticket ticket) {
            tvId.setText("Ticket #" + ticket.getIdTicket());
            tvDispositivo.setText("Dispositivo: " + ticket.getDispositivo());
            tvDescripcion.setText(ticket.getDescripcion());
            tvEstado.setText("Estado: " + ticket.getEstatus());
        }
    }
}