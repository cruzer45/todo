package todo

import org.vertx.groovy.core.Vertx
import javax.annotation.PreDestroy;

class EventBusService {

	def vertx
	def eb
	def httpServer
	
	def createServer = {
		log.info("Starting up vertx server")
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
	
	@PreDestroy
    def destroy() {
    	log.info("Shutting down vertx server")
		httpServer.close()
	}
}
