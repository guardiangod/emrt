# Electrical Meter Reading Tool (EMRT)

## Development Plan:
1. **Set Up the Project:**
- Create a Java Spring Boot project with Gradle.
2. **Parse the NEM12 Data:**
- Read the CSV-like data (NEM12 format) from the provided file.
- Extract necessary information from `200` and `300` records.
- Structure the code to handle large files efficiently.
3. **Generate SQL Insert Statements:**
- Generate `INSERT` statements for each reading.
- The generated SQL statements are written to a predefined output file.
4. **Testing:**
- Implement unit and integration tests to ensure the correctness of the parsing and SQL generation.

## Design Overview:
The solution is designed to be robust, modular, and efficient, handling potentially large files while ensuring the correct processing of hierarchical data.

## Project Structure
Here is the directory structure:
```css
emrt/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── flo/
│   │   │       └── ryan/
│   │   │           ├── model/
│   │   │           │   └── MeterReading.java
│   │   │           ├── service/
│   │   │           │   └── FileProcessingService.java
│   │   │           │   └── NEM12ParserService.java
│   │   │           │   └── SQLGeneratorService.java
│   │   │           └── Application.java
│   │   └── resources/
│   │       ├── input/
│   │       │   └── nem12.csv
│   │       └── output/
│   ├── test/
│   │   │── java/
│   │   │   └── flo/
│   │   │       └── ryan/
│   │   │           └── service/
│   │   │               ├── FileProcessingServiceTest.java
│   │   │               ├── NEM12ParserServiceTest.java
│   │   │               └── SQLGeneratorServiceTest.java
│   │   └── resources/
├── build.gradle
├── settings.gradle
```

## Instructions to Run the Application

### Environment Required
- JDK 17
- Gradle

### Environment Setup
1. **Ensure JDK 17 is installed** on your machine.
2. **Ensure Gradle is installed** on your machine.
3. **Set up your environment variables** to include the paths to JDK and Gradle.

### Running the Application
1. **Download the Source Code:**
   Download or copy the source code provided earlier into a directory on your computer.

2. **Navigate to the project directory:**
- Open a terminal (Linux/macOS) or command prompt (Windows).
- Use the cd command to navigate to the directory where you saved the source code. For example:
```sh
cd path-to-source-code/emrt
```

3. **Build the project**:
```sh
gradle build
```

4. **Run the application**:
```sh
gradle run
```
or you can run:
```sh
java -jar build/libs/emrt-1.0.0.jar
```

### Run Unit Tests
To run the unit tests, execute the following command in the terminal:
```sh
gradle test
```
This will compile the tests and run them, displaying the results in the terminal.

### Notes
The provided commands assumes a Unix-like terminal environment. The commands and process may vary slightly based on your operating system.


Copyright &copy; 2024, Ryan Le.