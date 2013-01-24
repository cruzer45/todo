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