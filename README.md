# FileSort
This is a simple application for sorting files to appropriate folders according to internally hardcoded rules.

## Build app
gradle clean build
run app java -jar

## How it works?
Each time app is launch, it copies all files from ./home to ./dev or ./test
Criteria: 
+ ./dev - `*.xml` and `*.jar` with even hour
+ ./test - `*.jar` with even odd

## Scheduler
Currently, this app does not have implemented any internal scheduler. System scheduler as cron can be used.

