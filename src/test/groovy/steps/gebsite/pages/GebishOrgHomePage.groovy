package steps.gebsite.pages
import geb.Page

class GebishOrgHomePage extends Page {

    static at = { title == "Geb - Very Groovy Browser Automation" }

    static content = {
        manualsMenu { $("#header-content ul li", 0).module MenuModule}
    }

    def clickMenuLink(def textContains){
        interact {
            moveToElement(manualsMenu.toggle)
        }
        manualsMenu.link(textContains).click()
    }
}