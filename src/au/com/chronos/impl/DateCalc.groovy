package au.com.chronos.impl

class DateCalc {


    LeapYear leapYear = new LeapYear()

    def calculateDaysInBetween(String startDate, String endDate) {

        Integer startDateDay = startDate.split('/')[0].toInteger()
        Integer startDateMonth = startDate.split('/')[1].toInteger()
        Integer startDateYear = startDate.split('/')[2].toInteger()

        Integer endDateDay = endDate.split('/')[0].toInteger()
        Integer endDateMonth = endDate.split('/')[1].toInteger()
        Integer endDateYear = endDate.split('/')[2].toInteger()

        return calculateDaysEpoch(endDateDay - 1, endDateMonth, endDateYear) -
                calculateDaysEpoch(startDateDay, startDateMonth, startDateYear)

    }


    def calculateDaysEpoch(Integer day, Integer month, Integer year) {
        def monthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
        def days = year * 365 + day

        (0..(month - 1)).each {
            days += monthDays.get(it)
        }

        days += leapYear.calculateLeapYearsRecursive(year, 0)
        if (leapYear.isLeapYear(year) && month <= 2) {
            days -= 1
        }
        return days
    }


}
