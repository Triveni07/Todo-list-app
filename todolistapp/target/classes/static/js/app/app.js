var app = angular.module('todoApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/ToDoListApp',
    TODO_SERVICE_API : 'http://localhost:8080/ToDoListApp/api/todo/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'ToDoController',
                controllerAs:'ctrl',
                resolve: {
                    todos: function ($q, ToDoService) {
                        console.log('Load all todos');
                        var deferred = $q.defer();
                        ToDoService.loadAllToDos().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

