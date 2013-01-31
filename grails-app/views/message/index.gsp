<!doctype html>
<html >
<head>
	<title>todo</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
	<script src="js/sockjs.js"></script>
	<script src="js/vertxbus.js"></script>
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/bootstrap-responsive.css"> 
	<link rel="stylesheet" href="css/app.css"> 
</head>

<body>
	<div class="container">
		<h1>Todos</h1>
		<hr />

		<form onsubmit="return false;">
			<input id="vertx-message" type="text" name="message" value="Hello, World!"/>
			<button type="submit" class="btn" onclick="send(this.form.message.value)"><i class="icon-plus"></i> Send Vertx Message</button>
		</form>
	</div>



	<script>
		var address = 'todo.todoAdded';

		var eb = new vertx.EventBus(window.location.protocol + '//' + window.location.hostname + ':8585'  + '/events');
		//var eb = vertx.eventBus;

		var address = 'example.address';

		var handler = function(message) {
			if (message){
				alert('Received message ' + message.message);
			}
		};

		eb.onopen = function() {
			console.log('open');
			eb.registerHandler(address, handler);
		};

		function send(message) {
			if (eb.readyState() == vertx.EventBus.OPEN) {
				console.log("sending message");
				eb.publish(address, {message:message});
			} 
			else {
				console.log("The socket is not open.");
			}
		};
	</script>
</body>
</html>