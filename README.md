# Keyboard Driven Framework Assignment

## How to run:
To run this progam just open testng.xml and make it run as a TestNG Suite <br>
The object repository is located in the src/test/resources/objectRepositories/objects.properties file <br>
The excel file is located in the src/test/resources/testData.xlsx file <br>
The excel file is divided in a controller file called TestSuite, and all the test cases located on different worksheets <br>
In the TestSuite worksheet you can ignore a test case typing an N on the "Run" column or run it typing a "Y", and make a test case dependant from other test case writing the name of the test case to which is dependent, this makes it so if the "parent" test case fails the "child" test case will be skipped <br>
