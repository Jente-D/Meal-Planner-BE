# Meal Planning

## Documentation

We are using Markdown as a documenting language in all documents.
(More info)[https://www.markdownguide.org]

## Git

We are using GitHub for our repositories.
We use the `conventional commit` system to provide declarative commit and pull messages.
(More info)[https://www.conventionalcommits.org/en/v1.0.0/]

## Maven

If you have Maven installed, you can use the `mvn` command to test and build the application.   
If you don't have Maven installed, this project provides a Maven Wrapper that will pull all needed dependencies. You can use this, as if Maven was installed.

```shell
./mvnw clean test
```
```shell
./mvnw clean package
```

## Java

This project is using Java 17 - OpenJDK version for building and production.
Installing the correct versions can be done with JDKMan or Chocolatly.

## Docker
Don't forget to configure your `.env` file.

```
DATABASE_PSW=[your password]
DATABASE_USER=[your username]
DATABASE_NAME=meal-planner
DATABASE_URL=jdbc:postgresql://localhost:5432/meal-planner
```

### to spin up my container:
```shell
docker compose up -d 
```

The `-d` flag starts the docker containers without pushing all logs into the current Terminal window. It will run it in the background.

### to tear down my container:
```shell
docker compose down -v
```

The `-v` flag is used to remove the created volume, freeing up local resources.
