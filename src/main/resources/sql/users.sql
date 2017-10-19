CREATE TABLE community.users (
  id SERIAL PRIMARY KEY,
  username VARCHAR(255) UNIQUE,
  firstname VARCHAR(255),
  surname VARCHAR(255),
  regEmail VARCHAR(255),
  pubEmail VARCHAR(255),
  ghLink VARCHAR(255),
  fbLink VARCHAR(255)
);

INSERT INTO community.users(username, firstname, surname, regemail, pubemail, ghlink, fbLink) VALUES ('MatthewPoletin', 'Matthew', 'Poletin', 'poletinm@yandex.ru', 'contact@matthewpoletin.ru', 'github.com/matthewpoletin', 'fb.me/matthewpoletin');
INSERT INTO community.users(username, firstname, surname, regemail, pubemail, ghlink, fbLink) VALUES ('kormvina', 'Ksenia', 'Strogina', 'ksyshkazaglushka@mail.ru', 'stroginakp@student.bmstu.ru', 'github.com/kormvina', 'fb.me/kseniastrogina');
