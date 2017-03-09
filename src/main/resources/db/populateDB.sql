DELETE FROM user_roles;
DELETE FROM menus;
DELETE FROM votes;
DELETE FROM users;
DELETE FROM restaurants;

ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100;

-- user
INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');--100

-- admin
INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');--101

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100),
  ('ROLE_ADMIN', 101),
  ('ROLE_USER', 101);


INSERT INTO restaurants (name) VALUES
  ('Versal'),--102
  ('Yaponamama'),--103
  ('Елки-палки');--104

INSERT INTO  votes (vote, date, user_id, restaurant_id) VALUES
  (TRUE, '2017-01-15', 100, 104),--105
  (TRUE, '2017-01-15', 101, 102),--106
  (TRUE, '2017-01-16', 100, 103),--107
  (TRUE, '2017-01-16', 101, 102);--108

INSERT INTO  menus (date, dish, price, restaurant_id) VALUES
  ('2017-01-15', 'Vegetable salad', 100, 102),--109
  ('2017-01-15', 'Chicken soup', 110, 102),--110
  ('2017-01-15', 'Pumpkin cream soup', 150, 102),--111
  ('2017-01-16', 'Tea', 90, 102),--112
  ('2017-01-16', 'Juice', 80, 102),--113
  ('2017-01-15', 'Water', 85, 103),--114
  ('2017-01-15', 'Chicken ball', 105, 103),--115
  ('2017-01-15', 'Vegetarian satay', 110, 103),--116
  ('2017-01-16', 'Vegetarian soup', 125, 103),--117
  ('2017-01-16', 'Chocolate cake', 120, 103),--118
  ('2017-01-16', 'Tiramisu cake', 100, 104),--119
  ('2017-01-16', 'Coffee', 95, 104),--120
  ('2017-01-17', 'Ice cream', 50, 104),--121
  ('2017-01-17', 'Fruit bread', 70, 104),--122
  ('2017-01-17', 'Winter Salad', 80, 104);--123