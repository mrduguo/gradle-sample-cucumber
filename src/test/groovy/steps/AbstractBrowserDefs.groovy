package steps

import geb.Browser
import geb.ConfigurationLoader
import steps.common.BrowserPhantomJSInstaller

abstract class AbstractBrowserDefs extends AbstractDefs {

    private static Browser browser

    Browser getBrowser() {
        if (browser == null) {
            def conf = new ConfigurationLoader().getConf()
            conf.reportsDir = new File('build/reports/browser-screenshot')
            browser = new Browser(conf)
            if (config('BROWSER_FIREFOX_DRIVER') == 'true') {
                // default is firefox
            } else {
                browser.driver = BrowserPhantomJSInstaller.usePhantomJS()
            }
        }
        browser
    }

    def methodMissing(String name, args) {
        getBrowser()."$name"(*args)
    }

    def propertyMissing(String name) {
        getBrowser()."$name"
    }

    def propertyMissing(String name, value) {
        getBrowser()."$name" = value
    }

    def takenScreenShotIfFailed(def action) {
        try {
            action()
        } catch (Throwable ex) {
            getBrowser().report("test-failure-${System.currentTimeMillis()}")
            throw ex
        }
    }
}