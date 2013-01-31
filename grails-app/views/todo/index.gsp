<!doctype html>
<html ng-app>
<head>
	<title>todo</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
	<script src="js/sockjs.js"></script>
	<script src="js/angular.js"></script>
	<script src="js/vertxbus.js"></script>
	<script src="js/ngCtrl.js"></script>
	<link rel="stylesheet" href="https://app.divshot.com/css/bootstrap.css">
	<link rel="stylesheet" href="https://app.divshot.com/css/bootstrap-responsive.css"> 
	<link rel="stylesheet" href="css/app.css"> 
</head>

<body>
	<div class="container" ng-controller="TodoCtrl">
		<h1>Todos</h1>
		<hr />

		<form onsubmit="return false;">
			<input type="text" name="message" value="Hello, World!"/>
			<input type="button" value="Send SockJS data" onclick="send(this.form.message.value)"/>
		</form>

		<form ng-submit="addTodo()">
			<input ng-model="description" id="description" type="text" > <button type="submit" class="btn"><i class="icon-plus"></i> Add Item</button>
		</form>

		<ul class="unstyled">
			<li ng-repeat="todo in todos">
				<label class="checkbox checked-{{todo.done}}" ng-click="complete(todo.id)">
					<input value="{{todo.done}}" ng-checked="todo.done" name="checkbox" type="checkbox"/> {{todo.description}}
				</label>
			</li>
		</ul>
	</div>
</body>
</html>