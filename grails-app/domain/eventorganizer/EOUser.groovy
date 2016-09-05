package eventorganizer

class EOUser {

    String fullName
    String userName
    String password
    String email
    String website
    String bio

    String toString(){
        "$fullName"
    }

    static constraints = {
        fullName blank: false
        userName blank: false
        password blank: false, password: true
        email blank: false, email: true
        website blank: true, url: true
        bio blank: true, maxSize: 2000
    }
}
