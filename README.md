# British Spoken Time Converter

A professional Java application demonstrating modern development practices, SOLID principles, and comprehensive testing for converting digital time (24-hour format) into British spoken time format.

## Architecture & Design Principles

### SOLID Principles Implementation
- **Single Responsibility Principle**: Each class has one clear responsibility
- **Open/Closed Principle**: Extensible design with interfaces
- **Liskov Substitution Principle**: Proper inheritance hierarchies
- **Interface Segregation Principle**: Focused, minimal interfaces
- **Dependency Inversion Principle**: Depends on abstractions, not concretions

### Key Features
- **Domain-Driven Design**: Rich domain model with `Time` value object
- **Layered Architecture**: Clear separation of concerns (domain, service, UI)
- **Dependency Injection**: Spring Boot's automatic dependency injection
- **Comprehensive Testing**: Unit tests, integration tests, mocking
- **Code Quality**: Static analysis, test coverage, modern Java practices
- **Immutable Objects**: Thread-safe, predictable behavior
- **Defensive Programming**: Input validation and error handling

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher

## Quick Start

```bash
# Clone and navigate to project
cd /path/to/british_spoken_time

# Run with full quality checks
mvn clean verify

# Run the application
java -jar target/british-spoken-time-1.0-SNAPSHOT.jar
```

## Development Commands

### Build & Test
```bash
# Compile only
mvn compile

# Run tests with coverage
mvn test

# Full build with quality checks
mvn clean verify

# Generate test coverage report
mvn jacoco:report
```

### Running the Application

```bash
# Using Spring Boot Maven plugin
mvn spring-boot:run

# Using packaged JAR
mvn package
java -jar target/british-spoken-time-1.0-SNAPSHOT.jar
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

## Supported Time Formats

| Input  | Output | Rule Applied |
|--------|--------|-------------|
| 00:00  | midnight | Special case |
| 12:00  | noon | Special case |
| 09:00  | nine o'clock | On the hour |
| 09:15  | quarter past nine | Quarter past |
| 09:30  | half past nine | Half past |
| 09:45  | quarter to ten | Quarter to |
| 14:05  | five past two | Minutes past |
| 14:20  | twenty past two | Minutes past |
| 16:40  | twenty to five | Minutes to |
| 23:55  | five to twelve | Minutes to midnight |

## Usage Examples

### Interactive Session
```
British Spoken Time Converter
Enter time (or 'exit' to quit):
> 09:15
quarter past nine
> 14:30
half past two
> 16:45
quarter to five
> exit
Goodbye!
```

### Error Handling
Robust validation with clear error messages:
```
> 25:00
Error: Invalid time: Hour must be between 0 and 23, got: 25
> abc:def
Error: Invalid time: Invalid time format. Hours and minutes must be numeric
```

## Development Notes

### Extending the Application
The architecture supports easy extension:
- **New Time Formats**: Implement `TimeConverter` interface
- **Different UIs**: Create new UI implementations
- **Additional Validation**: Extend domain model
- **New Languages**: Add locale-specific converters

### Testing Strategy
- **Domain Tests**: Validate business logic and constraints
- **Service Tests**: Verify component interactions with mocks
- **Integration Tests**: End-to-end functionality validation
- **Edge Cases**: Comprehensive boundary testing

## Project Structure

```
src/
├── main/java/org/british/spoken/time/
│   ├── Application.java                    # Main entry point with DI wiring
│   ├── domain/
│   │   └── Time.java                       # Immutable time value object
│   ├── service/
│   │   ├── TimeConverter.java              # Conversion interface
│   │   ├── BritishTimeConverter.java       # British format implementation
│   │   └── TimeService.java                # Application service layer
│   ├── converter/
│   │   └── NumberToWordConverter.java      # Utility for number conversion
│   └── ui/
│       └── ConsoleInterface.java           # Console UI implementation
└── test/java/org/british/spoken/time/
    ├── domain/
    │   └── TimeTest.java                   # Domain model tests
    ├── service/
    │   ├── BritishTimeConverterTest.java   # Service implementation tests
    │   └── TimeServiceTest.java            # Service layer tests with mocks
    ├── converter/
    │   └── NumberToWordConverterTest.java  # Utility tests
    └── integration/
        └── ApplicationIntegrationTest.java # End-to-end integration tests
```

## Code Quality & Testing

### Test Coverage
- **Unit Tests**: Comprehensive coverage of all components
- **Integration Tests**: End-to-end workflow validation
- **Parameterized Tests**: Extensive edge case coverage
- **Mocking**: Proper isolation using Mockito
- **Target Coverage**: 80% minimum instruction coverage

### Static Analysis
- **SpotBugs**: Automated bug detection
- **Compiler Warnings**: Treated as errors (-Werror)
- **Modern Java**: Leverages Java 21 features

### Best Practices Demonstrated
- **Immutable Objects**: Thread-safe value objects
- **Fail-Fast Validation**: Early error detection
- **Defensive Copying**: Safe object handling
- **Clean Code**: Self-documenting, readable implementation
- **SOLID Principles**: Professional OOP design
- **Separation of Concerns**: Clear architectural boundaries