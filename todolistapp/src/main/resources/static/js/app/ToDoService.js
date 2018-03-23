'use strict';

angular.module('todoApp').factory('ToDoService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllToDos: loadAllToDos,
                getAllToDos: getAllToDos,
                getToDo: getToDo,
                createToDo: createToDo,
                updateToDo: updateToDo,
                removeToDo: removeToDo
            };

            return factory;

            function loadAllToDos() {
                console.log('Fetching all todos');
                var deferred = $q.defer();
                $http.get(urls.TODO_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all todos');
                            $localStorage.todos = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading todos');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllToDos(){
                return $localStorage.todos;
            }

            function getToDo(id) {
                console.log('Fetching todo with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.TODO_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully todo with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading todo with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createToDo(todo) {
                console.log('Creating todo');
                var deferred = $q.defer();
                $http.post(urls.TODO_SERVICE_API, todo)
                    .then(
                        function (response) {
                            loadAllToDos();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating todo : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateToDo(todo, id) {
                console.log('Updating todo with id '+id);
                var deferred = $q.defer();
                $http.put(urls.TODO_SERVICE_API + id, todo)
                    .then(
                        function (response) {
                            loadAllToDos();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating todo with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeToDo(id) {
                console.log('Removing todo with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.TODO_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllToDos();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing todo with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);