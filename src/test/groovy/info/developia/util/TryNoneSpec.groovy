package info.developia.util

import spock.lang.Specification

class TryNoneSpec extends Specification {

    def "should return None"() {
        when:
        var result = Try.of(() -> print("Hello world!!"))
        then:
        result instanceof None.Nothing
    }

    def "should return Failure with division by zero message"() {
        when:
        def result = Try.of(() -> print("Calculate ${1 / 0}"))
        then:
        result instanceof None.Failure
        (result as None.Failure).error().getMessage() == "Division by zero"
    }

    def "should return exception with custom message"() {
        given:
        def exceptionMessage = "Ops, it's an error!"
        when:
        Try.of(() -> print("Calculate ${1 / 0}")).orFail(new RuntimeException(exceptionMessage))
        then:
        def exception = thrown(RuntimeException)
        exception.getMessage() == exceptionMessage
    }

    def "should return exception with provided exception including exception cause on message"() {
        given:
        def exceptionMessage = "Ops, it's an error! "
        var result = Try.of(() -> print("Calculate ${1 / 0}"))
        when:
        result.orFailWith((error) -> new RuntimeException("$exceptionMessage $error"))
        then:
        def exception = thrown(RuntimeException)
        exception.getMessage() == "$exceptionMessage java.lang.ArithmeticException: Division by zero"
    }
}
