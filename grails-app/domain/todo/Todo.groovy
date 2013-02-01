package todo

class Todo {

	boolean done
	String description

    static constraints = {
    	description(bank:false)
    	done()
    }

    String toString(){
    	description
    }

    def beforeValidate(){
    	description = description?.trim()?.capitalize()
    }
}
