package com.thesun4sky.todoparty.todo;


import com.thesun4sky.todoparty.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {


}
