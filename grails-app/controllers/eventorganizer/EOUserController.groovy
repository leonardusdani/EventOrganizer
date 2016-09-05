package eventorganizer

import org.springframework.dao.DataIntegrityViolationException

class EOUserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [EOUserInstanceList: EOUser.list(params), EOUserInstanceTotal: EOUser.count()]
    }

    def create() {
        [EOUserInstance: new EOUser(params)]
    }

    def save() {
        def EOUserInstance = new EOUser(params)
        if (!EOUserInstance.save(flush: true)) {
            render(view: "create", model: [EOUserInstance: EOUserInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'EOUser.label', default: 'EOUser'), EOUserInstance.id])
        redirect(action: "show", id: EOUserInstance.id)
    }

    def show(Long id) {
        def EOUserInstance = EOUser.get(id)
        if (!EOUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'EOUser.label', default: 'EOUser'), id])
            redirect(action: "list")
            return
        }

        [EOUserInstance: EOUserInstance]
    }

    def edit(Long id) {
        def EOUserInstance = EOUser.get(id)
        if (!EOUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'EOUser.label', default: 'EOUser'), id])
            redirect(action: "list")
            return
        }

        [EOUserInstance: EOUserInstance]
    }

    def update(Long id, Long version) {
        def EOUserInstance = EOUser.get(id)
        if (!EOUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'EOUser.label', default: 'EOUser'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (EOUserInstance.version > version) {
                EOUserInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'EOUser.label', default: 'EOUser')] as Object[],
                          "Another user has updated this EOUser while you were editing")
                render(view: "edit", model: [EOUserInstance: EOUserInstance])
                return
            }
        }

        EOUserInstance.properties = params

        if (!EOUserInstance.save(flush: true)) {
            render(view: "edit", model: [EOUserInstance: EOUserInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'EOUser.label', default: 'EOUser'), EOUserInstance.id])
        redirect(action: "show", id: EOUserInstance.id)
    }

    def delete(Long id) {
        def EOUserInstance = EOUser.get(id)
        if (!EOUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'EOUser.label', default: 'EOUser'), id])
            redirect(action: "list")
            return
        }

        try {
            EOUserInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'EOUser.label', default: 'EOUser'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'EOUser.label', default: 'EOUser'), id])
            redirect(action: "show", id: id)
        }
    }
}
