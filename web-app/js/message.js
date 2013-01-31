//load('vertx.js');

var address = 'todo.todoAdded';

var eb = new vertx.EventBus(window.location.protocol + '//' + window.location.hostname + ':8585'  + '/events');
//var eb = vertx.eventBus;

var address = 'example.address';

var handler = function(message) {
	if (message){
 		console.log('Received message ' + message);
	}
};

eb.onopen = function() {
	console.log('open');
	//eb.registerHandler(address, handler);
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