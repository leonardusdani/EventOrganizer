package eventorganizer

class EOTask {

    String title
    String notes
    EOUser assignedTo
    Date dueDate
    EOEvent event
    static belongsTo = EOEvent

    static constraints = {
        title blank: false
        notes blank: true, nullable: true, maxSize: 5000
        assignedTo nullable: true
        dueDate nullable: true
    }
}
