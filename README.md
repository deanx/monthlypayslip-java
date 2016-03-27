# montlypayslip-java

## Running only unit tests
mvn clean test

## Running project
mvn clean package && java -jar target/MontlypaySlip.jar <CSV_INPUT_FILE>

## Technologies
- Java 8
- Maven
- TestNG (unit test)
- Mockito (mocks)

## Assumptions
- CSV (input and output) has no header
- Super rate is positive integer
- Tax on the exercise description was used
 