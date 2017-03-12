CREATE TABLE PACKAGE (
	id_pack INTEGER,
	name VARCHAR(50),
	can_be_modified_outside INTEGER,
	PRIMARY KEY (id_pack)
);

CREATE TABLE RULE (
	id_rule INTEGER,
	name VARCHAR(50),
	detail TEXT,
	pack INTEGER,
	PRIMARY KEY (id_rule)
);

CREATE TABLE SENTENCE (
	id_sen INTEGER,
	detail VARCHAR(50),
	prop_ok VARCHAR(50),
	prop_no TEXT,
	id_rule INTEGER,
	pack INTEGER,
	PRIMARY KEY (id_sen)
);

INSERT INTO PACKAGE(id_pack, name, can_be_modified_outside) VALUES (1, 'Default package', 1);
INSERT INTO RULE(id_rule, name, detail, pack) VALUES (1, 'Default rule', 'Details of the default rule', 1);
INSERT INTO SENTENCE(id_sen, detail, prop_ok, prop_no, id_rule, pack) VALUES (21, 'There is an error in this ¤', 'sentence', 'sentance,santence', 1, 1);
INSERT INTO SENTENCE(id_sen, detail, prop_ok, prop_no, id_rule, pack) VALUES (22,'hello ¤', 'bibi', 'you,they', 1, 1);