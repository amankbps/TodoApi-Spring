package org.example.todoapispringapplication;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {


     private final TodoService todoService;
    private static List<Todo>todosList;

    public TodoController(@Qualifier("FakeTodoService") TodoService todoService)
    {
        this.todoService = todoService;
        todosList = new ArrayList<>();
        todosList.add(new Todo(1,false,"Todo 1",1));
        todosList.add(new Todo(2,true,"Todo 2",2));
    }

    @GetMapping
    public ResponseEntity<List<Todo>>getTodos(@RequestParam(required = false,defaultValue ="true") boolean isCompleted)
    {
        System.out.println(todoService.doSomething());
        return ResponseEntity.ok().body(todosList);
    }

    @PostMapping
   // one way to get stats code @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo)
    {
        todosList.add(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(todo);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<?>getTodoById(@PathVariable int todoId)
    {
        for(Todo todo : todosList)
        {
             if(todo.getId() == todoId)
             {
                 return ResponseEntity.ok().body(todo);
             }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<?>deleteById(@PathVariable int todoId)
    {
         int ind=-1;
         for(Todo todo : todosList)
         {
               if(todo.getId() == todoId)
               {
                   ind = todosList.indexOf(todo);
               }
         }
         if(ind != -1) {
             todosList.remove(ind);
             return ResponseEntity.ok().build();
         }
         else
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<?> updateTodo(@PathVariable int todoId, @RequestBody Todo updatedTodo) {
        for (Todo todo : todosList) {
            if (todo.getId() == todoId) {
               todosList.set(todosList.indexOf(todo),updatedTodo );
                return ResponseEntity.ok().body(todo);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }



}
