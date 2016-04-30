package steps

import app.TestApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = TestApplication.class, loader = SpringApplicationContextLoader.class)
abstract class AbstractDefs
{
    @Autowired
    ApplicationContext applicationContext

    String config(String key,String defaultValue=null){
        if(System.properties.containsKey(key)){
            System.properties[key]
        }else if(System.getenv().containsKey(key)){
            System.getenv()[key]
        }else{
            defaultValue
        }
    }
}