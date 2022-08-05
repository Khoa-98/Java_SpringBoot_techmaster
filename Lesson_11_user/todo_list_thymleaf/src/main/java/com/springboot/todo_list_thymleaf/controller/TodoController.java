package com.springboot.todo_list_thymleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.todo_list_thymleaf.model.Todo;
import com.springboot.todo_list_thymleaf.request.CreateTodoRequest;
import com.springboot.todo_list_thymleaf.request.UpdateTodoRequest;
import com.springboot.todo_list_thymleaf.service.TodoService;



@RestController
@RequestMapping("api/v1")
public class TodoController {

    @Autowired
    TodoService todoService;

    // lay danh sach tat ca coong viec
    @GetMapping("/todos")
    public List<Todo> getTodos(@RequestParam(required = false, defaultValue = "") String status) {

        return todoService.getTodos(status);
    }

    // lay cong viec theo id
    @GetMapping("/todos/{id}")
    public Todo getTodoById(@PathVariable int id) {
        return todoService.getTodoById(id);
    }

    // tao moi cong viec
    @PostMapping("/todos")
    public ResponseEntity<Todo> createTodo(@RequestBody CreateTodoRequest request) {
        Todo todo = todoService.createTodo(request);
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }

    // cap nhat conog viec
    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable int id, @RequestBody UpdateTodoRequest request) {
        return todoService.updateTodo(id, request);
    }

    // Xoa cong viec
    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable int id) {
        todoService.deleteTodo(id);
    }
}
