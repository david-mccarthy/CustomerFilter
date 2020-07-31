
# CustomerFilter
Filter a list of customers by distance to a constant office location.

**Usage**
*Build project:*
This is a gradle project, which will handle the build process.

Compile/build the application, run the follow from the root of the project. This uses the gradle wrapper to execute the build.

    gradlew build

By default, this will output the jar to the build directory.

*Run the application:*

    java -jar build/libs/CustomerFilter.jar <path to your customer file>
This will output the result to stdout.

Alternatively output data to a file with:

    java -jar build/libs/CustomerFilter.jar <path to your customer file> <output file>
This will output the result to stdout, and also write the results to your provided file.

*Testing:*
Tests run automatically when you run the build task. Alternatively run them with gradle using:

    gradlew test
