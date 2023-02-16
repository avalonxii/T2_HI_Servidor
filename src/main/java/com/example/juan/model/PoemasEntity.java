package com.example.juan.model;

import jakarta.persistence.*;

import java.sql.Date;
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
    @Column(name = "fecha")
    private Date fecha;
    @Basic
    @Column(name = "autor")
    private Integer autor;

    @ManyToOne
    @JoinColumn(name = "autor", referencedColumnName = "id")
    private UsuariosEntity usuariosByAutor;


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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getAutor() {
        return autor;
    }

    public void setAutor(Integer autor) {
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

    public UsuariosEntity getUsuariosByAutor() {
        return usuariosByAutor;
    }

    public void setUsuariosByAutor(UsuariosEntity usuariosByAutor) {
        this.usuariosByAutor = usuariosByAutor;
    }
}
