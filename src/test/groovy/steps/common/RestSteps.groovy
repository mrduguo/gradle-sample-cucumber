package steps.common

import com.jayway.restassured.RestAssured
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import steps.AbstractDefs

class RestSteps  extends AbstractDefs {

    def request = RestAssured.with()
    def response

    @Given('^REST base url (.*)$')
    def setRestBaseUrl(String baseUrl) {
        request.given().baseUri(baseUrl)
    }

    @When('^REST GET (.*)$')
    def whenRestGet(String path) {
        response=request.when().
                get(path).thenReturn()
    }

    @Then('^REST status (.*)$')
    def thenRestStatus(int status) {
        assert response.statusCode()==status
    }

    @Then('^REST json path (.*)=(.*)$')
    def thenRestHasJsonPath(String path, String value) {
        assert response.path(path)?.toString()==value
    }

    @Then('^REST has item (.*)=(.*)$')
    def thenRestHasItemPath(String attribute, String value) {
        assert response.path('.').find{it[attribute]==value}
    }
}
