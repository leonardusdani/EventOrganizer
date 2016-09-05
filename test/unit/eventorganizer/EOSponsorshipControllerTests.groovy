package eventorganizer



import org.junit.*
import grails.test.mixin.*

@TestFor(EOSponsorshipController)
@Mock(EOSponsorship)
class EOSponsorshipControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/EOSponsorship/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.EOSponsorshipInstanceList.size() == 0
        assert model.EOSponsorshipInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.EOSponsorshipInstance != null
    }

    void testSave() {
        controller.save()

        assert model.EOSponsorshipInstance != null
        assert view == '/EOSponsorship/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/EOSponsorship/show/1'
        assert controller.flash.message != null
        assert EOSponsorship.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/EOSponsorship/list'

        populateValidParams(params)
        def EOSponsorship = new EOSponsorship(params)

        assert EOSponsorship.save() != null

        params.id = EOSponsorship.id

        def model = controller.show()

        assert model.EOSponsorshipInstance == EOSponsorship
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/EOSponsorship/list'

        populateValidParams(params)
        def EOSponsorship = new EOSponsorship(params)

        assert EOSponsorship.save() != null

        params.id = EOSponsorship.id

        def model = controller.edit()

        assert model.EOSponsorshipInstance == EOSponsorship
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/EOSponsorship/list'

        response.reset()

        populateValidParams(params)
        def EOSponsorship = new EOSponsorship(params)

        assert EOSponsorship.save() != null

        // test invalid parameters in update
        params.id = EOSponsorship.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/EOSponsorship/edit"
        assert model.EOSponsorshipInstance != null

        EOSponsorship.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/EOSponsorship/show/$EOSponsorship.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        EOSponsorship.clearErrors()

        populateValidParams(params)
        params.id = EOSponsorship.id
        params.version = -1
        controller.update()

        assert view == "/EOSponsorship/edit"
        assert model.EOSponsorshipInstance != null
        assert model.EOSponsorshipInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/EOSponsorship/list'

        response.reset()

        populateValidParams(params)
        def EOSponsorship = new EOSponsorship(params)

        assert EOSponsorship.save() != null
        assert EOSponsorship.count() == 1

        params.id = EOSponsorship.id

        controller.delete()

        assert EOSponsorship.count() == 0
        assert EOSponsorship.get(EOSponsorship.id) == null
        assert response.redirectedUrl == '/EOSponsorship/list'
    }
}
