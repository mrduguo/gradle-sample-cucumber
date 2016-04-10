package app

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.yaml.snakeyaml.Yaml

@Configuration
class EnvConfig {

    @Bean
    def testEnv(){
        new Yaml().loadAs(
                getClass().getResourceAsStream(
                        "/${System.properties.testEnv?:System.getenv().testEnv?:'ci'}-environment.yml"
                ),
                Map.class
        )
    }
}
