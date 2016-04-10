package steps.gebsite

import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.springframework.beans.factory.annotation.Autowired
import steps.AbstractBrowserDefs
import steps.common.BrowserSteps
import steps.gebsite.pages.GebishOrgHomePage
import steps.gebsite.pages.TheBookOfGebPage

class GebSiteSteps extends AbstractBrowserDefs {


    @Autowired
    BrowserSteps browserSteps

    @When('^GEBSITE go home page$')
    def goHomePage() {
        browser.baseUrl='http://www.gebish.org'
        browser.to GebishOrgHomePage
    }

    @Then('^GEBSITE click manual link contains (.*)')
    def clickHomePageManualLink(String textContains) {
        takenScreenShotIfFailed{
            ((GebishOrgHomePage)browser.page).clickMenuLink(textContains)
        }
    }

    @Then('^GEBSITE on TheBookOfGebPage')
    def isOnManualPage() {
        at(TheBookOfGebPage)
    }
}
