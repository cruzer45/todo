package todo

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class TodoController {

    def todoService

	static allowedMethods = [list:"GET", save: "POST", toggleStatus: "POST"]

	def index() {
		render(view:"index.gsp")
	}

	def list() {
		render Todo.findAll() as JSON
	}

	def save() {
        todoService.save(request.JSON.description)
        render "Action Successful"
	}

	def toggleStatus() {
        todoService.toggleStatus(params.id)
        render "Action Successful"
	}
}
