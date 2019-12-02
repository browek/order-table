INSERT INTO role (id, description, name) VALUES (1, 'Admin role', 'ROLE_ADMIN');
INSERT INTO role (id, description, name) VALUES (2, 'Moderator role', 'ROLE_MODERATOR');
INSERT INTO role (id, description, name) VALUES (3, 'User role', 'ROLE_USER');

INSERT INTO user(username, password, id_role) VALUES
 ('admin', '$2a$11$RKCkokakTxYHj7i70qkq0u0sMZK9xzDv4O0gjVLehPYQLSfxGLF12',1);

INSERT INTO user(username, password,id_role) VALUES
 ('moderator', '$2a$11$RKCkokakTxYHj7i70qkq0u0sMZK9xzDv4O0gjVLehPYQLSfxGLF12',2);

INSERT INTO user(username, password,id_role) VALUES
('user', '$2a$11$RKCkokakTxYHj7i70qkq0u0sMZK9xzDv4O0gjVLehPYQLSfxGLF12',3);

