# Spring Web Application

This is a simple Spring Framework web application.

## Project Structure

```
spring-web-app
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── springwebapp
│   │   │               ├── SpringWebAppApplication.java
│   │   │               ├── controller
│   │   │               │   └── HomeController.java
│   │   │               └── service
│   │   │                   └── ExampleService.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── templates
│   │           └── index.html
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── springwebapp
│                       └── SpringWebAppApplicationTests.java
├── build.gradle
└── settings.gradle
```

## Setup Instructions

1. Clone the repository:
   ```
   git clone <repository-url>
   ```

2. Navigate to the project directory:
   ```
   cd spring-web-app
   ```

3. Build the project using Gradle:
   ```
   ./gradlew build
   ```

4. Run the application:
   ```
   ./gradlew bootRun
   ```

## Usage

Once the application is running, you can access it at `http://localhost:8080`. The home page will be displayed.

## Dependencies

This project uses the following dependencies:
- Spring Boot
- Spring Web
- Thymeleaf

## Testing

To run the tests, use the following command:
```
./gradlew test
```

## License

This project is licensed under the MIT License.