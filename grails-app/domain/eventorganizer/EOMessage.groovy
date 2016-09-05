package eventorganizer

class EOMessage {

    String subject
    String content
    EOMessage parent
    EOEvent event
    EOUser author

    static belongsTo = EOEvent

    static constraints = {
        subject blank: false
        content blank: false, maxSize: 2000
        parent nullable: true
        author nullable: false
    }
}
