import org.vertx.groovy.core.Vertx

class BootStrap {

	def init = { servletContext ->

		def vertx = Vertx.newVertx()
		def httpServer = vertx.createHttpServer()
		def server = vertx.createSockJSServer(httpServer)
		server.bridge([prefix: '/events'], [[:]], [[:]])
		httpServer.listen(8585)

		new todo.Todo(description:"Build this app",done:false).save();
		new todo.Todo(description:"Pick up milk",done:false).save();
		new todo.Todo(description:"Feed the dog.",done:false).save();
		new todo.Todo(description:"Kiss the wife.",done:false).save();
	}
	def destroy = {
	}
}
