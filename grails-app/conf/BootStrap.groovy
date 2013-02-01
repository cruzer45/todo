import org.codehaus.groovy.grails.commons.ApplicationAttributes

class BootStrap {

	def init = { servletContext ->
		
		def ctx = servletContext.getAttribute(ApplicationAttributes.APPLICATION_CONTEXT)
		ctx.eventBusService.createServer()

		new todo.Todo(description:"Build this app",done:false).save();
		new todo.Todo(description:"Pick up milk",done:false).save();
		new todo.Todo(description:"Feed the dog.",done:false).save();
		new todo.Todo(description:"Kiss the wife.",done:false).save();
	}
	def destroy = { servletContext ->
		
	}
}
