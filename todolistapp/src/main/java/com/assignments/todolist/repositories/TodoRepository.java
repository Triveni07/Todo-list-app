package com.assignments.todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignments.todolist.model.ToDo;

@Repository
public interface TodoRepository extends JpaRepository<ToDo, Long> {

	ToDo findById(Long id);
    
	ToDo findByStatus(String status);

	ToDo findByTitle(String title);
		 
}
