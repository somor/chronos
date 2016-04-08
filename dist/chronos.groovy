#!/usr/bin/env groovy

/**
 *
 * Problem Statement
 *
 * - Calculate the number of full days between the experiment's start and end dates.
 *
 * Considerations
 *
 * - The first and the last day are considered partial days and never counted.
 * - The solution needs to cater for all valid dates between 01/01/1901 - 31/12/2999
 *
 *
 * Requirements
 *
 * - Command line based system
 * - At least one way of providing input and output on the terminal
 *
 * @param args -[hf] [startDate] [endDate]
 * @return Number of full days between two dates without considering first and last day
 */

def datecalculator(args) {

    String YEAR_FORMAT = 'DD/MM/YYYY'

    def cli = new CliBuilder(usage: 'chronos.groovy[startDate] [endDate]')
    cli.with {
        h(longOpt: 'help', 'Usage Information')
        f(longOpt: 'format', YEAR_FORMAT)
    }

    def options = cli.parse(args)
    if (!options) {
        return
    }

    // Show help info
    if (options.h) {
        cli.usage()
        return
    }

    // Show format data info for this version
    if (options.f) {
        return "Format is ${YEAR_FORMAT}"
    }

    def extraArguments = options.arguments()
    if (extraArguments) {

        if (extraArguments.size() > 2) {
            return "Number of arguments exceeded."
        }

        String date1 = extraArguments[0]
        String date2 = extraArguments[1]

        if (!((date1 =~ /^[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}/) && (date2 =~ /^[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}/))) {
            return "Date must be in the following format: ${YEAR_FORMAT}"
        }

        Integer date1Day = date1.split('/')[0].toInteger()
        Integer date1Month = date1.split('/')[1].toInteger()
        Integer date1Year = date1.split('/')[2].toInteger()

        Integer date2Day = date2.split('/')[0].toInteger()
        Integer date2Month = date2.split('/')[1].toInteger()
        Integer date2Year = date2.split('/')[2].toInteger()

        def startDate = calculateDaysEpoch(date1Day,  date1Month, date1Year)
        def endDate = calculateDaysEpoch(date2Day, date2Month, date2Year)

        if (Integer.max(startDate,endDate) == startDate) {
            def tmpDate = startDate
            startDate = endDate
            endDate = tmpDate
        }

        def daysInBetween = (endDate -1) - startDate

        return "Number of full days between $date1 and $date2: $daysInBetween days"

    }

    return cli.usage()

}

private def calculateDaysEpoch(Integer day, Integer month, Integer year) {
    def monthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    def days = year * 365 + day

    (0..(month - 1)).each {
        days += monthDays.get(it)
    }

    days += calculateLeapYears(year)
    if (isLeapYear(year) && month <= 2) {
        days -= 1
    }
    return days
}

private def calculateLeapYears(Integer year) {
    Integer yearEpoch = 1901
    return findLeapYearsFor(year) - findLeapYearsFor(yearEpoch)
}

private def isLeapYear(Integer year) {
    return ((year % 4) == 0) && (!((year % 100) == 0) || ((year % 400) == 0))
}

private findLeapYearsFor(Integer year) {
    return (year / 4).toInteger() - (year / 100).toInteger() + (year / 400).toInteger()
}


println datecalculator(args)

