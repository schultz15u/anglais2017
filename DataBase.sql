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

CREATE TABLE SCORE (
	id_score INTEGER,
	score FLOAT,
	pack INTEGER,
	PRIMARY KEY (id_score)
);

