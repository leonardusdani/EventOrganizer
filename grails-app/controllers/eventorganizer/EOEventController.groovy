package eventorganizer

import org.springframework.dao.DataIntegrityViolationException

class EOEventController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [EOEventInstanceList: EOEvent.list(params), EOEventInstanceTotal: EOEvent.count()]
    }

    def create() {
        [EOEventInstance: new EOEvent(params)]
    }

    def save() {
        def EOEventInstance = new EOEvent(params)
        if (!EOEventInstance.save(flush: true)) {
            render(view: "create", model: [EOEventInstance: EOEventInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'EOEvent.label', default: 'EOEvent'), EOEventInstance.id])
        redirect(action: "show", id: EOEventInstance.id)
    }

    def show(Long id) {
        def EOEventInstance = EOEvent.get(id)
        if (!EOEventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'EOEvent.label', default: 'EOEvent'), id])
            redirect(action: "list")
            return
        }

        [EOEventInstance: EOEventInstance]
    }

    def edit(Long id) {
        def EOEventInstance = EOEvent.get(id)
        if (!EOEventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'EOEvent.label', default: 'EOEvent'), id])
            redirect(action: "list")
            return
        }

        [EOEventInstance: EOEventInstance]
    }

    def update(Long id, Long version) {
        def EOEventInstance = EOEvent.get(id)
        if (!EOEventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'EOEvent.label', default: 'EOEvent'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (EOEventInstance.version > version) {
                EOEventInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'EOEvent.label', default: 'EOEvent')] as Object[],
                          "Another user has updated this EOEvent while you were editing")
                render(view: "edit", model: [EOEventInstance: EOEventInstance])
                return
            }
        }

        EOEventInstance.properties = params

        if (!EOEventInstance.save(flush: true)) {
            render(view: "edit", model: [EOEventInstance: EOEventInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'EOEvent.label', default: 'EOEvent'), EOEventInstance.id])
        redirect(action: "show", id: EOEventInstance.id)
    }

    def delete(Long id) {
        def EOEventInstance = EOEvent.get(id)
        if (!EOEventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'EOEvent.label', default: 'EOEvent'), id])
            redirect(action: "list")
            return
        }

        try {
            EOEventInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'EOEvent.label', default: 'EOEvent'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'EOEvent.label', default: 'EOEvent'), id])
            redirect(action: "show", id: id)
        }
    }
}
