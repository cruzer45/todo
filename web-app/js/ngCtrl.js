
var sock = new SockJS('http://localhost:8585/events');

var eb = new vertx.EventBus(window.location.protocol + '//' + window.location.hostname + ':' + window.location.port + '/eventbus');

sock.onopen = function() {
	console.log('open');
};

sock.onmessage = function(e) {
	console.log('message', e.data);
	alert('received message echoed from server: ' + e.data);
};

sock.onclose = function() {
	console.log('close');
};

function send(message) {

	if (sock.readyState == WebSocket.OPEN) {
		console.log("sending message")
		sock.send(message);
	} else {
		console.log("The socket is not open.");
	}
}

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
	}