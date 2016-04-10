package steps.gebsite.pages
import geb.Module

class MenuModule extends Module {

    static content = {
        toggle { children("span") }
        link(to: TheBookOfGebPage, toWait: true) {text-> $('.link-list li a',text: contains(text)) }
    }
}