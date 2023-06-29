INSERT INTO Users(id, email, password, country, following_cnt, follower_cnt) VALUES(1, 'abc@naver.com', '12345', 'Seoul', 0, 0);
INSERT INTO Users(id, email, password, country, following_cnt, follower_cnt) VALUES(2, 'def@gmail.com', '12345', 'Pusan', 0, 0);

INSERT INTO Articles(id, title, content, writer, like_cnt, thumbnail, created_at)
VALUES(1, 'Test Article 1', 'Test Article Content 1', 1, 1, null, now());

INSERT INTO Articles(id, title, content, writer, like_cnt, thumbnail, created_at)
VALUES(2, 'Test Article 2', 'Test Article Content 2', 2, 2, null, now());

INSERT INTO Articles(id, title, content, writer, like_cnt, thumbnail, created_at)
VALUES(3, 'Test Article 3', 'Test Article Content 3', 1, 4, null, now());

INSERT INTO Articles(id, title, content, writer, like_cnt, thumbnail, created_at)
VALUES(4, 'Test Article 4', 'Test Article Content 4', 2, 4, null, now());

INSERT INTO Articles(id, title, content, writer, like_cnt, thumbnail, created_at)
VALUES(5, 'Test Article 5', 'Test Article Content 5', 1, 5, null, now());

INSERT INTO Articles(id, title, content, writer, like_cnt, thumbnail, created_at)
VALUES(6, 'Test Article 6', 'Test Article Content 6', 2, 6, null, now());
