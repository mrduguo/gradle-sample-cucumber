package app

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class Foo {
    @Value('${foo.name:Foo}')
    String name
}
