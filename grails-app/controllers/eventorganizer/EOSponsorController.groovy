package eventorganizer

import org.springframework.dao.DataIntegrityViolationException

class EOSponsorController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [EOSponsorInstanceList: EOSponsor.list(params), EOSponsorInstanceTotal: EOSponsor.count()]
    }

    def create() {
        [EOSponsorInstance: new EOSponsor(params)]
    }

    def save() {
        def EOSponsorInstance = new EOSponsor(params)
        if (!EOSponsorInstance.save(flush: true)) {
            render(view: "create", model: [EOSponsorInstance: EOSponsorInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'EOSponsor.label', default: 'EOSponsor'), EOSponsorInstance.id])
        redirect(action: "show", id: EOSponsorInstance.id)
    }

    def show(Long id) {
        def EOSponsorInstance = EOSponsor.get(id)
        if (!EOSponsorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'EOSponsor.label', default: 'EOSponsor'), id])
            redirect(action: "list")
            return
        }

        [EOSponsorInstance: EOSponsorInstance]
    }

    def edit(Long id) {
        def EOSponsorInstance = EOSponsor.get(id)
        if (!EOSponsorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'EOSponsor.label', default: 'EOSponsor'), id])
            redirect(action: "list")
            return
        }

        [EOSponsorInstance: EOSponsorInstance]
    }

    def update(Long id, Long version) {
        def EOSponsorInstance = EOSponsor.get(id)
        if (!EOSponsorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'EOSponsor.label', default: 'EOSponsor'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (EOSponsorInstance.version > version) {
                EOSponsorInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'EOSponsor.label', default: 'EOSponsor')] as Object[],
                          "Another user has updated this EOSponsor while you were editing")
                render(view: "edit", model: [EOSponsorInstance: EOSponsorInstance])
                return
            }
        }

        EOSponsorInstance.properties = params

        if (!EOSponsorInstance.save(flush: true)) {
            render(view: "edit", model: [EOSponsorInstance: EOSponsorInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'EOSponsor.label', default: 'EOSponsor'), EOSponsorInstance.id])
        redirect(action: "show", id: EOSponsorInstance.id)
    }

    def delete(Long id) {
        def EOSponsorInstance = EOSponsor.get(id)
        if (!EOSponsorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'EOSponsor.label', default: 'EOSponsor'), id])
            redirect(action: "list")
            return
        }

        try {
            EOSponsorInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'EOSponsor.label', default: 'EOSponsor'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'EOSponsor.label', default: 'EOSponsor'), id])
            redirect(action: "show", id: id)
        }
    }
}
