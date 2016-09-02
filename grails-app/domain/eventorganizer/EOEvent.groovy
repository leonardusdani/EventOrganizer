package eventorganizer

class EOEvent {

    String city
    String name
    EOUser organizer
    String venue
    Date startDate
    Date endDate
    String description

    static constraints = {
        name blank: false
        city blank: false, nullable: false
        description blank: false, maxSize: 2000
        organizer()
        venue blank: false
        startDate()
        endDate()
    }
}
