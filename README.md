Приложение "Лучшая Цена".

Приложение позволяет искать товар с наиболее выгодными цена в магазинах города.

Весь проект состоит из мобильного приложения, временное название "Сканер Чеков" и серверной части на базе Java 8, Framework Spring Cloud(Eureka, Config, Web, Actuator и т.д.) и поисковой базы данных ElasticSearch.

Принцип работы.

С помощью мобильного приложения сканируется QR-Code чека, данные кода отправляются на сервер, где происходит запрос к API "Проверка чека" налоговой службы. Результатом ответа, является информация чека, наименование товара и цена товара, так же, мета информация, ИНН магазина, дата покупки и т.д.

Данные о товаре сохраняются в базе данных. 

С помощью запроса к ElasticSearch, получаем товар с наименьшей ценой и где его можно купить(адрес магазина).

TODO

Развитие проекта.

Планируется веб версия приложения.
Расширение функционала мобильного приложения.
Оптимизация и отказоустойчивость серверной части.

P.S. Пока доступ к налоговой службе не оформлен. Приходиться использовать, что предлагают другие, например сторонний сайт (временное решение) или проверка по ОФД.
