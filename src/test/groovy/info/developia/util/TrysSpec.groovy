package info.developia.util

import spock.lang.Specification

import java.util.function.Supplier

class TrysSpec extends Specification {

    def "should return None on runnable function"() {
        when:
        var result = Trys.of(() -> print("Hello world!!"))
        then:
        result instanceof Result.None
    }

    def "should return Failure with division by zero on runnable function"() {
        when:
        var result = Trys.of(() -> print("Calculate ${1 / 0}"))
        then:
        result instanceof Result.Failure
        result.fail().getMessage() == "Division by zero"
    }

    def "should return Success with expected value on supplier function"() {
        given:
        String message = "Hello world!!"
        Supplier<String> supplier = { return message }
        when:
        var result = Trys.of(supplier)
        then:
        result instanceof Result.Success
        result.get() == message
    }

    def "should return Failure with division by zero on supplier function"() {
        given:
        Supplier<String> supplier = { return "Calculate ${1 / 0}" }
        when:
        var result = Trys.of(supplier)
        then:
        result instanceof Result.Failure
        result.fail().getMessage() == "Division by zero"
    }

    def "should return Success with expected value even with fallback on supplier function"() {
        given:
        String message = "Hello world!!"
        String fallbackMessage = "fallback value"
        Supplier<String> supplier = { return message }
        when:
        var result = Trys.of(supplier)
        then:
        result instanceof Result.Success
        result.getOr(fallbackMessage) == message
    }

    def "should return Failure and fallback value with division by zero on supplier function"() {
        given:
        String fallbackValue = "fallback value"
        Supplier<String> supplier = { return "Calculate ${1 / 0}" }
        when:
        var result = Trys.of(supplier)
        then:
        result instanceof Result.Failure
        result.getOr(fallbackValue) == fallbackValue
    }

    def "should return Failure and fails with provided exception on supplier function"() {
        given:
        Supplier<String> supplier = { return "Calculate ${1 / 0}" }
        var result = Trys.of(supplier)
        when:
        result.getOrFailWith(new RuntimeException("Opps, it's an error!"))
        then:
        result instanceof Result.Failure
        def exception = thrown(RuntimeException)
        exception.getMessage() == "Opps, it's an error!"
    }

    def "should return Success and value even with provided exception on supplier function"() {
        given:
        Supplier<String> supplier = { return "Calculate ${1 + 1}" }
        var result = Trys.of(supplier)
        when:
        result.getOrFailWith(new RuntimeException("Opps, it's an error!"))
        then:
        result instanceof Result.Success
        noExceptionThrown()
    }

    def "should return Failure and provided exception on supplier function"() {
        given:
        Supplier<String> supplier = { return "Calculate ${1 / 0}" }
        var result = Trys.of(supplier)
        when:
        result.getOrFailWith(new RuntimeException("Opps, it's an error!"))
        then:
        result instanceof Result.Failure
        def exception = thrown(RuntimeException)
        exception.getMessage() == "Opps, it's an error!"
    }

    def "should return Failure and provided exception message on supplier function"() {
        given:
        Supplier<String> supplier = { return "Calculate ${1 / 0}" }
        var result = Trys.of(supplier)
        when:
        result.getOrFailWith((error) -> new RuntimeException("Opps, it's an error! " + error))
        then:
        result instanceof Result.Failure
        def exception = thrown(RuntimeException)
        exception.getMessage() == "Opps, it's an error! java.lang.ArithmeticException: Division by zero"
    }
}
