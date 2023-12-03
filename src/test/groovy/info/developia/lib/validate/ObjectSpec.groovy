package info.developia.lib.validate

import info.developia.lib.validate.Object
import spock.lang.Specification

class ObjectSpec extends Specification {

    class MyObject {}

    def "should return object"() {
        given:
        MyObject myObject = new MyObject()
        when:
        def result = Object.notNull(myObject)
        then:
        myObject == result
    }

    def "should throw a NullPointer exception"() {
        given:
        MyObject myObject = null
        when:
        Object.notNull(myObject)
        then:
        thrown(NullPointerException.class)
    }

    def "should throw a NullPointer exception with message #message"() {
        given:
        String message = "Oh, this shouldn't be null!!"
        MyObject myObject = null
        when:
        Object.notNull(myObject, message)
        then:
        def exception = thrown(NullPointerException.class)
        message == exception.getMessage()
    }


}
