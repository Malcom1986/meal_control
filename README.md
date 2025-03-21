[![Java CI](https://github.com/Malcom1986/meal_control/actions/workflows/main.yml/badge.svg)](https://github.com/Malcom1986/meal_control/actions/workflows/main.yml)

# Трекер калорий

Данный проект представляет собой REST API сервис для отслеживания дневной нормы калорий пользователя и учета съеденных блюд. Сервис позволяет пользователям добавлять свои данные, регистрировать блюда и отслеживать приемы пищи, а также получать отчеты о своем питании

## Системные требования

- Docker
- Docker Compose

## Локальный запуск

```bash
make start
```

Откройте интерактивную документацию по адресу http://localhost:8080/swagger-ui/index.html

Файл *Nutrition Control.postman_collection.json* содержит Postman коллекцию для работы с приложением

## Запуск тестов

```bash
make test
```
