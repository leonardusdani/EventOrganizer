package eventorganizer



import org.junit.*
import grails.test.mixin.*

@TestFor(EOTaskController)
@Mock(EOTask)
class EOTaskControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/EOTask/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.EOTaskInstanceList.size() == 0
        assert model.EOTaskInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.EOTaskInstance != null
    }

    void testSave() {
        controller.save()

        assert model.EOTaskInstance != null
        assert view == '/EOTask/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/EOTask/show/1'
        assert controller.flash.message != null
        assert EOTask.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/EOTask/list'

        populateValidParams(params)
        def EOTask = new EOTask(params)

        assert EOTask.save() != null

        params.id = EOTask.id

        def model = controller.show()

        assert model.EOTaskInstance == EOTask
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/EOTask/list'

        populateValidParams(params)
        def EOTask = new EOTask(params)

        assert EOTask.save() != null

        params.id = EOTask.id

        def model = controller.edit()

        assert model.EOTaskInstance == EOTask
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/EOTask/list'

        response.reset()

        populateValidParams(params)
        def EOTask = new EOTask(params)

        assert EOTask.save() != null

        // test invalid parameters in update
        params.id = EOTask.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/EOTask/edit"
        assert model.EOTaskInstance != null

        EOTask.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/EOTask/show/$EOTask.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        EOTask.clearErrors()

        populateValidParams(params)
        params.id = EOTask.id
        params.version = -1
        controller.update()

        assert view == "/EOTask/edit"
        assert model.EOTaskInstance != null
        assert model.EOTaskInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/EOTask/list'

        response.reset()

        populateValidParams(params)
        def EOTask = new EOTask(params)

        assert EOTask.save() != null
        assert EOTask.count() == 1

        params.id = EOTask.id

        controller.delete()

        assert EOTask.count() == 0
        assert EOTask.get(EOTask.id) == null
        assert response.redirectedUrl == '/EOTask/list'
    }
}
