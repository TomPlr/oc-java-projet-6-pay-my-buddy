/* USER */;
INSERT INTO user (username, email, password, role, active)
VALUES ('kticksall0', 'bstenett0@gmail.com', '$2y$10$tCmRdeGQ0VMAUsp6LgaPY.yijeg/d9tAI0qGD.a9dECwUC7bMppHW',"USER", true),
       ('mlibbie1', 'ctruran1@usnews.com', '$2y$10$tCmRdeGQ0VMAUsp6LgaPY.yijeg/d9tAI0qGD.a9dECwUC7bMppHW',"USER", true),
       ('tohickey2', 'aviall2@va.gov', '$2y$10$tCmRdeGQ0VMAUsp6LgaPY.yijeg/d9tAI0qGD.a9dECwUC7bMppHW',"USER", true),
       ('dloadwick3', 'hbuttress3@macromedia.com', '$2y$10$tCmRdeGQ0VMAUsp6LgaPY.yijeg/d9tAI0qGD.a9dECwUC7bMppHW',"USER",
        true),
       ('wgarman4', 'gdanilishin4@mashable.com', '$2y$10$tCmRdeGQ0VMAUsp6LgaPY.yijeg/d9tAI0qGD.a9dECwUC7bMppHW', "USER",false),
       ('avedenyapin5', 'ggouldsmith5@ebay.co.uk', '$2y$10$tCmRdeGQ0VMAUsp6LgaPY.yijeg/d9tAI0qGD.a9dECwUC7bMppHW',"USER",
        true),
       ('ldewar6', 'rtuckwell6@gmail.com', '$2y$10$tCmRdeGQ0VMAUsp6LgaPY.yijeg/d9tAI0qGD.a9dECwUC7bMppHW',"USER", true),
       ('eellingford7', 'estainfield7@howstuffworks.com',
        '$2y$10$tCmRdeGQ0VMAUsp6LgaPY.yijeg/d9tAI0qGD.a9dECwUC7bMppHW',"USER", true),
       ('dedmans8', 'imalley8@gmail.com', '$2y$10$tCmRdeGQ0VMAUsp6LgaPY.yijeg/d9tAI0qGD.a9dECwUC7bMppHW', "USER",true),
       ('admin', 'admin@paymybuddy.com', '$2y$10$ulVcF8W.PYALaWMjDp0eLuU3FJsYbIL7PXMwH12Vj7aOgnJtqhmtm', "ADMIN",true),
       ('gbeardsley9', 'lcamden9@hugedomains.com', '$2y$10$tCmRdeGQ0VMAUsp6LgaPY.yijeg/d9tAI0qGD.a9dECwUC7bMppHW',"USER",
        true);


/* ACCOUNT */;
INSERT INTO account (balance, status, user_id)
VALUES (47.30, 'allowed', 1),
       (54.42, 'allowed', 2),
       (33.90, 'allowed', 3),
       (0.67, 'allowed', 4),
       (0, 'blocked', 5),
       (84.70, 'allowed', 6),
       (78.42, 'allowed', 7),
       (14.27, 'allowed', 8),
       (6.16, 'blocked', 9),
       (19.21, 'allowed', 10);

/* TRANSACTION */;
INSERT INTO transaction (date, description, amount, sender_account_id, receiver_account_id)
VALUES ('2023-11-19 16:30:28', 'consequat dui', 12.03, 1, 2),
       ('2023-10-03 09:55:58', null, 8.38, 2, 4),
       ('2023-09-10 23:42:51', 'nec', 18.23, 6, 1),
       ('2024-03-18 04:55:43', 'habitasse platea dictumst', 6.17, 2, 1),
       ('2023-12-17 11:12:00', 'mattis', 2.07, 10, 9);

/* USER_CONNECTION */;
INSERT INTO assoc_user_connection (user_id, connection_id)
VALUES (1, 2),
       (2, 1),
       (2, 4),
       (4, 2),
       (6, 1),
       (1, 6),
       (10, 9),
       (9, 10);