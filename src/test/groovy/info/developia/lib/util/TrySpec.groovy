package info.developia.lib.util

import info.developia.lib.util.Result
import info.developia.lib.util.Try
import spock.lang.Specification

import java.util.function.Supplier

class TrySpec extends Specification {

    def "should return Success with expected value"() {
        given:
        String message = "Hello world!!"
        Supplier<String> supplier = { return message }
        when:
        var result = Try.of(supplier)
        then:
        result instanceof Result.Success
        result.get() == message
    }

    def "should return Failure with division by zero"() {
        given:
        Supplier<String> supplier = { return "Calculate ${1 / 0}" }
        when:
        var result = Try.of(supplier)
        then:
        result instanceof Result.Failure
        result.fail().getMessage() == "Division by zero"
    }

    def "should return Success with expected value even with fallback"() {
        given:
        String message = "Hello world!!"
        String fallbackMessage = "fallback value"
        Supplier<String> supplier = { return message }
        when:
        var result = Try.of(supplier)
        then:
        result instanceof Result.Success
        result.getOr(fallbackMessage) == message
    }

    def "should return Success with mapped value"() {
        given:
        String message = "Hello world!!"
        String fallbackMessage = "fallback value"
        Supplier<String> supplier = { return message }
        when:
        var result = Try.of(supplier).map(value -> value.toUpperCase())
        then:
        result instanceof Result.Success
        result.getOr(fallbackMessage) == message.toUpperCase()
    }

    def "should return Failure and fallback value with division by zero"() {
        given:
        String fallbackValue = "fallback value"
        Supplier<String> supplier = { return "Calculate ${1 / 0}" }
        when:
        var result = Try.of(supplier)
        then:
        result instanceof Result.Failure
        result.getOr(fallbackValue) == fallbackValue
    }

    def "should return Failure and fails with provided exception"() {
        given:
        Supplier<String> supplier = { return "Calculate ${1 / 0}" }
        var result = Try.of(supplier)
        when:
        result.getOrFailWith(new RuntimeException("Opps, it's an error!"))
        then:
        result instanceof Result.Failure
        def exception = thrown(RuntimeException)
        exception.getMessage() == "Opps, it's an error!"
    }

    def "should return Success and value even with provided exception"() {
        given:
        Supplier<String> supplier = { return "Calculate ${1 + 1}" }
        var result = Try.of(supplier)
        when:
        result.getOrFailWith(new RuntimeException("Opps, it's an error!"))
        then:
        result instanceof Result.Success
        noExceptionThrown()
    }

    def "should return Failure and provided exception"() {
        given:
        Supplier<String> supplier = { return "Calculate ${1 / 0}" }
        var result = Try.of(supplier)
        when:
        result.getOrFailWith(new RuntimeException("Opps, it's an error!"))
        then:
        result instanceof Result.Failure
        def exception = thrown(RuntimeException)
        exception.getMessage() == "Opps, it's an error!"
    }

    def "should return Failure and provided exception message"() {
        given:
        Supplier<String> supplier = { return "Calculate ${1 / 0}" }
        var result = Try.of(supplier)
        when:
        result.getOrFailWith((error) -> new RuntimeException("Opps, it's an error! " + error))
        then:
        result instanceof Result.Failure
        def exception = thrown(RuntimeException)
        exception.getMessage() == "Opps, it's an error! java.lang.ArithmeticException: Division by zero"
    }
}
