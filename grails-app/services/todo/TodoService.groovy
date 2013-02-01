package todo

import grails.converters.JSON
import org.springframework.transaction.annotation.Transactional

@Transactional
class TodoService {

	def eventBusService
	def address = "todo"

	def save = { description ->
		def todoInstance = new Todo(description:description,done:false)
		if (todoInstance.save(flush: true)) {
			eventBusService.publish(address, ['message':"New item added to list:" + description])
		}
		return
	}

	def toggleStatus = { id,version ->
		def todoInstance = Todo.get(id)

		if (todoInstance && (todoInstance.version == version as int)) {
			Todo.lock(id)
			todoInstance.done = !todoInstance.done
			todoInstance.save(flush: true)
		}
		else{
			println("Todo version info for to #" + id + " was off... Aboting.")
			println("Expected " + version + " and got " + todoInstance.version)
		}
		eventBusService.publish(address, ['message':"Item status toggled:" + todoInstance.description] )
		return
	}
}
