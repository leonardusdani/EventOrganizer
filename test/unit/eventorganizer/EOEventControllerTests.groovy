package eventorganizer



import org.junit.*
import grails.test.mixin.*

@TestFor(EOEventController)
@Mock(EOEvent)
class EOEventControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/EOEvent/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.EOEventInstanceList.size() == 0
        assert model.EOEventInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.EOEventInstance != null
    }

    void testSave() {
        controller.save()

        assert model.EOEventInstance != null
        assert view == '/EOEvent/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/EOEvent/show/1'
        assert controller.flash.message != null
        assert EOEvent.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/EOEvent/list'

        populateValidParams(params)
        def EOEvent = new EOEvent(params)

        assert EOEvent.save() != null

        params.id = EOEvent.id

        def model = controller.show()

        assert model.EOEventInstance == EOEvent
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/EOEvent/list'

        populateValidParams(params)
        def EOEvent = new EOEvent(params)

        assert EOEvent.save() != null

        params.id = EOEvent.id

        def model = controller.edit()

        assert model.EOEventInstance == EOEvent
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/EOEvent/list'

        response.reset()

        populateValidParams(params)
        def EOEvent = new EOEvent(params)

        assert EOEvent.save() != null

        // test invalid parameters in update
        params.id = EOEvent.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/EOEvent/edit"
        assert model.EOEventInstance != null

        EOEvent.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/EOEvent/show/$EOEvent.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        EOEvent.clearErrors()

        populateValidParams(params)
        params.id = EOEvent.id
        params.version = -1
        controller.update()

        assert view == "/EOEvent/edit"
        assert model.EOEventInstance != null
        assert model.EOEventInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/EOEvent/list'

        response.reset()

        populateValidParams(params)
        def EOEvent = new EOEvent(params)

        assert EOEvent.save() != null
        assert EOEvent.count() == 1

        params.id = EOEvent.id

        controller.delete()

        assert EOEvent.count() == 0
        assert EOEvent.get(EOEvent.id) == null
        assert response.redirectedUrl == '/EOEvent/list'
    }
}
