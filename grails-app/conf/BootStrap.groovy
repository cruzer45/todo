class BootStrap {

    def init = { servletContext ->
    	new todo.Todo(description:"Build this app",done:false).save();
    	new todo.Todo(description:"Pick up milk",done:false).save();
    	new todo.Todo(description:"Feed the dog.",done:false).save();
    	new todo.Todo(description:"Kiss the wife.",done:false).save();
    }
    def destroy = {
    }
}
