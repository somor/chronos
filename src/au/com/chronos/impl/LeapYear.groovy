package au.com.chronos.impl

class LeapYear {

    // EpochDate "01/01/1901"
    static Integer yearEpoch = 1901

    def isLeapYear(Integer year) {
        return ((year % 4) == 0) && (!((year % 100) == 0) || ((year % 400) == 0))
    }

    def calculateLeapYearsRecursive(Integer year, def leaps) {

        if (year == yearEpoch) {
            return leaps
        }

        if ((year % 4 == 0) && !(year % 100 == 0) || (year % 400 == 0)) {
            leaps += 1
        }

        return calculateLeapYearsRecursive(year - 1, leaps)
    }

    def calculateLeapYearsFormula(Integer year) {
        return leapFormula(year) - leapFormula(yearEpoch)
    }

    private leapFormula(Integer year) {
        return (year / 4).toInteger() - (year / 100).toInteger() + (year / 400).toInteger()
    }


}
