package org.example.todoapispringapplication;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("FakeTodoService")
public class FakeTodoService implements TodoService {


    @Override
    @TimeMonitor
    public String doSomething() {

        return "doing something";
    }
}
