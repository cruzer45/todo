package todo

class Todo {

	int version 
	boolean done
	String description

    static constraints = {
    	description(bank:false)
    	done()
    	version()
    }

    String toString(){
    	description
    }

    def beforeValidate(){
    	description = description?.trim()?.capitalize()
    }
}
