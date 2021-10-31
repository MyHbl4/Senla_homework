--Задание: 1
--Найдите номер модели, скорость и размер жесткого диска для всех ПК стоимостью менее 500 дол. Вывести: model, speed и hd
SELECT model, speed, hd FROM pc WHERE price < 500;
--Задание: 2
--Найдите производителей принтеров. Вывести: maker
SELECT maker FROM product WHERE type = 'Printer';
--Задание: 3
--Найдите номер модели, объем памяти и размеры экранов ПК-блокнотов, цена которых превышает 1000 дол.
SELECT model, hd, screen FROM laptop WHERE price > 1000;
--Задание: 4
--Найдите все записи таблицы Printer для цветных принтеров.
SELECT * FROM printer WHERE color = 'y';
--Задание: 5
--Найдите номер модели, скорость и размер жесткого диска ПК, имеющих 12x или 24x CD и цену менее 600 дол.
SELECT model, speed, hd FROM pc WHERE (cd = '12x' OR cd = '24x') AND price < 600;
--Задание: 6
--Укажите производителя и скорость для тех ПК-блокнотов, которые имеют жесткий диск объемом не менее 100 Гбайт.
SELECT maker, speed FROM laptop INNER JOIN product USING (model) WHERE hd > 100;
--Задание: 7
--Найдите номера моделей и цены всех продуктов (любого типа), выпущенных производителем B (латинская буква).
SELECT model, price FROM pc INNER JOIN product USING (model) WHERE maker LIKE 'B%'
UNION
SELECT model, price FROM printer INNER JOIN product USING (model) WHERE maker LIKE 'B%'
UNION
SELECT model, price FROM laptop INNER JOIN product USING (model) WHERE maker LIKE 'B%';
--Задание: 8
--Найдите производителя, выпускающего ПК, но не ПК-блокноты.
SELECT maker FROM pc INNER JOIN product USING (model) INNER JOIN laptop USING (model) WHERE
--Задание: 9
--Найдите производителей ПК с процессором не менее 450 Мгц. Вывести: Maker
SELECT DISTINCT maker FROM product WHERE type = 'PC' AND NOT (type = 'Laptop');
--Задание: 10
--Найдите принтеры, имеющие самую высокую цену. Вывести: model, price
SELECT model, price FROM printer WHERE price = (SELECT MAX(price) FROM printer);
--Задание: 11
--Найдите среднюю скорость ПК.
SELECT ROUND(AVG(speed)) FROM pc;
--Задание: 12
--Найдите среднюю скорость ПК-блокнотов, цена которых превышает 1000 дол.
SELECT AVG(price) FROM laptop WHERE price > 1000;
--Задание: 13
--Найдите среднюю скорость ПК, выпущенных производителем A.
SELECT AVG(speed) FROM pc INNER JOIN product USING (model) WHERE maker LIKE 'A%';
--Задание: 14
--Для каждого значения скорости найдите среднюю стоимость ПК с такой же скоростью процессора. Вывести: скорость, средняя цена
SELECT speed, AVG(price) FROM pc GROUP BY speed;
--Задание: 15
--Найдите размеры жестких дисков, совпадающих у двух и более PC. Вывести: HD
SELECT hd, COUNT(*) FROM pc GROUP BY hd HAVING COUNT(*) > 1;
--Задание: 16
--Найдите пары моделей PC, имеющих одинаковые скорость и RAM. В результате каждая пара указывается только один раз, т.е. (i,j), но не (j,i), Порядок вывода: модель с большим номером, модель с меньшим номером, скорость и RAM.
SELECT DISTINCT first_model.model, second_model.model, first_model.speed, first_model.ram FROM pc first_model, pc second_model WHERE first_model.speed = second_model.speed AND first_model.ram = second_model.ram AND first_model.model > second_model.model;
--Задание: 17
--Найдите модели ПК-блокнотов, скорость которых меньше скорости любого из ПК.
--Вывести: type, model, speed
SELECT type, model, speed FROM laptop INNER JOIN product USING (model) WHERE speed < (SELECT MIN(speed) FROM pc);
--Задание: 18
--Найдите производителей самых дешевых цветных принтеров. Вывести: maker, price
SELECT maker, price FROM printer INNER JOIN product USING (model) WHERE color = 'y' AND price = (SELECT MIN(price) FROM printer WHERE color = 'y');
--Задание: 19
--Для каждого производителя найдите средний размер экрана выпускаемых им ПК-блокнотов. Вывести: maker, средний размер экрана.
SELECT maker, AVG(screen) FROM laptop INNER JOIN product USING (model) GROUP BY maker;
--Задание: 20
--Найдите производителей, выпускающих по меньшей мере три различных модели ПК. Вывести: Maker, число моделей
SELECT maker, COUNT(*) FROM pc INNER JOIN product USING (model)  GROUP BY maker HAVING COUNT(model) > 2;
--Задание: 21
--Найдите максимальную цену ПК, выпускаемых каждым производителем. Вывести: maker, максимальная цена.
SELECT maker, MAX(price) FROM pc INNER JOIN product USING (model) GROUP BY maker;
--Задание: 22
--Для каждого значения скорости ПК, превышающего 600 МГц, определите среднюю цену ПК с такой же скоростью. Вывести: speed, средняя цена.
SELECT speed, AVG(price) FROM pc WHERE speed > 600 GROUP BY speed;
--Задание: 23
--Найдите производителей, которые производили бы как ПК со скоростью не менее 750 МГц, так и ПК-блокноты со скоростью не менее 750 МГц. Вывести: Maker
SELECT DISTINCT maker FROM pc INNER JOIN product USING (model) WHERE speed >= 750 AND maker IN (SELECT DISTINCT maker FROM laptop INNER JOIN product USING (model) WHERE speed >= 750);
--Задание: 24
--Перечислите номера моделей любых типов, имеющих самую высокую цену по всей имеющейся в базе данных продукции.
SELECT model FROM pc WHERE price = (SELECT MAX(price) FROM pc)
UNION
SELECT model FROM laptop WHERE price = (SELECT MAX(price) FROM laptop)
UNION
SELECT model FROM printer WHERE price = (SELECT MAX(price) FROM printer);
--Задание: 25
--Найдите производителей принтеров, которые производят ПК с наименьшим объемом RAM и с самым быстрым процессором среди всех ПК, имеющих наименьший объем RAM. Вывести: Maker
SELECT DISTINCT maker FROM product WHERE type = 'Printer' AND maker IN(SELECT maker FROM product WHERE model IN(SELECT model FROM pc WHERE speed = (SELECT MAX(speed) FROM pc WHERE ram = (SELECT MIN(ram) FROM pc))));