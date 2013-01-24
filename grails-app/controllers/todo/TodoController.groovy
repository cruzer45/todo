package todo

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

class TodoController {

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
            list()
            return
        }
    }

    def complete() {
        def todoInstance = Todo.get(params.id)
        if (todoInstance) {
            todoInstance.done = true
            todoInstance.save()
        }
        render ""
    }
}
