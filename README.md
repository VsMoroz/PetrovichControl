# PetrovichControl
Ibeacon + Android App + Email, Оповещение заинтересованных что персона появилась возле маячка
+ FrontEnd
+ BackEnd
+ Андроид приложение

http://locator.cf

## 1й этап системы:
Пользователь может зарегистрироваться и с мобильного приложения и  через web (валидируется получая ссылку в почту)

После регистрации пользователь или сканирует из эфира или вводит вручную UUID Ibeacon`ы и дает им имена (Рабочее место, Двери работа, Двери школа,Вход 
магазина, Вход Больница, Прилавок с золотом). Так же вручную пользователь может ввести UUID и название где он установлен через WEB.

После регистрации  пользователь может или скопировать или нажав кнопку  получить в буфер обмена два типа ссылки:
  - ссылка на web интерфейс где посторонний пользователь сможет выбрать !любой! ваш зарегистрированный Ibeacon (Рабочее место, Двери работа, Двери школа,Вход магазина) ввести свой email и получить на него письмо (разово) когда вы приблизитесь к этому Ibeacon максимально близко.
  - ссылка на web интерфейс где посторонний пользователь  увидит !только один! ваш зарегистрированный Ibeacon (Рабочее место, Двери работа, Двери школа,Вход магазина) ввести свой email и получить на него письмо (разово) когда вы приблизитесь к этому Ibeacon максимально близко.

Соответственно мобильное приложение должно уметь регистрировать пользователя на бекенде, уметь синхронизировать с бекендом UUID и Название рабочего места. В случае конфликта применяется версия того места где была последняя корректировка.
В случае обнаружения зарегистрированного бекона слать его UUID на сервер при наличии связи 

 * Бекенд сохраняет посылки от мобильного добавляя timestamp, показывает это на отдельной странице ( в личном кабинете этого пользователя) перемещений в web форме.

--- Прототип бекенда: --------------

Ссылка на web страницу с которой пользователь будет отправлять запрос о его информировании: https://agent.electricimp.com/dymdKy1MiG5g

Андроид приложение должно передавать на https://agent.electricimp.com/dymdKy1MiG5g пост запрос с полями: &key,&idbeacon,&delay,&email,&nplace

В качестве тестового запроса, можно отсылать такой же пост запрос всего с двумя полями &email &test - в ответ система будет отсылать на этот email сообщение с содержимым поля test в теме письма.

## 2й этап:
Добавить в систему возможность определять направление движения (зашел вышел) методом использования двух IBeacon расположенных на внутренней и внешней стороне двери, соответственно в мобильном приложении определять направление движения методом учета динамики изменений расстояния до этих двух беаконов.
В логах перемещения пользователей добавить так же направление движение
В БД хранить такую пару как один объект

Добавить в личном кабинете пользователя возможность выполнять  правило/действие (по началу просто отсылку на почтовый ящик если пользователь появился возле обьекта с учетом направления (вход/выход)

Добавить в базу данных маяков возможность ставить признак публичности Ibeacon ы. Приватные видит только этот пользователь, публичные все. Речь идет только о каталоге IBeaconов.

Тоесть в 1й версии у нас пользователь новые Ibeacon в свой каталог будет вводить сам руками или сканировать, а во второй он сможет их так же добавить из общего каталога. А так же сможет сам добавить новый маяк в общий каталог если поставит ему галочку Public


