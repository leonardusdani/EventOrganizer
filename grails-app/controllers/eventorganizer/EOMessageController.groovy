package eventorganizer

import org.springframework.dao.DataIntegrityViolationException

class EOMessageController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [EOMessageInstanceList: EOMessage.list(params), EOMessageInstanceTotal: EOMessage.count()]
    }

    def create() {
        [EOMessageInstance: new EOMessage(params)]
    }

    def save() {
        def EOMessageInstance = new EOMessage(params)
        if (!EOMessageInstance.save(flush: true)) {
            render(view: "create", model: [EOMessageInstance: EOMessageInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'EOMessage.label', default: 'EOMessage'), EOMessageInstance.id])
        redirect(action: "show", id: EOMessageInstance.id)
    }

    def show(Long id) {
        def EOMessageInstance = EOMessage.get(id)
        if (!EOMessageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'EOMessage.label', default: 'EOMessage'), id])
            redirect(action: "list")
            return
        }

        [EOMessageInstance: EOMessageInstance]
    }

    def edit(Long id) {
        def EOMessageInstance = EOMessage.get(id)
        if (!EOMessageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'EOMessage.label', default: 'EOMessage'), id])
            redirect(action: "list")
            return
        }

        [EOMessageInstance: EOMessageInstance]
    }

    def update(Long id, Long version) {
        def EOMessageInstance = EOMessage.get(id)
        if (!EOMessageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'EOMessage.label', default: 'EOMessage'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (EOMessageInstance.version > version) {
                EOMessageInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'EOMessage.label', default: 'EOMessage')] as Object[],
                          "Another user has updated this EOMessage while you were editing")
                render(view: "edit", model: [EOMessageInstance: EOMessageInstance])
                return
            }
        }

        EOMessageInstance.properties = params

        if (!EOMessageInstance.save(flush: true)) {
            render(view: "edit", model: [EOMessageInstance: EOMessageInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'EOMessage.label', default: 'EOMessage'), EOMessageInstance.id])
        redirect(action: "show", id: EOMessageInstance.id)
    }

    def delete(Long id) {
        def EOMessageInstance = EOMessage.get(id)
        if (!EOMessageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'EOMessage.label', default: 'EOMessage'), id])
            redirect(action: "list")
            return
        }

        try {
            EOMessageInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'EOMessage.label', default: 'EOMessage'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'EOMessage.label', default: 'EOMessage'), id])
            redirect(action: "show", id: id)
        }
    }
}
