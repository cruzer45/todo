<!DOCTYPE html>
<html ng-app>
	<head>
		<title>Todo Mashup - Grails + Vert.x + AngularJs</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
		<script src="js/angular.js" type="text/javascript"></script>
		<script src="js/sockjs.js" type="text/javascript"></script>
		<script src="js/vertxbus.js" type="text/javascript"></script>
		<script src="js/TodoCtrl.js" type="text/javascript"></script>
		<link rel="stylesheet" href="https://app.divshot.com/css/bootstrap.css" type="text/css">
		<link rel="stylesheet" href="https://app.divshot.com/css/bootstrap-responsive.css" type="text/css">
		<link rel="stylesheet" href="css/app.css" type="text/css">
	</head>
	<body>
		<div class="container" ng-controller="TodoCtrl">
			<h1>
				Todo Mashup 
			</h1>
			<h3>Grails + Vert.x + AngularJs</h3>
			<hr>
			<form ng-submit="addTodo()">
				<input ng-model="description" id="description" type="text"/> 
				<button type="submit" class="btn"> Add Item</button>
			</form>
			<ul class="unstyled">
				<li ng-repeat="todo in todos">
					<label class="checkbox checked-{{todo.done}}" ng-click="toggleStatus(todo.id)" />
						<input value="{{todo.done}}" ng-checked="todo.done" name="checkbox" type="checkbox"> 
						{{todo.description}}
					</label>
				</li>
			</ul>
		</div>
	</body>
</html>