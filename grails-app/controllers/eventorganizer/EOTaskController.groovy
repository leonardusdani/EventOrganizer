package eventorganizer

import org.springframework.dao.DataIntegrityViolationException

class EOTaskController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [EOTaskInstanceList: EOTask.list(params), EOTaskInstanceTotal: EOTask.count()]
    }

    def create() {
        [EOTaskInstance: new EOTask(params)]
    }

    def save() {
        def EOTaskInstance = new EOTask(params)
        if (!EOTaskInstance.save(flush: true)) {
            render(view: "create", model: [EOTaskInstance: EOTaskInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'EOTask.label', default: 'EOTask'), EOTaskInstance.id])
        redirect(action: "show", id: EOTaskInstance.id)
    }

    def show(Long id) {
        def EOTaskInstance = EOTask.get(id)
        if (!EOTaskInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'EOTask.label', default: 'EOTask'), id])
            redirect(action: "list")
            return
        }

        [EOTaskInstance: EOTaskInstance]
    }

    def edit(Long id) {
        def EOTaskInstance = EOTask.get(id)
        if (!EOTaskInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'EOTask.label', default: 'EOTask'), id])
            redirect(action: "list")
            return
        }

        [EOTaskInstance: EOTaskInstance]
    }

    def update(Long id, Long version) {
        def EOTaskInstance = EOTask.get(id)
        if (!EOTaskInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'EOTask.label', default: 'EOTask'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (EOTaskInstance.version > version) {
                EOTaskInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'EOTask.label', default: 'EOTask')] as Object[],
                          "Another user has updated this EOTask while you were editing")
                render(view: "edit", model: [EOTaskInstance: EOTaskInstance])
                return
            }
        }

        EOTaskInstance.properties = params

        if (!EOTaskInstance.save(flush: true)) {
            render(view: "edit", model: [EOTaskInstance: EOTaskInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'EOTask.label', default: 'EOTask'), EOTaskInstance.id])
        redirect(action: "show", id: EOTaskInstance.id)
    }

    def delete(Long id) {
        def EOTaskInstance = EOTask.get(id)
        if (!EOTaskInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'EOTask.label', default: 'EOTask'), id])
            redirect(action: "list")
            return
        }

        try {
            EOTaskInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'EOTask.label', default: 'EOTask'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'EOTask.label', default: 'EOTask'), id])
            redirect(action: "show", id: id)
        }
    }
}
