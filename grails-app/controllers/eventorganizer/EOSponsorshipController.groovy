package eventorganizer

import org.springframework.dao.DataIntegrityViolationException

class EOSponsorshipController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [EOSponsorshipInstanceList: EOSponsorship.list(params), EOSponsorshipInstanceTotal: EOSponsorship.count()]
    }

    def create() {
        [EOSponsorshipInstance: new EOSponsorship(params)]
    }

    def save() {
        def EOSponsorshipInstance = new EOSponsorship(params)
        if (!EOSponsorshipInstance.save(flush: true)) {
            render(view: "create", model: [EOSponsorshipInstance: EOSponsorshipInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'EOSponsorship.label', default: 'EOSponsorship'), EOSponsorshipInstance.id])
        redirect(action: "show", id: EOSponsorshipInstance.id)
    }

    def show(Long id) {
        def EOSponsorshipInstance = EOSponsorship.get(id)
        if (!EOSponsorshipInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'EOSponsorship.label', default: 'EOSponsorship'), id])
            redirect(action: "list")
            return
        }

        [EOSponsorshipInstance: EOSponsorshipInstance]
    }

    def edit(Long id) {
        def EOSponsorshipInstance = EOSponsorship.get(id)
        if (!EOSponsorshipInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'EOSponsorship.label', default: 'EOSponsorship'), id])
            redirect(action: "list")
            return
        }

        [EOSponsorshipInstance: EOSponsorshipInstance]
    }

    def update(Long id, Long version) {
        def EOSponsorshipInstance = EOSponsorship.get(id)
        if (!EOSponsorshipInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'EOSponsorship.label', default: 'EOSponsorship'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (EOSponsorshipInstance.version > version) {
                EOSponsorshipInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'EOSponsorship.label', default: 'EOSponsorship')] as Object[],
                          "Another user has updated this EOSponsorship while you were editing")
                render(view: "edit", model: [EOSponsorshipInstance: EOSponsorshipInstance])
                return
            }
        }

        EOSponsorshipInstance.properties = params

        if (!EOSponsorshipInstance.save(flush: true)) {
            render(view: "edit", model: [EOSponsorshipInstance: EOSponsorshipInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'EOSponsorship.label', default: 'EOSponsorship'), EOSponsorshipInstance.id])
        redirect(action: "show", id: EOSponsorshipInstance.id)
    }

    def delete(Long id) {
        def EOSponsorshipInstance = EOSponsorship.get(id)
        if (!EOSponsorshipInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'EOSponsorship.label', default: 'EOSponsorship'), id])
            redirect(action: "list")
            return
        }

        try {
            EOSponsorshipInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'EOSponsorship.label', default: 'EOSponsorship'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'EOSponsorship.label', default: 'EOSponsorship'), id])
            redirect(action: "show", id: id)
        }
    }
}
