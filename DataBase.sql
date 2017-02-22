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
	PRIMARY KEY (id_sen)
);

INSERT INTO RULE(ID_RULE, NAME) VALUES (1, 'TOTO');

INSERT INTO SENTENCE(id_sen, detail, prop_ok, prop_no, id_rule) VALUES (21, 'There is an error in this ¤', 'sentence', 'sentance,santence', 1);
INSERT INTO SENTENCE(ID_SEN, DETAIL, PROP_OK, PROP_NO, ID_RULE) VALUES (22,'hello ¤', 'bibi', 'you,they', 1);