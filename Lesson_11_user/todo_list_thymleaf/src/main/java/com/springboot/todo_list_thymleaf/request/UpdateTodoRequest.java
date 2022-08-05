package com.springboot.todo_list_thymleaf.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTodoRequest {
    private String title;
    private boolean Status;
}
