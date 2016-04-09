package steps.network

import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import steps.AbstractDefs

import java.util.concurrent.TimeUnit

class BasicNetworkSteps extends AbstractDefs {

    String status

    @Given('^NET LOADED$')
    def load() {}

    @When('^NET ping (.*)$')
    def ping(String address) {
        boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win")
        ProcessBuilder processBuilder = new ProcessBuilder("ping", isWindows ? "-n" : "-c", "1", address)
        Process proc = processBuilder.start()
        try {
            assert proc.waitFor(5, TimeUnit.SECONDS)
            assert proc.exitValue() == 0
            status = 'success'
        } catch (Throwable ex) {
            status = 'failed'
        }
    }

    @When('^NET telnet (.*) (\\d*)$')
    def telnet(String address, int port) {
        try {
            new Socket(address, port)
            status = 'success'
        } catch (UnknownHostException e) {
            status = "unknown_host"
        } catch (IOException e) {
            status = 'port_not_open'
        }
    }

    @Then('^NET status should (\\w*)$')
    def statusShouldBe(String expectedStatus) {
        assert status == expectedStatus
    }
}
