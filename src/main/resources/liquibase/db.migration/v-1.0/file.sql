CREATE TABLE `Country`(`country_id` BIGINT NOT NULL AUTO_INCREMENT,`name` varchar(45) NOT NULL,`population` BIGINT NOT NULL,PRIMARY KEY (`country_id`));

CREATE TABLE `Capital`(`country_id` BIGINT NOT NULL,`name` varchar(45) NOT NULL,PRIMARY KEY (`country_id`),
                       CONSTRAINT `Capital_relation_1` FOREIGN KEY (`country_id`) REFERENCES `Country` (`country_id`) ON DELETE
                           CASCADE ON UPDATE CASCADE);

INSERT INTO Country(country_id,name,population)
VALUES (1, 'Belarus',9500000),(2, 'Russia',144000000),(3, 'Ukraine',38000000),(4, 'Poland',38000000),
(5,'Latvia',25000000),( 6,'Estonia',3000000);

INSERT INTO Capital(country_id,name)
VALUES (1,'Minsk'),( 2,'Moskow'),(3, 'Kiev'),( 4,'Warsaw'),(5, 'Riga'),( 6,'Tallin');
