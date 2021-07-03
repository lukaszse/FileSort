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


## Requirements
+ java 11
+ gradle 7+

## Micronaut 2.5.7 Documentation

- [User Guide](https://docs.micronaut.io/2.5.7/guide/index.html)
- [API Reference](https://docs.micronaut.io/2.5.7/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/2.5.7/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

