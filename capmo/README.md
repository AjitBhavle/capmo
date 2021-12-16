# CapmoCaseStudy
This is case study repository

# Pre-requisites:
  - JDK 8+ should be installed
  - Maven path should be set in system path(MAVEN)
  - System veriable path should be set "JAVA_HOME"
  - All maven dependency should be installed from pom.xml file
  
# Parellel execution:
  - Parellel test case execution support is there in framework.
  - To execute test cases parellelly add "parellel" attribute in testNG.xml file.(located at project root folder).
  - You can execute parellel test cases by methods, tests, classes by changing above attribute values in testNG.xml file

# Multiple Browser support:

  - This framework supports for multiple browser as you need to change browser parameter in "data.prperties" file(/capmo/src/test/java/com/capmo/swaglab/resources)

# Execution steps:

  ## Command line Execution:
     - If you are executing test cases from command line then make below changes in "Base.java" file changes.
        - Uncomment this line in 'openBrowser()' method if you are sending parameter from Maven (String browserName=System.getProperty("browser");)
        - comment this line if you are sending parameter from Maven (String browserName = prop.getProperty("browser");)
     
     - Enter below command on command line and execute your TC.
       - mvn test -Dbrowser=chrome
  

  ## From eclipse execution: 
     -  Right click on "testNG.xml" file and run as TestNG then you will see execution on console.
     -  Html report will get generated in "/capmo/htmlReportsAndScreenshots/reports/" folder.
     -  Screenshots will get generated in "/capmo/htmlReportsAndScreenshots/screenshots/" folder.
     
# Folder structure:
  ## Base package: 
     - You will find all report generation and testNG annotation methods under below folder
     '/capmo/src/test/java/com/capmo/swaglab/base'
     
  ## Helper package: 
     - You will find all common methods used in framework under below folder path.
     '/capmo/src/test/java/com/capmo/swaglab/helper'
     
  ## POM package: 
     - You will find all pages locators under below folder path.
     '/capmo/src/test/java/com/capmo/swaglab/pom'  
     
  ## Resources package: 
     - You will find all data related files(data.peroperties) under below folder path.
     '/capmo/src/test/java/com/capmo/swaglab/resources'
     
  ## Main package: 
     - You will find all test cases written under below folder path.
     '/capmo/src/test/java/com/capmo/swaglab/main'
     
  ## Reports ans screenshots: 
     - You will find all test cases execution html report and failure screenshots under below folder
     '/capmo/htmlReportsAndScreenshots'
     
  ## Driver .exe's:
    - You will find driver exe files under below path
     '/capmo/driver' 
     
  
