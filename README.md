# Meal Planning

## Docker
Don't forget to configurer your `.env` file.

```
DATABASE_PSW=[your password]
DATABASE_USER=[your username]
DATABASE_NAME=meal-planner
DATABASE_URL=jdbc:postgresql://localhost:5432/meal-planner
```

to spin up my container:
```shell
docker compose up -d 
```

to tear down my container:
```shell
docker compose down -v
```