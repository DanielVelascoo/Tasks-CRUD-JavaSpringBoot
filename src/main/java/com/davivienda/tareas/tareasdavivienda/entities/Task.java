package com.davivienda.tareas.tareasdavivienda.entities;

import java.sql.Date; // Usamos java.sql.Date para la fecha de expiración
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Date expiration_date;

    // Constructor sin parámetros (necesario para JPA)
    public Task() {}

    // Constructor con parámetros
    public Task(Long id, String title, String description, Date expiration_date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.expiration_date = expiration_date;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }
}
