package eventorganizer

class EOEvent {

    String city
    String name
    EOUser organizer
    String venue
    Date startDate
    Date endDate
    String description
    static hasMany = [volunteers : EOUser, respondents : String, sponsorships : EOSponsorship, tasks : EOTask, messages: EOMessage]

    String toString(){
        "$name, $city"
    }

    static constraints = {
        name blank: false
        city blank: false, nullable: false
        description blank: false, maxSize: 2000
        organizer()
        venue blank: false
        startDate()
        endDate()
        volunteers nullable: true
        sponsorships nullable: true
        tasks nullable: true
        messages nullable: true
    }
}
