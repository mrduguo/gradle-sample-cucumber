package steps.common

import com.jayway.restassured.RestAssured
import com.jayway.restassured.response.Response
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import steps.AbstractDefs

class RestSteps  extends AbstractDefs {

    def request = RestAssured.with()
    Response response

    @Given('^REST base url (.*)$')
    def setRestBaseUrl(String baseUrl) {
        request.given().baseUri(baseUrl)
    }

    @When('^REST GET (.*)$')
    def whenRestGet(String path) {
        if(path.charAt(0)!='/'){
            def paths=path.split('/',2)
            setRestBaseUrl(resolvePlaceholder(paths[0]))
            path=paths[1]
        }
        response=request.when().
                get(path).thenReturn()
    }

    @Then('^REST status (.*)$')
    def thenRestStatus(int status) {
        assert response.statusCode()==status
    }

    @Then('^REST contains path (.*)=(.*)$')
    def thenRestContainsPath(String path, String value) {
        assert response.path(path)?.toString()==value
    }

    @Then('^REST contains text (.*)$')
    def thenRestContainsText(String expectedText) {
        assert response.asString().contains(expectedText)
    }

    @Then('^REST has item (.*)=(.*)$')
    def thenRestHasItemPath(String attribute, String value) {
        assert response.path('.').find{it[attribute]==value}
    }
}
