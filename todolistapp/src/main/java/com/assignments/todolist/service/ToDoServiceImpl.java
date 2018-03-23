package com.assignments.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignments.todolist.model.ToDo;
import com.assignments.todolist.repositories.TodoRepository;



@Service("todoService")
@Transactional
public class ToDoServiceImpl implements ToDoService{

	private final TodoRepository todoRepository;
	
	@Autowired
	public ToDoServiceImpl(final TodoRepository todoRepository){
		this.todoRepository = todoRepository;
	}

	@Override
	public ToDo findById(Long id) {
		return todoRepository.findOne(id);
	}

	@Override
	public ToDo findByStatus(String status) {
		return todoRepository.findByStatus(status);
	}

	@Override
	public ToDo findByTitle(String title) {
		return todoRepository.findByTitle(title);
	}

	
	@Override
	public void saveToDo(ToDo todo) {
		 todoRepository.save(todo);
		
	}

	@Override
	public void updateToDo(ToDo id) {
		 todoRepository.save(id);
		
	}

	@Override
	public void deleteToDoById(Long id) {
		 todoRepository.delete(id);
		
	}

	@Override
	public void deleteAllToDos() {
		 todoRepository.deleteAll();
		
	}

	@Override
	public List<ToDo> findAllToDos() {
		return todoRepository.findAll();
	}

	@Override
	public boolean isToDoExist(ToDo todo) {
		return findByTitle(todo.getTitle()) != null;
	}

	
	

}
