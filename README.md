# FileSort
This is a simple application for sorting files to appropriate folders according to internally hardcoded rules.

## Build app
1. Build app with gradle: \
`gradle clean build` 
2. Build app is located in following location: \
`optional invocation with path not supported yet. Application will work in current path folders` 
3. run app `java -jar` - *optional invocation with path as a parameter not supported yet. Application will work in current path folders.*

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

