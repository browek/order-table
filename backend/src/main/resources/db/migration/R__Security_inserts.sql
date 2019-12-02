INSERT INTO role (id, description, name) VALUES (1, 'Admin role', 'ROLE_ADMIN');
INSERT INTO role (id, description, name) VALUES (2, 'Moderator role', 'ROLE_MODERATOR');
INSERT INTO role (id, description, name) VALUES (3, 'User role', 'ROLE_USER');

INSERT INTO user(id, username, password, id_role) VALUES
 (1, 'Admin', '$2a$04$iRsccpxqihb7QvTewwyncOVpMTF/xLX4YekCDIgUi4b.BBzM4uRdi',1);

INSERT INTO user(id, username, password,id_role) VALUES
 (2, 'Moderator', '$2a$04$zhnbwbGJkroe6CyQdA8Og.dHMt9NEGf.ZdrZGyv5E8K1UmKlUvACC',2);

INSERT INTO user(id, username, password,id_role) VALUES
(3, 'User', '$2a$04$gpcSxxNLO/pNfvxVDCAFqeInF9RrmQadtyMFX9Mywum22eEz7Lvqa',3);

