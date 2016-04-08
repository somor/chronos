package au.com.au.com.chronos.scripts

import org.junit.After
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.assertEquals

class DateCalculatorScriptsTests {

    GroovyShell shell
    Binding binding
    PrintStream orig
    ByteArrayOutputStream out

    @Before
    void setUp() {
        orig = System.out
        out = new ByteArrayOutputStream()
        System.setOut(new PrintStream(out))
        binding = new Binding()
        shell = new GroovyShell(binding)
    }

    @After
    void tearDown() {
        System.setOut(orig)
    }

    @Test
    void testInvalidValidStartDate() {
        binding.setVariable("args", ["07-11/1972", "08/11/1972"])
        shell.evaluate(new File("src/au/com/au/com/chronos/scripts/datecalculator.groovy"))
        assertEquals "Date must be in the following format: DD/MM/YYYY", out.toString().trim()
    }

    @Test
    void testIsFourthYearALeapYear() {
        binding.setVariable("args", ["07/11/1972", "08/11-1972"])
        shell.evaluate(new File("src/au/com/au/com/chronos/scripts/datecalculator.groovy"))
        assertEquals "Date must be in the following format: DD/MM/YYYY", out.toString().trim()
    }


    @Test
    void testCalculateDaysInBetweenOne() {

        binding.setVariable("args", ["07/11/1972", "08/11/1972"])
        shell.evaluate(new File("src/au/com/au/com/chronos/scripts/datecalculator.groovy"))
        assertEquals "Number of full days between 07/11/1972 and 08/11/1972: 0 days", out.toString().trim()

    }

    @Test
    void testCalculateDaysInBetweenTwo() {

        binding.setVariable("args", ["01/01/2000", "13/01/2000"])
        shell.evaluate(new File("src/au/com/au/com/chronos/scripts/datecalculator.groovy"))
        assertEquals "Number of full days between 01/01/2000 and 13/01/2000: 11 days", out.toString().trim()

    }

    @Test
    void testCalculateDaysInBetweenThree() {

        binding.setVariable("args", ["02/06/1983", "22/06/1983"])
        shell.evaluate(new File("src/au/com/au/com/chronos/scripts/datecalculator.groovy"))
        assertEquals "Number of full days between 02/06/1983 and 22/06/1983: 19 days", out.toString().trim()

    }

    @Test
    void testCalculateDaysInBetweenFour() {

        binding.setVariable("args", ["04/07/1984", "25/12/1984"])
        shell.evaluate(new File("src/au/com/au/com/chronos/scripts/datecalculator.groovy"))
        assertEquals "Number of full days between 04/07/1984 and 25/12/1984: 173 days", out.toString().trim()

    }

    @Test
    void testCalculateDaysInBetweenFive() {

        binding.setVariable("args", ["03/08/1983", "03/01/1989"])
        shell.evaluate(new File("src/au/com/au/com/chronos/scripts/datecalculator.groovy"))
        assertEquals "Number of full days between 03/08/1983 and 03/01/1989: 1979 days", out.toString().trim()

    }

    @Test
    void testCalculateDaysInBetweenSix() {

        binding.setVariable("args", ["01/01/1901", "31/12/2999"])
        shell.evaluate(new File("src/au/com/au/com/chronos/scripts/datecalculator.groovy"))
        assertEquals "Number of full days between 01/01/1901 and 31/12/2999: 401400 days", out.toString().trim()

    }


    @Test
    void testCalculateDaysInBetweenWithEndDateFirst() {

        binding.setVariable("args", ["31/12/2999", "01/01/1901"])
        shell.evaluate(new File("src/au/com/au/com/chronos/scripts/datecalculator.groovy"))
        assertEquals "Number of full days between 31/12/2999 and 01/01/1901: 401400 days", out.toString().trim()

    }

    @Test
    void testCalculateDaysInBetweenWithEndDateFirstTwo() {

        binding.setVariable("args", ["03/01/1989", "03/08/1983"])
        shell.evaluate(new File("src/au/com/au/com/chronos/scripts/datecalculator.groovy"))
        assertEquals "Number of full days between 03/01/1989 and 03/08/1983: 1979 days", out.toString().trim()

    }


}
