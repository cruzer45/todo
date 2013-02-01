package todo

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class TodoController {
	
	def eventBusService
	def address = "todo"

	static allowedMethods = [list:"GET", save: "POST", complete: "POST"]

	def index() {
		render(view:"index.gsp")
	}

	def list() {
		render Todo.findAll() as JSON
	}

	def save() {
		def todoInstance = new Todo(
				description:request.JSON.description,
				done:false
				)
		if (todoInstance.save(flush: true)) {
			eventBusService.publish(address, ['message':"New item added to list:" + todoInstance.description])
			list()
			return
		}
	}

	def toggleStatus() {
		def todoInstance = Todo.get(params.id)
		if (todoInstance) {
			todoInstance.done = !todoInstance.done
			todoInstance.save()
		}
        eventBusService.publish(address, ['message':"Item status toggled:" + todoInstance.description] )
		render todoInstance as JSON
	}
}
