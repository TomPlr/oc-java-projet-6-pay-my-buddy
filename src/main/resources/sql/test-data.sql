/* USER */;
INSERT INTO user (username, email, password, active)
VALUES ('kticksall0', 'bstenett0@gmail.com', '$2a$04$4CRiFB0eqpgSbvkq/WNl3uEC21FnWK2h5wUnc3W45x9lWH3uLRm8y', true),
       ('mlibbie1', 'ctruran1@usnews.com', '$2a$04$3K36SyLnd3UsiGUF7da65e2161lxyBaSwCZBe4xgSv.hfM5GLuehG', true),
       ('tohickey2', 'aviall2@va.gov', '$2a$04$7t1vYu4Plj0IGPbd3fKFYO7ZKACXnzvoT1Vj9xzSBElcrQ/3YfEN.', true),
       ('dloadwick3', 'hbuttress3@macromedia.com', '$2a$04$vKb6Y1oujsBcI/./TCDhlOgXOnXtWVgE8sFVNuZVVyjqnIgyWZACW',
        true),
       ('wgarman4', 'gdanilishin4@mashable.com', '$2a$04$cIgTqpIM9mWldypwCpGCuufc945fi31G5SESJ7dTUEZBu4XcxL97q', false),
       ('avedenyapin5', 'ggouldsmith5@ebay.co.uk', '$2a$04$4q/8rjm9wgOoXLgFI4mXcuNY35pWTW.m1Xc5m.ffhzA214HD9m5fm',
        true),
       ('ldewar6', 'rtuckwell6@gmail.com', '$2a$04$ZnZ/TvFJ2JoOIJioXbiAc.HspHFjHXN.iFZ.WEirntqG2S.EmwTd6', true),
       ('eellingford7', 'estainfield7@howstuffworks.com',
        '$2a$04$F9drN./VmSZZkvxHyfS4ZuWPzEiT7E4u.Xe.1kCoLYy3gy.RAY1lq', true),
       ('dedmans8', 'imalley8@gmail.com', '$2a$04$4vnboxE3HTMnNN2olIugyetJMN2broh6H2DQBylBZKIcR6326wmfu', true),
       ('gbeardsley9', 'lcamden9@hugedomains.com', '$2a$04$Eve30aXFx6CkFk6Fn1wNlO7slizqG5MTdYsp1q1I3zkUvqDm/aJ4q',
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