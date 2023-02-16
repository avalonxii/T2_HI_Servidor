package com.example.juan.model;

import jakarta.persistence.*;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "poemas", schema = "t2_hi_servidor", catalog = "")
public class PoemasEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "titulo")
    private String titulo;
    @Basic
    @Column(name = "contenido")
    private String contenido;

    @Basic
    @Column(name = "imagen")
    private String imagen;
    @Basic
    @Column(name = "fecha")
    private Date fecha = new Date();
    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private UsuariosEntity autor;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getFecha() {
        return fecha;
    }

//    public void setFecha(Date fecha) {
//        this.fecha = fecha;
//    }

    public UsuariosEntity getAutor() {
        return autor;
    }

    public void setAutor(UsuariosEntity autor) {
        this.autor = autor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoemasEntity that = (PoemasEntity) o;
        return id == that.id && Objects.equals(titulo, that.titulo) && Objects.equals(contenido, that.contenido) && Objects.equals(fecha, that.fecha) && Objects.equals(autor, that.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, contenido, fecha, autor);
    }

    public String getDia() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        return formatter.format(fecha);
    }

    public String getMes() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM");
        return formatter.format(fecha);
    }

    public  String getYear(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        return formatter.format(fecha);
    }
}
