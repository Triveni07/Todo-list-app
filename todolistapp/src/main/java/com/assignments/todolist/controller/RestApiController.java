package com.assignments.todolist.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.assignments.todolist.model.ToDo;
import com.assignments.todolist.service.ToDoService;
import com.assignments.todolist.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	ToDoService todoService; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All ToDos---------------------------------------------

	@RequestMapping(value = "/todo/", method = RequestMethod.GET)
	public ResponseEntity<List<ToDo>> loadAllToDos() {
		List<ToDo> todos = todoService.findAllToDos();
		if (todos.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ToDo>>(todos, HttpStatus.OK);
	}

	// -------------------Retrieve Single ToDo------------------------------------------

	@RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getToDo(@PathVariable("id") long id) {
		logger.info("Fetching ToDo with id {}", id);
		ToDo todo = todoService.findById(id);
		if (todo == null) {
			logger.error("ToDo with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("ToDo with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ToDo>(todo, HttpStatus.OK);
	}

	// -------------------Create a ToDo-------------------------------------------

	@RequestMapping(value = "/todo/", method = RequestMethod.POST)
	public ResponseEntity<?> createToDo(@RequestBody ToDo todo, UriComponentsBuilder ucBuilder) {
		logger.info("Creating todo : {}", todo);

		if (todoService.isToDoExist(todo)) {
			logger.error("Unable to create. A todo with title {} already exist", todo.getTitle());
			return new ResponseEntity(new CustomErrorType("Unable to create. A todo with title " + 
			todo.getTitle() + " already exist."),HttpStatus.CONFLICT);
		}
		todoService.saveToDo(todo);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/todo/{id}").buildAndExpand(todo.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a ToDo ------------------------------------------------

	@RequestMapping(value = "/todo/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateToDo(@PathVariable("id") long id, @RequestBody ToDo todo) {
		logger.info("Updating todo with id {}", id);

		ToDo currentTodo = todoService.findById(id);

		if (currentTodo == null) {
			logger.error("Unable to update. ToDo with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. ToDo with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentTodo.setTitle(todo.getTitle()); 
		currentTodo.setNotes(todo.getNotes());
		currentTodo.setStatus(todo.getStatus());

		((ToDoService) currentTodo).updateToDo(currentTodo);
		return new ResponseEntity<ToDo>(currentTodo, HttpStatus.OK);
	}

	// ------------------- Delete a ToDo-----------------------------------------

	@RequestMapping(value = "/todo/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteToDo(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting User with id {}", id);

		ToDo todo = todoService.findById(id);
		if (todo == null) {
			logger.error("Unable to delete. ToDo with id {} not found.", id);
			return new ResponseEntity<Object>(new CustomErrorType("Unable to delete. ToDo with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		todoService.deleteToDoById(id);
		return new ResponseEntity<ToDo>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All ToDos-----------------------------

	@RequestMapping(value = "/todo/", method = RequestMethod.DELETE)
	public ResponseEntity<ToDo> deleteAllToDos() {
		logger.info("Deleting All ToDos");

		todoService.deleteAllToDos();
		return new ResponseEntity<ToDo>(HttpStatus.NO_CONTENT);
	}

}