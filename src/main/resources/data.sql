INSERT INTO users(email, password, created_at) VALUES ('email@email.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', '2019-01-21T05:47:20.949');
INSERT INTO users(email, password, created_at) VALUES ('email1@email.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', '2019-01-21T05:47:20.949');
INSERT INTO users(email, password, created_at) VALUES ('email2@email.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', '2019-01-21T05:47:20.949');

INSERT INTO categories(name, category_id) VALUES ('Esporte', null);
INSERT INTO categories(name, category_id) VALUES ('Futebol', 1);

INSERT INTO products(name, price, quantity, description, category_id, owner_id)
VALUES ('Produto novinho', 200, 200, 'Só comprar e levar', 1, 1);
INSERT INTO products(name, price, quantity, description, category_id, owner_id)
VALUES ('Produto novinho', 200, 200, 'Só comprar e levar', 1, 2);

INSERT INTO product_feature(name, description, product_id) VALUES ('Qualidade', 'Muito Boa', 1);
INSERT INTO product_feature(name, description, product_id) VALUES ('Tamanho', 'Quase um relógio do faustão', 1);
INSERT INTO product_feature(name, description, product_id) VALUES ('Preço', 'Cabe no seu bolso', 1);

INSERT INTO product_feature(name, description, product_id) VALUES ('Qualidade', 'Muito Boa', 2);
INSERT INTO product_feature(name, description, product_id) VALUES ('Tamanho', 'Quase um relógio do faustão', 2);
INSERT INTO product_feature(name, description, product_id) VALUES ('Preço', 'Cabe no seu bolso', 2);

INSERT INTO product_images(link, product_id) VALUES ('http://fakeimage.com.br/image1.png', 1);
INSERT INTO product_images(link, product_id) VALUES ('http://fakeimage.com.br/image2.png', 1);

INSERT INTO product_images(link, product_id) VALUES ('http://fakeimage.com.br/image1.png', 2);
INSERT INTO product_images(link, product_id) VALUES ('http://fakeimage.com.br/image2.png', 2);
INSERT INTO product_images(link, product_id) VALUES ('http://fakeimage.com.br/image3.png', 2);

INSERT INTO product_opinions(description, review, title, owner_id, product_id) VALUES ('bateria dura bastante', 4.5, 'Produto excelente', 1, 2);
INSERT INTO product_opinions(description, review, title, owner_id, product_id) VALUES ('bateria dura medio', 3.5, 'Produto excelente', 2, 2);
INSERT INTO product_opinions(description, review, title, owner_id, product_id) VALUES ('bateria dura pouco', 2.5, 'Produto excelente', 3, 2);

INSERT INTO product_questions(title, owner_id, product_id, created_at) VALUES ('A bateria dura bastante?', 1, 2, '2019-01-21T05:47:20.949');
INSERT INTO product_questions(title, owner_id, product_id, created_at) VALUES ('A tela é pequena?', 1, 2, '2019-01-21T05:47:20.949');
INSERT INTO product_questions(title, owner_id, product_id, created_at) VALUES ('Tem bastante sinal de wifi', 1, 2, '2019-01-21T05:47:20.949');