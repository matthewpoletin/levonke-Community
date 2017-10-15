CREATE TABLE community.users (
  id SERIAL PRIMARY KEY,
  username VARCHAR(255),
  firstname VARCHAR(255),
  surname VARCHAR(255),
  regEmail VARCHAR(255),
  pubEmail VARCHAR(255),
  ghLink VARCHAR(255),
  fbLink VARCHAR(255)
);

INSERT INTO community.users(username, firstname, surname, regemail, pubemail, ghlink, fbLink) VALUES ('MatthewPoletin', 'Matthew', 'Poletin', 'poletinm@yandex.ru', 'contact@matthewpoletin.ru', 'github.com/matthewpoletin', 'fb.me/matthewpoletin');
INSERT INTO community.users(username, firstname, surname, regemail, pubemail, ghlink, fbLink) VALUES ('polall', 'Alexander', 'Poletin', 'polall@yandex.ru', 'polall@tdprime.ru', NULL, NULL);

