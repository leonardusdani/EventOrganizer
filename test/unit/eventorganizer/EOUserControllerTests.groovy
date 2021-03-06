package eventorganizer



import org.junit.*
import grails.test.mixin.*

@TestFor(EOUserController)
@Mock(EOUser)
class EOUserControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/EOUser/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.EOUserInstanceList.size() == 0
        assert model.EOUserInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.EOUserInstance != null
    }

    void testSave() {
        controller.save()

        assert model.EOUserInstance != null
        assert view == '/EOUser/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/EOUser/show/1'
        assert controller.flash.message != null
        assert EOUser.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/EOUser/list'

        populateValidParams(params)
        def EOUser = new EOUser(params)

        assert EOUser.save() != null

        params.id = EOUser.id

        def model = controller.show()

        assert model.EOUserInstance == EOUser
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/EOUser/list'

        populateValidParams(params)
        def EOUser = new EOUser(params)

        assert EOUser.save() != null

        params.id = EOUser.id

        def model = controller.edit()

        assert model.EOUserInstance == EOUser
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/EOUser/list'

        response.reset()

        populateValidParams(params)
        def EOUser = new EOUser(params)

        assert EOUser.save() != null

        // test invalid parameters in update
        params.id = EOUser.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/EOUser/edit"
        assert model.EOUserInstance != null

        EOUser.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/EOUser/show/$EOUser.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        EOUser.clearErrors()

        populateValidParams(params)
        params.id = EOUser.id
        params.version = -1
        controller.update()

        assert view == "/EOUser/edit"
        assert model.EOUserInstance != null
        assert model.EOUserInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/EOUser/list'

        response.reset()

        populateValidParams(params)
        def EOUser = new EOUser(params)

        assert EOUser.save() != null
        assert EOUser.count() == 1

        params.id = EOUser.id

        controller.delete()

        assert EOUser.count() == 0
        assert EOUser.get(EOUser.id) == null
        assert response.redirectedUrl == '/EOUser/list'
    }
}
