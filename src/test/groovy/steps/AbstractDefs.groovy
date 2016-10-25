package steps

import app.TestApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.context.ApplicationContext
import org.springframework.core.env.Environment
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = TestApplication.class, loader = SpringApplicationContextLoader.class)
abstract class AbstractDefs
{
    @Autowired
    ApplicationContext applicationContext

    @Autowired
    Environment env;

    String config(String key, String defaultValue = null) {
        env.getProperty(key,(String)defaultValue)
    }

    String resolvePlaceholder(String key) {
        env.resolveRequiredPlaceholders(key)
    }
}