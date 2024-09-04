package org.example.todoapispringapplication;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {


     private final TodoService todoService;

    public TodoController(@Qualifier("AnotherTodoService") TodoService todoService)
    {
        this.todoService = todoService;

    }

    @GetMapping
    public ResponseEntity<List<Todo>>getTodos(@RequestParam(required = false,defaultValue ="true") boolean isCompleted)
    {
        return ResponseEntity.ok().body(todoService.getAllTodos());
    }

    @PostMapping
   // one way to get stats code @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo)
    {
         todoService.addTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(todo);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<?>getTodoById(@PathVariable int todoId)
    {
        Optional<Todo> optionalTodo = todoService.getTodoById(todoId);
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            return ResponseEntity.ok().body(todo);

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }

    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<?> deleteById(@PathVariable int todoId) {
        if (todoService.getTodoById(todoId).isPresent()) {
            todoService.deleteTodoById(todoId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo not found");
        }
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<?> updateTodo(@PathVariable int todoId, @RequestBody Todo updatedTodo) {

         if(todoService.exist(todoId)) {
             todoService.updateTodo(todoId, updatedTodo);
             return ResponseEntity.ok().build();
         }
         else
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }



}
