package org.example.todoapispringapplication;

import java.util.List;
import java.util.Optional;

public interface TodoService {


     public String  doSomething();
     List<Todo> getAllTodos();
     Optional<Todo> getTodoById(int id);
     Todo addTodo(Todo todo);
     void deleteTodoById(int id);
     Todo updateTodo(int id, Todo updatedTodo);
     boolean exist(int id);
}
