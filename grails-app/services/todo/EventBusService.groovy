package todo

import org.vertx.groovy.core.Vertx
import org.springframework.beans.factory.DisposableBean

class EventBusService {

	def vertx
	def eb
	def httpServer
	
	def createServer = {
		println("Starting up vertx server")
		vertx = Vertx.newVertx()
		httpServer = vertx.createHttpServer()
		def server = vertx.createSockJSServer(httpServer)
		server.bridge([prefix: '/events'], [[:]], [[:]])
		httpServer.listen(8585)
		
		eb = vertx?.eventBus
	}
	
	def publish = { address , message ->
		eb.publish(address, message)
	}
	
    def destroy = {
    	println("Shutting down vertx server")
		httpServer.close()
	}
}
