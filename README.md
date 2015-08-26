# PetrovichControl
Ibeacon + Android App + Email, Оповещение заинтересованных что персона появилась возле маячка
+ FrontEnd
+ BackEnd
+ Андроид приложение

Ссылка на web страницу с которой пользователь будет отправлять запрос о его информировании: https://agent.electricimp.com/dymdKy1MiG5g

Андроид приложение должно передавать на https://agent.electricimp.com/dymdKy1MiG5g пост запрос с полями: &key,&idbeacon,&delay,&email 

В качестве тестового запроса, можно отсылать такой же пост запрос всего с двумя полями &email &test - в ответ система будет отсылать на этот email сообщение с содержимым поля test в теле письма.
