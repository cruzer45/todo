package todo

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class TodoController {

    def todoService

	static allowedMethods = [list:"GET", save: "POST", complete: "POST"]

	def index() {
		render(view:"index.gsp")
	}

	def list() {
		render Todo.findAll() as JSON
	}

	def save() {
        todoService.save(request.JSON.description)
        render ""
	}

	def toggleStatus() {
        todoService.toggleStatus(params.id, params.version)
        render ""
	}
}
