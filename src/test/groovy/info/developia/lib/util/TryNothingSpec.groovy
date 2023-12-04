package info.developia.lib.util

import spock.lang.Specification

class TryNothingSpec extends Specification {

    def "should return None"() {
        when:
        var result = Try.of(() -> print("Hello world!!"))
        then:
        result instanceof Nothing.None
    }

    def "should return Failure with division by zero message"() {
        when:
        def result = Try.of(() -> print("Calculate ${1 / 0}"))
        then:
        result instanceof Nothing.Failure
        (result as Nothing.Failure).error().getMessage() == "Division by zero"
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
        when:
        Try.of(() -> print("Calculate ${1 / 0}")).orFailWith((error) -> new RuntimeException("$exceptionMessage $error"))
        then:
        def exception = thrown(RuntimeException)
        exception.getMessage() == "$exceptionMessage java.lang.ArithmeticException: Division by zero"
    }
}
