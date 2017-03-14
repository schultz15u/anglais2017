CREATE TABLE PACKAGE (
	id_pack INTEGER,
	name STRING,
	can_be_modified_outside INTEGER,
	PRIMARY KEY (id_pack)
);

CREATE TABLE RULE (
	id_rule INTEGER,
	name STRING,
	detail TEXT,
	pack INTEGER,
	PRIMARY KEY (id_rule)
);

CREATE TABLE SENTENCE (
	id_sen INTEGER,
	detail STRING,
	prop_ok STRING,
	prop_no TEXT,
	id_rule INTEGER,
	pack INTEGER,
	PRIMARY KEY (id_sen)
);

INSERT INTO PACKAGE(id_pack, name, can_be_modified_outside) VALUES (1, 'Default package 1', 1);
INSERT INTO PACKAGE(id_pack, name, can_be_modified_outside) VALUES (2, 'Default package 2', 1);
INSERT INTO RULE(id_rule, name, detail, pack) VALUES (1, 'Default rule 1', 'Details of the default rule 1', 1);
INSERT INTO RULE(id_rule, name, detail, pack) VALUES (2, 'Default rule 2', 'Details of the default rule 2', 1);
INSERT INTO RULE(id_rule, name, detail, pack) VALUES (3, 'Default rule 3', 'Details of the default rule 1', 2);
INSERT INTO RULE(id_rule, name, detail, pack) VALUES (4, 'Default rule 4', 'Details of the default rule 2', 2);
INSERT INTO SENTENCE(id_sen, detail, prop_ok, prop_no, id_rule, pack) VALUES (1, 'There is an error in this @', 'sentence', 'sentance,santence', 1, 1);
INSERT INTO SENTENCE(id_sen, detail, prop_ok, prop_no, id_rule, pack) VALUES (2,'hello @', 'bibi', 'you,they', 2, 1);
INSERT INTO SENTENCE(id_sen, detail, prop_ok, prop_no, id_rule, pack) VALUES (3, 'Make a new @', 'sentence', 'sentance,santence', 3, 2);