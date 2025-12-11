# British Spoken Time Converter

A Java console application that converts digital time (24-hour format) into British spoken time format.

## Features

- Converts time from HH:MM format to British spoken English
- Handles special cases like "noon", "midnight", "quarter past", "half past"
- Interactive console interface
- Input validation

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher

## Setup

1. **Clone or download the project**
   ```bash
   cd /path/to/british_spoken_time
   ```

2. **Compile the project**
   ```bash
   mvn compile
   ```

3. **Run tests (optional)**
   ```bash
   mvn test
   ```

## Running the Application

### Option 1: Using Maven
```bash
mvn exec:java -Dexec.mainClass="org.british.spoken.time.Main"
```

### Option 2: Using compiled JAR
```bash
# Build the JAR file
mvn package

# Run the JAR
java -jar target/british_spoken_time-1.0-SNAPSHOT.jar
```

### Option 3: Direct Java execution
```bash
# Compile first
mvn compile

# Run with classpath
java -cp target/classes org.british.spoken.time.Main
```

## How to Use

1. **Start the application** using one of the methods above
2. **Enter time** in HH:MM format (24-hour format)
3. **View the result** in British spoken format
4. **Continue entering times** or type `exit` to quit

### Example Session
```
British Spoken Time Converter
Enter time (or 'exit' to quit):
> 09:15
quarter past nine
> 14:30
half past two
> 16:45
quarter to five
> 00:00
midnight
> 12:00
noon
> exit
Goodbye!
```

## Time Format Examples

| Input  | Output |
|--------|--------|
| 00:00  | midnight |
| 12:00  | noon |
| 09:00  | nine o'clock |
| 09:15  | quarter past nine |
| 09:30  | half past nine |
| 09:45  | quarter to ten |
| 14:20  | twenty past two |
| 16:40  | twenty to five |

## How to Exit

Type `exit` (case-insensitive) and press Enter to quit the application.

## Error Handling

The application validates input and provides helpful error messages for:
- Invalid time format (not HH:MM)
- Invalid hours (not 0-23)
- Invalid minutes (not 0-59)
- Non-numeric input

## Project Structure

```
src/
├── main/java/org/british/spoken/time/
│   ├── Main.java                    # Console application entry point
│   └── BritishTimeConverter.java    # Time conversion logic
└── test/java/org/british/spoken/time/
    └── BritishTimeConverterTest.java # Unit tests
```