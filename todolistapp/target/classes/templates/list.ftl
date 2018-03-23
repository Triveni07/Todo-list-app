<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Todo </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.todo.id" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="title">Title</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.todo.title" id="title" class="todotitle form-control input-sm" placeholder="Enter todo title" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="notes">Notes</label>
	                        <div class="col-md-7">
	                            <textarea ng-model="ctrl.todo.notes" id="notes" class="form-control input-sm" placeholder="Describe here" ng-maxlength="100" ng-trim="true"></textarea>
	                        </div>
	                    </div>
	                </div>
	
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="status">Status</label>
	                        <div class="col-md-7">
	                            <input type="radio" ng-model="ctrl.todo.status" id="status" value="Pending"> Pending</input><br>
	                            	   <input type="radio" ng-model="ctrl.todo.status" id="status" value="Completed"> Completed</input>
	                            
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!ctrl.todo.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of todos </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>ID</th>
		                <th>Title</th>
		                <th>Notes</th>
		                <th>Status</th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="t in ctrl.getAllToDos()">
		                <td>{{t.id}}</td>
		                <td>{{t.title}}</td>
		                <td>{{t.notes}}</td>
		                <td>{{t.status}}</td>
		                <td><button type="button" ng-click="ctrl.editToDo(t.id)" class="btn btn-success custom-width">Edit</button></td>
		                <td><button type="button" ng-click="ctrl.removeToDo(t.id)" class="btn btn-danger custom-width">Remove</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
</div>