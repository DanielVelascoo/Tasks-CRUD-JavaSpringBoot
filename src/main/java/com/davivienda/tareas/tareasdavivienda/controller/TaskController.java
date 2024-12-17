package com.davivienda.tareas.tareasdavivienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davivienda.tareas.tareasdavivienda.entities.Task;
import com.davivienda.tareas.tareasdavivienda.repository.TaskRepository;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://127.0.0.1:5500")

public class TaskController {
    
    @Autowired
    private TaskRepository taskrepository;

    //Get all task
    @GetMapping
    public List<Task> getAllTasks(){ //En una lista pongo todas la tareas que hay en la BD
        return taskrepository.findAll(); //Con el findAll se busca todo
    }
    
    //Get taks by ID
    @GetMapping("/{id}") // Aqui se hace que se busque una tarea por ID 
    public Task getTaskbyId(@PathVariable Long id){
        return taskrepository.findById(id) //En este caso se busca por id
        .orElseThrow(() -> new RuntimeException("No se encontró la tarea con el ID: " + id)); //Por si no encuentra el ID entonces muestre un error
    }

    //Create task
    @PostMapping
    public Task createTask(@RequestBody Task task){ //Con este es para crear una tarea
        return taskrepository.save(task); //Con save se guarda
    }

    //Update Task
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task taskDetails){
        Task task = taskrepository.findById(id)//En este caso se busca por id y se guarda en una variable
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id)); //Por si no encuentra el ID entonces muestre un error

        task.setTitle(taskDetails.getTitle()); //Con la variable traemos los datos que se pueden editar, en este caso titulo
        task.setDescription(taskDetails.getDescription());//Con la variable traemos los datos que se pueden editar, en este caso descripcion
        task.setExpiration_date(taskDetails.getExpiration_date());//Con la variable traemos los datos que se pueden editar, en este caso fecha de vencimiento

        return taskrepository.save(task); //Aqui se guarda lo que se actualiza y se muestra
    }

    @DeleteMapping("/{id}")
    public String DeleteProduct(@PathVariable Long id){
        Task task = taskrepository.findById(id)//En este caso se busca por id y se guarda en una variable
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id)); //Por si no encuentra el ID entonces muestre un error

        taskrepository.delete(task); //Aqui ya se elimina 
        return "La tarea con el id: " + id + " fue eliminado correctamente"; //Muestro mensaje de eliminacion de tarea 
    }



    
}
