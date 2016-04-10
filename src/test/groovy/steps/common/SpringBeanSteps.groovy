package steps.common

import app.Foo
import cucumber.api.java.en.When
import org.springframework.beans.factory.annotation.Autowired
import steps.AbstractDefs

class SpringBeanSteps extends AbstractDefs {

    @Autowired
    Foo foo

    @When('^Spring foo name is (.*)$')
    def fooName(String expected) {
        assert foo.name==expected
    }
}
