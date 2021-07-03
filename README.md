# FileSort
This is a simple application for sorting files to appropriate folders according to internally hardcoded rules.

## Build app
1. Build app with gradle: \
`gradle clean build` 
2. The built app is located in the following location: \
`build/libs/FilesSort-0.1.jar` 
3. run app `java -jar FilesSort-0.1.jar`
app can e invoked optionally with paameter `path`, example: \
   `java -jar FilesSort-0.1.jar -p {your chosen path}`

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

