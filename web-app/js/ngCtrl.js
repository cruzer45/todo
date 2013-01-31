
//var sock = new SockJS(window.location.protocol + '//' + window.location.hostname + ':8585'  + '/events');

var eb = new vertx.EventBus(window.location.protocol + '//' + window.location.hostname + ':8585'  + '/events');

var address = 'todo.todoAdded';


var handler = function(message) {
	if(message) {alert('Received message ' + message.message);}
};

eb.onopen = function() {
	console.log('open');
	eb.registerHandler(address, handler);
};

eb.onmessage = function(e) {
	console.log('message', e.data);
	alert('received message echoed from server: ' + e.data);
};

eb.onclose = function() {
	console.log('close');
};

function send(message) {

	var result = eb.readyState(); 
	console.log(result);
	console.log(vertx.EventBus.OPEN);
	console.log(address);
	console.log(message);

	if (eb.readyState() == vertx.EventBus.OPEN) {
		console.log("sending message");
		eb.publish(address, {message:message});
	} 
	else {
		console.log("The socket is not open.");
	}
};

function TodoCtrl($scope, $routeParams, $http){
	$scope.todos = [];

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
				$scope.todos = data;
				$scope.description = "";
			})
		};

	 // when we first stat up, load todos
	 $scope.list()
};
