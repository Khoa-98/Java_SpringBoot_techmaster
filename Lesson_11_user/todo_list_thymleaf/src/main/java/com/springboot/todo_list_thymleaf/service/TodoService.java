package com.springboot.todo_list_thymleaf.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.springboot.todo_list_thymleaf.exception.NotFoundException;
import com.springboot.todo_list_thymleaf.model.Todo;
import com.springboot.todo_list_thymleaf.request.CreateTodoRequest;
import com.springboot.todo_list_thymleaf.request.UpdateTodoRequest;

@Service
public class TodoService {
    // Tạo danh sách todos để quản lý
    private List<Todo> todos;

    // Trong contructor , tạo ra 1 số đối tượng todos
    public TodoService() {
        Random rd = new Random();
        todos = new ArrayList<>();

        todos.add(new Todo(rd.nextInt(100), "Lam bai tap", true));
        todos.add(new Todo(rd.nextInt(100), "di da bong", false));
        todos.add(new Todo(rd.nextInt(100), "an com", true));

    }

    public List<Todo> getTodos(String status) {
        return switch (status) {
            case "true" -> todos.stream().filter(todo -> todo.isStatus()).collect(Collectors.toList());
            case "false" -> todos.stream().filter(todo -> !todo.isStatus()).collect(Collectors.toList());
            default -> todos;
        };
    }

    public Todo getTodoById(int id) {
        Optional<Todo> todoOptional = findById(id);

        return todoOptional.orElseThrow(() -> {
            throw new NotFoundException(" khong tim thay cong viec co id =" + id);
        });
    }

    public Todo createTodo(CreateTodoRequest request) {
        // có thể validate title neu de trong

        Random rd = new Random();
        Todo todo = new Todo(rd.nextInt(100), request.getTitle(), false);
        todos.add(todo);

        return todo;

    }

    public Todo updateTodo(int id, UpdateTodoRequest request) {

        // kiem tra xem cong viec co ton tai hay khong
        // Optional<Todo> todoOptional = findById(id);

        // if (todoOptional.isEmpty()) {

        // throw new NotFoundException("Khong tim thay cong viec co id =" + id);
        // }

        // cap nhat cong viec theo thong tin tu request
        Todo todo = getTodoById(id);
        todo.setTitle(request.getTitle());
        todo.setStatus(request.isStatus());

        // tra ve cong viec sau khi cap nhat thanh cong
        return todo;
    }

    public void deleteTodo(int id) {
        Todo todo = getTodoById(id);
        todos.removeIf(t -> t.getId() == todo.getId());
    }

    // Helper metohd: tim kiem 1 todo theo id
    public Optional<Todo> findById(int id) {
        return todos.stream().filter(todo -> todo.getId() == id).findFirst();
    }
}
