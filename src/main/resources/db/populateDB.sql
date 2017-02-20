DELETE FROM user_roles;
DELETE FROM menus;
DELETE FROM votes;
DELETE FROM users;
DELETE FROM restaurants;

ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100;

-- user
INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni');

-- admin
INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', '$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100),
  ('ROLE_ADMIN', 101),
  ('ROLE_USER', 101);


INSERT INTO restaurants (name) VALUES
  ('Versal'),
  ('Yaponamama'),
  ('Елки-палки');

INSERT INTO  votes (vote, date, user_id, restaurant_id) VALUES
  (TRUE, '2017-01-15', 100, 104),
  (TRUE, '2017-01-15', 101, 102),
  (TRUE, '2017-01-16', 100, 103),
  (TRUE, '2017-01-16', 101, 102);

INSERT INTO  menus (date, dish, price, restaurant_id) VALUES
  ('2017-01-15', 'Vegetable salad', 100, 102),
  ('2017-01-15', 'Chicken soup', 110, 102),
  ('2017-01-15', 'Pumpkin cream soup', 150, 102),
  ('2017-01-16', 'Tea', 90, 102),
  ('2017-01-16', 'Juice', 80, 102),
  ('2017-01-15', 'Water', 85, 103),
  ('2017-01-15', 'Chicken ball', 105, 103),
  ('2017-01-15', 'Vegetarian satay', 110, 103),
  ('2017-01-16', 'Vegetarian soup', 125, 103),
  ('2017-01-16', 'Chocolate cake', 120, 103),
  ('2017-01-16', 'Tiramisu cake', 100, 104),
  ('2017-01-16', 'Coffee', 95, 104),
  ('2017-01-17', 'Ice cream', 50, 104),
  ('2017-01-17', 'Fruit bread', 70, 104),
  ('2017-01-17', 'Winter Salad', 80, 104);