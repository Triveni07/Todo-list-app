'use strict';

angular.module('todoApp').controller('ToDoController',
		['ToDoService', '$scope',  function( ToDoService, $scope) {

			var self = this;
			self.todo = {};
			self.todos=[];

			self.submit = submit;
			self.getAllToDos = getAllToDos;
			self.createToDo = createToDo;
			self.updateToDo = updateToDo;
			self.removeToDo = removeToDo;
			self.editToDo = editToDo;
			self.reset = reset;

			self.successMessage = '';
			self.errorMessage = '';
			self.done = false;
			
			 self.onlyIntegers = /^\d+$/;
		        self.onlyNumbers = /^\d+([,.]\d+)?$/;


		
			function submit() {
				console.log('Submitting');
				if (self.todo.id === undefined || self.todo.id === null) {
					console.log('Saving New todo', self.todo);
					createToDo(self.todo);
				} else {
					updateToDo(self.todo, self.todo.id);
					console.log('ToDo updated with id ', self.todo.id);
				}
			}

			function createToDo(todo) {
				console.log('About to create ToDo');
				ToDoService.createToDo(todo)
				.then(
						function (response) {
							console.log('Todo created successfully');
							self.successMessage = 'Todo created successfully';
							self.errorMessage='';
							self.done = true;
							self.todo={};
							$scope.myForm.$setPristine();
						},
						function (errResponse) {
							console.error('Error while creating todo');
							self.errorMessage = 'Error while creating todo: ' + errResponse.data.errorMessage;
							self.successMessage='';
						}
				);
			}


			function updateToDo(todo, id){
				console.log('About to update ToDo');
				ToDoService.updateToDo(todo, id)
				.then(
						function (response){
							console.log('ToDo updated successfully');
							self.successMessage='ToDo updated successfully';
							self.errorMessage='';
							self.done = true;
							$scope.myForm.$setPristine();
						},
						function(errResponse){
							console.error('Error while updating ToDo');
							self.errorMessage='Error while updating ToDo '+errResponse.data;
							self.successMessage='';
						}
				);
			}


			function removeToDo(id){
				console.log('About to remove ToDo with id '+id);
				ToDoService.removeToDo(id)
				.then(
						function(){
							console.log('ToDo '+id + ' removed successfully');
						},
						function(errResponse){
							console.error('Error while removing ToDo '+id +', Error :'+errResponse.data);
						}
				);
			}


			function getAllToDos(){
				return ToDoService.getAllToDos();
			}

			function editToDo(id) {
				self.successMessage='';
				self.errorMessage='';
				ToDoService.getToDo(id).then(
						function (todo) {
							self.todo = todo;
						},
						function (errResponse) {
							console.error('Error while removing todo ' + id + ', Error :' + errResponse.data);
						}
				);
			}
			function reset(){
				self.successMessage='';
				self.errorMessage='';
				self.todo={};
				$scope.myForm.$setPristine(); //reset Form
			}
		}

		]);