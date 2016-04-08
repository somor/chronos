package au.com.chronos.impl

import org.junit.Before
import org.junit.Test

class LeapYearTests extends GroovyTestCase {

    LeapYear leapYear

    @Before
    void setUp() {
        leapYear = new LeapYear()
    }

    @Test
    void testIfALeapYear() {
        assertTrue leapYear.isLeapYear(2004)
        assertTrue leapYear.isLeapYear(2008)
        assertTrue leapYear.isLeapYear(2012)
        assertTrue leapYear.isLeapYear(2000)
        assertTrue leapYear.isLeapYear(2400)
    }

    @Test
    void testIfNotALeapYear() {
        assertFalse leapYear.isLeapYear(1900)
        assertFalse leapYear.isLeapYear(2100)
    }

    @Test
    void testCalculateLeapsReursive() {
        assertEquals 30, leapYear.calculateLeapYearsRecursive(2020,0)
        assertEquals 250, leapYear.calculateLeapYearsRecursive(2929,0)
    }

    @Test
    void testCalculateLeapsFormula() {
        assertEquals 30, leapYear.calculateLeapYearsFormula(2020)
        assertEquals 250, leapYear.calculateLeapYearsFormula(2929)
    }



}
