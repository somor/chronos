[![Build Status](https://travis-ci.org/somor/chronos.svg?branch=master)](https://travis-ci.org/somor/chronos)

# Getting started with Chronos

Chronos is a command line written in Groovy that helps you to calculate the number of full days between experiment's
start and end dates.

## Install
There are a few different ways you can start using chronos, but the most recommended is the following:

* If you are on Linux or Mac you need to install Groovy
```
brew install groovy          # Homebrew
sudo apt-get install groovy  # Debian-based
sudo yum install groovy      # RHEL and friends
```

* Make sure everything looks good on your command line
```
groovy -version
Groovy Version: 2.4.6 JVM: 1.8.0_77 Vendor: Oracle Corporation OS: Mac OS X
```

* Copy the script under [dist] to your scripts folder or leave it there as you wish.
* Change the permissions for the script
```
chmod +x datecalculator.groovy
```
* It should be ready to use
```
./chronos.groovy -h
```

## Examples
*All examples assume you have already install Groovy*

See usage information

```
./chronos.groovy -h
usage: chronos.groovy[startDate] [endDate]
 -f,--format   DD/MM/YYYY
 -h,--help     Usage Information
```

Validate the format of the arguments

```
./chronos.groovy -f
Format is DD/MM/YYYY
```

Get the days in between two experiments

```
./chronos.groovy 04/07/1984  25/12/1984
Number of full days between 04/07/1984 and 25/12/1984: 173 days
```

# What is the problem with chronos?

### Problem Statement

* Calculate the number of full days between the experiment's start and end dates.

### Considerations

* The first and the last day are considered partial days and never counted.
* The solution needs to cater for all valid dates between 01/01/1901 - 31/12/2999

### Requirements

* Command line based system
* At least one way of providing input and output on the terminal

# What do I know about dates?

When you read the problem the first time, as a developer it looks a very straight forward problem.
Probably after one minute finishing the problem statement, you really start thinking about the problem and you just
realise that you can't remember much about calendars and the mathematics behind them. But no fear you must have,
there is always Wikipedia to the rescue.

## Let's start with System Time

[System Time - Wikipedia](https://en.wikipedia.org/wiki/System_time)

Considering that "system time" represents a notion of the passing time for a computer measured by a system clock;
which is implemented typically as a count of the number of ticks that have transpired since some arbitrary starting date,
we have an epoch. There are examples of epoch times like Unix epoch (1 January 1970 00:00:00 UT) or
Windows using Gregorian Calendars (1 January 1601 00:00:00 UT) that can be used for calendar times and express
time in more suitable forms for humans. Libraries and frameworks usually deals with adjustments for timezones,
daylight saving times (DST), leap seconds and user's locale settings. All this very valuable if you want to create a great library.


## About Gregorian Calendars

[Gregorian Calendar - Wikipedia](https://en.wikipedia.org/wiki/Gregorian_calendar)

Everything can be found on Wikipedia, but in general the Gregorian Calendar was a refinement to the Julian calendar
amounting to a 0.0002% correction of the year.

The proposal included reducing the number of leap years in four centuries from 100 to 97, by making 3 out of 4
centurial years common instead of leap years. The reform can be read as follows:

"Every year that is exactly divisible by four is a leap year, except for years that are exactly divisible by 100,
but these centurial years are leap years if they are exactly divisible by 400. For example, the years 1700, 1800,
and 1900 are not leap years, but the year 2000 is."

"In addition to the change in the mean length of the calendar year from 365.25 days (365 days 6 hours) to 365.2425 days
(365 days 5 hours 49 minutes 12 seconds), a reduction of 10 minutes 48 seconds per year, the Gregorian calendar
reform also dealt with the accumulated difference between these lengths."

Having a better understanding about calendars and differences between Julian and Gregorian calendar for adjustments,
the best way to summarise the rules in a Gregorian Calendar will be as follows:

```
Rule:
Every fourth year is a leap year.
Example:
2004, 2008, and 2012 are leap years.
```

```
Rule:
Considering the adjustment ,every hundredth year is not a leap year.
Example:
1900 and 2100 are not leap years.
```

```
Rule:
Every four hundred years, there's a leap year after all.
Example:
2000 and 2400 are leap years.
```

This piece of information was be very useful once I started implementing the system and consider at least leap years.


## About the implementation

I chose Groovy because I'm quite familiar with the language and it's quite expressive when scripting simple programs.

Chronos was developed iteratively and using TDD techniques to test most of the functions and rules that eventually
were moved into the final script.

Under [src] and [test] packages you can see some of the scenarios that I implemented to reach the final solution.

This is just the beginning of chronos and it can be always improved.
