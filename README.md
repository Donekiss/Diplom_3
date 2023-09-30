# Diplom_3

Проект для покрытия **UI-тестами** заданной части функциональности сайта:

https://stellarburgers.nomoreparties.site/

## Зависимости

- Java
- Maven
- JUnit
- RestAssured
- JavaFaker
- Allure

## Запуск проекта

Для запуска тестов выполните следующие команды:

- Для браузера Yandex:
```
mvn clean test -Dbrowser=yandex
```
- Для браузера Chrome:
```
mvn clean test -Dbrowser=chrome
```
Для просмотра отчета тестирования выполните следующую команду:
```
mvn allure::serve
```
### Приятного просмотра
<img height="100" src="https://papik.pro/uploads/posts/2022-08/1661868729_41-papik-pro-p-palets-vverkh-smailik-png-43.jpg" title="Приятного просмотра" width="200"/>