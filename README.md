You need to have installed:
1. Java version 1.7 or above
2. Maven version 3.1.1 or above

The project include the test and functionality for generating Allure report (report contains test steps, screenshots and etc.).

You can run the test in Windows and Linux OS.
You can run the test in three browsers: Chrome, Firefox 42 and IE11.

To run the test you need to open command line in directory with pom.xml and run one of these commands:
"mvn claen test" (run test in chrome).
"mvn clean test -P firefox" (run test in Firefox)
"mvn clean test -P ie" (run test in IE)

To generate Allure report after test running you need to run command "mvn site".
To view the report you need to open file \target\site\allure-maven-plugin\index.html in Firefox.










