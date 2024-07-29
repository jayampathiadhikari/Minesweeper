1. Build the Project
   Prerequisites
   Ensure you have Maven installed on your system. You can check by running:

mvn -v
If Maven is not installed, you can install it using your package manager, for example:

sudo apt-get install maven
Ensure you have Java installed on your system. You can check by running:

java -version
If Java is not installed, you can install it using your package manager, for example:

sudo apt-get install openjdk-8-jdk
Building the Project
Navigate to the project directory where your pom.xml file is located.

Run the following Maven command to compile the project and package it into a JAR file:
============
mvn clean package
This command will:

Clean the target directory (mvn clean)
Compile the source code
Package the compiled code into a JAR file (mvn package)
If the build is successful, you should see a target directory with a JAR file named MineSweeperApp-1.0-SNAPSHOT.jar.



2. Run the Project
   Running the Project
   After building the project, navigate to the target directory:

cd target
Run the JAR file using the java -jar command:

java -jar MineSweeper-1.0-SNAPSHOT.jar