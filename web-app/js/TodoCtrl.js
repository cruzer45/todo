function TodoCtrl($scope, $routeParams, $http){

	$scope.todos = [];
	var address = 'todo',
	eb = new vertx.EventBus(window.location.protocol + '//' + window.location.hostname + ':8585' + '/events');

	var handler = function(message) {
		if (message){
			console.log(message.message);
			$scope.list();
		}
	};

	eb.onopen = function() {
		console.log('connection open');
		eb.registerHandler(address, handler);
	};

	$scope.list = function(){
		$http.get("todo/list").success( function( data ) {	
			console.log(data);
			$scope.todos = data	})
	};

	$scope.toggleStatus = function(id, version){
		console.log("about to try to toggle todo #" + id);
		$http.post("todo/toggleStatus?id="+id);	
	};

	$scope.addTodo = function(){
		$http.post("todo/save",{description : $scope.description}
			).success( function() {
				$scope.description = "";
			})
		};

	 // when we first stat up, load todos
	 $scope.list()
	}