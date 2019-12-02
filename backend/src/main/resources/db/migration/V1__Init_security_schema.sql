CREATE TABLE `role` (
`id` bigint NOT NULL AUTO_INCREMENT,
`description` varchar(255),
`name` varchar(255),
PRIMARY KEY (`id`));

CREATE TABLE `user` (
`id` bigint NOT NULL AUTO_INCREMENT,
`username` varchar(255),
`password` varchar(255),
`id_role` bigint,
PRIMARY KEY (`id`),
CONSTRAINT FKrhfovtciq1l558cw6udg0h0d3 
	FOREIGN KEY(id_role) 
	REFERENCES role (id) 
	ON DELETE NO ACTION
	ON UPDATE CASCADE);
