package com.example.myapplication.model;

public class Ticket {

    private int idTicket;

    private Empleado empleado;


    private String descripcion;

    private String dispositivo;

    private String fecha;

    private char estatus;

    private String fechaAtencion;
    private Categoria categoria;


    public Ticket() {
    }


    public Ticket(int idTicket, Empleado empleado, Categoria categoria, String descripcion, String dispositivo, String fecha, char estatus, String fechaAtencion) {
        this.idTicket = idTicket;
        this.empleado = empleado;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.dispositivo = dispositivo;
        this.fecha = fecha;
        this.estatus = estatus;
        this.fechaAtencion = fechaAtencion;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public char getEstatus() {
        return estatus;
    }

    public void setEstatus(char estatus) {
        this.estatus = estatus;
    }

    public String getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(String fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }


}
