<h2 align="center"> Проект по автоматизации тестирования для компании "Магнит Доставка" </h2>
<a href="https://rabota.sber.ru"><img src="./images/icons/logo.svg"></a>

# <a name="Содержание">Содержание</a>
+ [Описание](#Описание)
+ [Технологии и инструменты](#Технологии-и-инструменты)
+ [Варианты запуска](#Варианты-запуска)
    + [Gradle command](#GradleCommand)
    + [Запуск в Jenkins](#RunInJenkins)
+ [Telegram уведомления](#TelegramNotifications)
+ [Результаты тестов в Allure Report](#AllureReport)
+ [Интеграция с Allure TestOps](#AllureTestOps)
+ [Интеграция с Jira](#Jira)
+ [Видео запуска тестов](#Video)


# <a name="Описание">Описание</a>
Тестовый проект состоит из веб-тестов (UI), тестов API и мобильных тестов (Android).\
Краткий список интересных фактов о проекте:
- [x] `Page Object` проектирование
- [x] Параметризованные тесты
- [x] Различные файлы конфигурации для запуска теста в зависимости от параметров сборки
- [x] Конфигурация с библиотекой `Owner`
- [x] Использование `Lombok` для моделей в API тестах
- [x] Objects serialization/deserialization for API requests/responses using `Jackson`
- [x] Использование request/response спецификаций для API тестов
- [x] Custom Allure listener для API requests/responses логов
- [x] Интеграция с `Allure TestOps`
- [x] Autotests as test documentation
- [x] Интеграция с `Jira`


# <a name="Технологии и инструменты">Технологии и инструменты</a>
<p  align="center">
  <code><img width="5%" title="IntelliJ IDEA" src="./images/icons/IDEA-logo.svg"></code>
  <code><img width="5%" title="Java" src="./images/icons/java-logo.svg"></code>
  <code><img width="5%" title="Selenide" src="./images/icons/selenide-logo.svg"></code>
  <code><img width="5%" title="REST-Assured" src="./images/icons/rest-assured-logo.svg"></code>
  <code><img width="5%" title="Selenoid" src="./images/icons/selenoid-logo.svg"></code>
  <code><img width="5%" title="Gradle" src="./images/icons/gradle-logo.svg"></code>
  <code><img width="5%" title="JUnit5" src="./images/icons/junit5-logo.svg"></code>
  <code><img width="5%" title="Allure Report" src="./images/icons/allure-Report-logo.svg"></code>
  <code><img width="5%" title="Allure TestOps" src="./images/icons/allure-ee-logo.svg"></code>
  <code><img width="5%" title="Github" src="./images/icons/git-logo.svg"></code>
  <code><img width="5%" title="Jenkins" src="./images/icons/jenkins-logo.svg"></code>
  <code><img width="5%" title="Jira" src="./images/icons/jira-logo.svg"></code>
  <code><img width="5%" title="Telegram" src="./images/icons/Telegram.svg"></code>
  <code><img width="5%" title="Browserstack" src="./images/icons/browserstack.svg"></code>
  <code><img width="5%" title="Android Studio" src="https://upload.wikimedia.org/wikipedia/commons/9/95/Android_Studio_Icon_3.6.svg"></code>
  <code><img width="5%" title="Appium" src="./images/icons/appium.svg"></code>
</p>

Автотесты в этом проекте написаны на `Java` использую `Selenide` фреймворк.\
`Gradle` - используется как инструмент автоматизации сборки.  \
`JUnit5` - для выполнения тестов.\
`REST Assured` - для тестирования REST-API сервисов.\
`Jenkins` - CI/CD для запуска тестов удаленно.\
`Selenoid` - для удаленного запуска браузера в `Docker` контейнерах.\
`Browserstack` - для запуска мобильных тестов удаленно.\
`Android Studio tools`, `Appium` - для запуска мобильных тестов локально на эмуляторе мобильных устройств.\
`Allure Report` - для визуализации результатов тестирования.\
`Telegram Bot` - для уведомлений о результатах тестирования.\
`Allure TestOps` - как система управления тестированием.

[Вернуться к оглавлению ⬆](#Содержание)

# <a name="Варианты запуска">Варианты запуска</a>

## <a name="GradleCommand">Команды для Gradle</a>
Для запуска локально и в Jenkins используется следующая команда::
```bash
gradle clean test -Dtag=<tag> -DrunIn=<runIn>
```
Дополнительные параметры:
> `-Dselenoid_user_sys_prop=enter_user` `-Dselenoid_key_sys_prop=enter_key` - данные для selenoid\
> `-Dbrowserstack_user_sys_prop=enter_user` `-Dbrowserstack_key_sys_prop=enter_key` - данные для browserstack\
> `-DapiBaseUrl=url` можно добавить для установки базового URL-адреса для тестов API.

`tag` - теги для запуска выполнения тестов:
>- *API*
>- *Web*
>- *Android*
 
`runIn` - определяет среду для запуска этих тестов:
>- *api* - for api tests
>- *browser_selenoid*
>- *browser_local*
>- *android_browserstack*
>- *android_emulator*

Дополнительные свойства извлекаются из соответствующего файла конфигурации (в зависимости от значения `runIn`):
```bash
./resources/config/${runIn}.properties
```

Допустимые комбинации:
```mermaid
graph LR
A[tag] --> B[API]
A --> C[Web]
A --> D[Android]
B --> K[api]
C --> E[browser_selenoid]
C --> F[browser_local]
D --> G[android_browserstack]
D --> H[android_emulator]
```

[Вернуться к оглавлению ⬆](#Содержание)

## <a name="RunInJenkins">Запуск в [Jenkins](https://jenkins.autotests.cloud/job/dostavka.magnit.ru/)</a>
Главная страница сборки:
<p  align="center">
<img src="images/screens/JenkinsMain.png" width="950">
</p>

Параметризованное задание Jenkins может быть запущено с необходимыми ***tag*** and ***runIn***:
<p  align="center">
<img src="images/screens/JenkinsMain.png" alt="JenkinsBuildParameters" width="950">
</p>

Конфиденциальная информация (имена для входа и пароли) хранится в зашифрованном виде в хранилище учетных данных Jenkins.\
И относительно безопасно передается в сборку аргументами gradle, а его значения маскируются в логах.

После завершения сборки результаты тестирования доступны в:
>- <code><strong>*Allure Report*</strong></code>
>- <code><strong>*Allure TestOps*</strong></code> - результаты загружаются туда и тест-кейсы могут автоматически обновляться в соответствии с последними изменениями в коде.

<p  align="center">
<img src="images/screens/JenkinsFinishedBuild.png" alt="JenkinsFinishedBuild" width="950">
</p>

[Вернуться к оглавлению ⬆](#Содержание)
