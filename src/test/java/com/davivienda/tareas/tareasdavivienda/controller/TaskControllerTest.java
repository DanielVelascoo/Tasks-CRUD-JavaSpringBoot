package com.davivienda.tareas.tareasdavivienda.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.sql.Date;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.davivienda.tareas.tareasdavivienda.entities.Task;
import com.davivienda.tareas.tareasdavivienda.repository.TaskRepository;

@WebMvcTest(TaskController.class)  // Solo WebMvcTest es necesario
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Task task;

    @Mock
    private TaskRepository taskRepository;  // Mock de TaskRepository

    @InjectMocks
    private TaskController taskController;  // Se inyectan los mocks en el controlador

    @BeforeEach
    void setUp() {
        task = new Task();
        task.setTitle("Correr");
        task.setDescription("Correr por 15 minutos");
        task.setExpiration_date(Date.valueOf("2024-12-18"));  // Usa Date.valueOf para convertir la cadena en un objeto Date
    }

    @Test
    void testGetAllTasks() throws Exception {
        // Simula que cuando se llame a taskRepository.findAll() se devuelve la lista de tareas
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task));

        // Realiza la petición GET y verifica que el resultado sea correcto
        mockMvc.perform(get("/tasks"))  // Asume que la URL es "/tasks"
            .andExpect(status().isOk())  // Verifica que la respuesta sea 200 OK
            .andExpect(jsonPath("$[0].title").value("Correr"))  // Verifica que el primer item tenga el título "Correr"
            .andExpect(jsonPath("$[0].description").value("Correr por 15 minutos"))  // Verifica la descripción
            .andExpect(jsonPath("$[0].expiration_date").value("2024-12-18"));  // Verifica la fecha de expiración
    }
}
