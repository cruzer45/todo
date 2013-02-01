function TodoCtrl($scope, $routeParams, $http){

	$scope.todos = [];
	var address = 'todo.todoAdded',
		eb = new vertx.EventBus(window.location.protocol + '//' + window.location.hostname + ':8585' + '/events');

	var handler = function(message) {
		if (message){
			console.log('Received message ' + message.message);
			$scope.list();
		}
	};

	eb.onopen = function() {
		console.log('connection open');
		eb.registerHandler(address, handler);
	};

	function send(message) {
		if (eb.readyState() == vertx.EventBus.OPEN) {
			console.log("sending message");
			eb.publish(address, message);
		}
		else {
			console.log("The socket is not open.");
		}
	};

	$scope.list = function(){
		$http.get("todo/list").success( function( data ) {	$scope.todos = data	})
	};

	$scope.complete = function(id){
		$http.post("todo/complete?id="+id).success( function( data ) {
			$scope.list()
		})	
	};

	$scope.addTodo = function(){
		$http.post("todo/save",{description : $scope.description}
			).success( function( data ) {
				eb.publish(address, {message:("New item added to list:" + $scope.description )});
				$scope.todos = data;
				$scope.description = "";
			})
		};

	 // when we first stat up, load todos
	 $scope.list()
}