package au.com.chronos.impl

import org.junit.Before
import org.junit.Test

class DateCalcTests extends GroovyTestCase {

    DateCalc dateCalc

    @Before
    void setUp() {
        dateCalc = new DateCalc()
    }

    @Test
    void testCalculateDaysInBetween() {

        // 07/11/1972 - 08/11/1972 = 0
        assertEquals 0, dateCalc.calculateDaysInBetween("07/11/1972", "08/11/1972")
        // 01/01/2000 - 13/01/2000 = 11
        assertEquals 11, dateCalc.calculateDaysInBetween("01/01/2000", "13/01/2000")
        // 02/06/1983 - 22/06/1983: 19 days
        assertEquals 19, dateCalc.calculateDaysInBetween("02/06/1983", "22/06/1983")
        // 04/07/1984 - 25/12/1984: 173 days
        assertEquals 173, dateCalc.calculateDaysInBetween("04/07/1984", "25/12/1984")
        // 03/01/1989 - 03/08/1983: 1979 days
        assertEquals 1979, dateCalc.calculateDaysInBetween("03/08/1983", "03/01/1989")
        // 01/01/1901 - 31/12/2999: 401,402
        assertEquals 401400, dateCalc.calculateDaysInBetween("01/01/1901", "31/12/2999")

    }


}
