package steps.common

import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.springframework.beans.factory.annotation.Autowired
import steps.AbstractDefs

class ServiceSteps extends AbstractDefs {


    @Autowired
    def testEnv

    @Autowired
    BasicNetworkSteps networkSteps

    @Autowired
    RestSteps restSteps

    def selectedServiceName
    def selectedService

    @When('^SERVICE select (.*)$')
    def select(String service) {
        selectedServiceName = service
        selectedService = testEnv.services[selectedServiceName]
        assert selectedService
    }

    @Then('^SERVICE ping all should success$')
    def ping() {
        doWithServices('host') { service ->
            try {
                networkSteps.ping(service.host)
                networkSteps.statusShouldBe('success')
            } catch (Throwable ex) {
                throw new RuntimeException("ping $selectedServiceName:$service.host failed",ex)
            }
        }
    }

    @Then('^SERVICE telnet all should success$')
    def telnet() {
        doWithServices('port') { service ->
            try {
                networkSteps.telnet(service.host, service.port)
                networkSteps.statusShouldBe('success')
            } catch (Throwable ex) {
                throw new RuntimeException("telnet $selectedServiceName:$service.host:$service.port failed",ex)
            }
        }
    }

    @Then('^SERVICE health check all should success$')
    def healthCheck() {
        doWithServices('health') { service ->
            def baseUrl = "${service.port == 443 ? 'https' : 'http'}://$service.host:$service.port"
            try {
                restSteps.setRestBaseUrl(baseUrl)
                restSteps.whenRestGet(service.health)
                restSteps.thenRestStatus(200)
            } catch (Throwable ex) {
                throw new RuntimeException("health check ${baseUrl}$service.health failed",ex)
            }
        }
    }


    def doWithServices(def requiredField, def action) {
        selectedService.each { service ->
            if (service[requiredField] != null) {
                action(service)
            }
        }
    }
}
