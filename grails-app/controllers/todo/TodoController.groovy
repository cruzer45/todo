package todo

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class TodoController {

    def todoService

	static allowedMethods = [list:"GET", save: "POST", toggleStatus: "PUT"]

	def index() {
		render(view:"index.gsp")
	}

	def list() {
		render Todo.findAll() as JSON
	}

	def save() {
        todoService.save(request.JSON.description)
        render "Save Successful"
	}

	def toggleStatus() {
        todoService.toggleStatus(request.JSON.id)
        render "Toggle Status Successful"
	}
}
