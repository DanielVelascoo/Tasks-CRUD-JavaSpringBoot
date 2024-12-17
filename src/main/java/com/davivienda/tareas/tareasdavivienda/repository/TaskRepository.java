package com.davivienda.tareas.tareasdavivienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davivienda.tareas.tareasdavivienda.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
