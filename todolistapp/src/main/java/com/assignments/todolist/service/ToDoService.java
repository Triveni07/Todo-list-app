package com.assignments.todolist.service;


import java.util.List;

import com.assignments.todolist.model.ToDo;

public interface ToDoService {
	
	ToDo findById(Long id);

	ToDo findByTitle(String title);
	
	ToDo findByStatus(String status);

	void saveToDo(ToDo todo);

	void updateToDo(ToDo todo);

	void deleteToDoById(Long id);

	void deleteAllToDos();
	
	List<ToDo> findAllToDos();

	boolean isToDoExist(ToDo todo);


}