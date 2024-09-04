package org.example.todoapispringapplication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.todoapispringapplication.TodoRepository;
import java.util.List;
import java.util.Optional;

@Service("AnotherTodoService")
public class AnotherTodoService implements TodoService {


       TodoRepository todoRepository;
       public AnotherTodoService(TodoRepository todoRepository) {
           this.todoRepository = todoRepository;
       }

     @Override
     public String doSomething(){
          return "something from another todo service";
    }

    @Override
    public List<Todo> getAllTodos() {
        return  todoRepository.findAll();
    }

    @Override
    public Optional<Todo> getTodoById(int id) {
        return  todoRepository.findById(id);
    }

    @Override
    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public void deleteTodoById(int id) {
          todoRepository.deleteById(id);
    }

    @Override
    public Todo updateTodo(int id, Todo updatedTodo) {
        if (todoRepository.existsById(id)) {
            updatedTodo.setId(id); // Ensure the ID remains the same
            return todoRepository.save(updatedTodo);
        } else {
            return null;
        }

    }
    @Override
    public boolean exist(int id)
    {
        return todoRepository.existsById(id);
    }
}
