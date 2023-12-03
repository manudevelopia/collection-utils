package info.developia.lib.types

import info.developia.lib.types.Range
import spock.lang.Specification

class RangeTest extends Specification {
    def "range from to should behave as independent values"() {
        given:
        int from = 1
        int to = 5
        when:
        Range range = Range.of(from, to)
        then:
        range.to() - range.from() == to - from
    }

    def "range should have the same type"() {
        given:
        int from = 1
        String to = '5'
        when:
        Range.of(from, to)
        then:
        thrown(UnsupportedOperationException)
    }
}
