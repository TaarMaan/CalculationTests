# CalculationTests

CalculationTests (pet-project) - Spring-Angular приложение, отображающее различные этапы тестирования приложение (testcontainers,cucumber,karma,e2e)

## Необходимые технологии для запуска

- Intellij Idea
- Docker
- Postman
- Node.js (v16.13.0) не старше, иначе будет конфликт версии Angular (node_manager)

## Установка и запуск приложения bek (Testcontainers/Cucumber)

- Запускаем приожение
  - > В терминале переходим в директорию докер-компоуз файла cd bek/src/main/resources/docker-compose.yml
  - > Далее пишем команду docer-compose up
  - > Теперь подключаемся к нашей базульке поднятой через праметры в application.properties
  - > Запускаем BekApplication, если миграции не прошли, то запустить в ручную скрипты + перезапустить

- Проверим энд-поинты приложения в Postman (запросы операций схожи) + тесты для Postmana

![](https://github.com/TaarMaan/CalculationTests/blob/master/bek/src/main/resources/images/addition.PNG)

![](https://github.com/TaarMaan/CalculationTests/blob/master/bek/src/main/resources/images/addition%202.PNG)

- Запрос "Поиск по дате"

![](https://github.com/TaarMaan/CalculationTests/blob/master/bek/src/main/resources/images/3-2.PNG)

![](https://github.com/TaarMaan/CalculationTests/blob/master/bek/src/main/resources/images/3-1.PNG)

- Запрос "Поиск по параметрам"

![](https://github.com/TaarMaan/CalculationTests/blob/master/bek/src/main/resources/images/4-2.PNG)

![](https://github.com/TaarMaan/CalculationTests/blob/master/bek/src/main/resources/images/4-1.PNG)

- Запускаем модульные тесты
  - >  BekApplicationTests (Не забываем подправить базу данных для тестконтейнерсов если нужно в PostgreTestConainer

![](https://github.com/TaarMaan/CalculationTests/blob/master/bek/src/main/resources/images/1.PNG)

- Запускаем интеграционные тесты
  - > test.feature по пути bek/src/test/resources/features/test.feature

![](https://github.com/TaarMaan/CalculationTests/blob/master/bek/src/main/resources/images/2.PNG)

# Установк и запуск приложения frontend (Karma/e2e)

- Запускаем фронт тесты
  - > Переходим в директорию cd front/frontend
  - > Далее устанавливаем Angular client командой npm install -g @angular/cli@12.2.13
  - > Далее разрешаем node_manager вносить изменения в проекте, для зкачки node_modules командой Set-ExecutionPolicy -Scope CurrentUser -ExecutionPolicy RemoteSigned
  - > И обновляем его командой npx webdriver-manager update

- Запускаем приложение командой ng serve

![](https://github.com/TaarMaan/CalculationTests/blob/master/bek/src/main/resources/images/7.PNG)

- Запускаем Karma тесты командой ng test

![](https://github.com/TaarMaan/CalculationTests/blob/master/bek/src/main/resources/images/5.PNG)

- Запускаем e2e тесты командой ng e2e

![](https://github.com/TaarMaan/CalculationTests/blob/master/bek/src/main/resources/images/6.PNG)
