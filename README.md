# java-filmorate
Template repository for Filmorate project.
![Диаграмма БД](/dbdiagram.png)

Ссылка на dbdiagram.io https://dbdiagram.io/d/62bda47069be0b672c74b9a3

Примеры запросов:

Все фильмы
SELECT *
FROM FILMS

Топ 5 популярных фильмов
SELECT *
FROM FILMS
ORDER BY LIKES DESC
LIMIT 5

Все юзеры
SELECT *
FROM USERS

Друзья юзера 1
SELECT *
FROM FRIENDSHIPS
WHERE USER_ID = 1

Общий друзья юзеров 1 и 2
SELECT *
FROM FRIENDSHIPS
WHERE USER_ID = 1
  AND FRIEND_ID IN (SELECT FRIEND_ID FROM FRIENDSHIPS WHERE USER_ID = 2)

Лайки фильму с айди 1
SELECT *
FROM LIKES
WHERE film_id = 1
