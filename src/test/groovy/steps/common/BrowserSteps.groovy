package steps.common

import cucumber.api.java.en.Given
import cucumber.api.java.en.When
import geb.Browser
import steps.AbstractBrowserDefs
import steps.AbstractDefs

class BrowserSteps extends AbstractBrowserDefs {

    @Given('^BROWSER base url (.*)$')
    def setBaseUrl(String baseUrl) {
        browser.baseUrl=baseUrl
    }

    @When('^BROWSER go (.*)$')
    def go(String path) {
        browser.go path
    }

    @When('^BROWSER title (.*)$')
    def title(String pageTitle) {
        assert browser.title==pageTitle
    }

    @When('^BROWSER has element (.*)=(.*)$')
    def path(String selector,String value) {
        assert browser.$(selector).first().text()==value
    }
}
