package eventorganizer



import org.junit.*
import grails.test.mixin.*

@TestFor(EOMessageController)
@Mock(EOMessage)
class EOMessageControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/EOMessage/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.EOMessageInstanceList.size() == 0
        assert model.EOMessageInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.EOMessageInstance != null
    }

    void testSave() {
        controller.save()

        assert model.EOMessageInstance != null
        assert view == '/EOMessage/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/EOMessage/show/1'
        assert controller.flash.message != null
        assert EOMessage.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/EOMessage/list'

        populateValidParams(params)
        def EOMessage = new EOMessage(params)

        assert EOMessage.save() != null

        params.id = EOMessage.id

        def model = controller.show()

        assert model.EOMessageInstance == EOMessage
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/EOMessage/list'

        populateValidParams(params)
        def EOMessage = new EOMessage(params)

        assert EOMessage.save() != null

        params.id = EOMessage.id

        def model = controller.edit()

        assert model.EOMessageInstance == EOMessage
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/EOMessage/list'

        response.reset()

        populateValidParams(params)
        def EOMessage = new EOMessage(params)

        assert EOMessage.save() != null

        // test invalid parameters in update
        params.id = EOMessage.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/EOMessage/edit"
        assert model.EOMessageInstance != null

        EOMessage.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/EOMessage/show/$EOMessage.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        EOMessage.clearErrors()

        populateValidParams(params)
        params.id = EOMessage.id
        params.version = -1
        controller.update()

        assert view == "/EOMessage/edit"
        assert model.EOMessageInstance != null
        assert model.EOMessageInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/EOMessage/list'

        response.reset()

        populateValidParams(params)
        def EOMessage = new EOMessage(params)

        assert EOMessage.save() != null
        assert EOMessage.count() == 1

        params.id = EOMessage.id

        controller.delete()

        assert EOMessage.count() == 0
        assert EOMessage.get(EOMessage.id) == null
        assert response.redirectedUrl == '/EOMessage/list'
    }
}
