package eventorganizer



import org.junit.*
import grails.test.mixin.*

@TestFor(EOSponsorController)
@Mock(EOSponsor)
class EOSponsorControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/EOSponsor/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.EOSponsorInstanceList.size() == 0
        assert model.EOSponsorInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.EOSponsorInstance != null
    }

    void testSave() {
        controller.save()

        assert model.EOSponsorInstance != null
        assert view == '/EOSponsor/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/EOSponsor/show/1'
        assert controller.flash.message != null
        assert EOSponsor.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/EOSponsor/list'

        populateValidParams(params)
        def EOSponsor = new EOSponsor(params)

        assert EOSponsor.save() != null

        params.id = EOSponsor.id

        def model = controller.show()

        assert model.EOSponsorInstance == EOSponsor
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/EOSponsor/list'

        populateValidParams(params)
        def EOSponsor = new EOSponsor(params)

        assert EOSponsor.save() != null

        params.id = EOSponsor.id

        def model = controller.edit()

        assert model.EOSponsorInstance == EOSponsor
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/EOSponsor/list'

        response.reset()

        populateValidParams(params)
        def EOSponsor = new EOSponsor(params)

        assert EOSponsor.save() != null

        // test invalid parameters in update
        params.id = EOSponsor.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/EOSponsor/edit"
        assert model.EOSponsorInstance != null

        EOSponsor.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/EOSponsor/show/$EOSponsor.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        EOSponsor.clearErrors()

        populateValidParams(params)
        params.id = EOSponsor.id
        params.version = -1
        controller.update()

        assert view == "/EOSponsor/edit"
        assert model.EOSponsorInstance != null
        assert model.EOSponsorInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/EOSponsor/list'

        response.reset()

        populateValidParams(params)
        def EOSponsor = new EOSponsor(params)

        assert EOSponsor.save() != null
        assert EOSponsor.count() == 1

        params.id = EOSponsor.id

        controller.delete()

        assert EOSponsor.count() == 0
        assert EOSponsor.get(EOSponsor.id) == null
        assert response.redirectedUrl == '/EOSponsor/list'
    }
}
