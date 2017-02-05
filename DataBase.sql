CREATE TABLE USER (
  id_user INTEGER,
  username VARCHAR(50),
  mail VARCHAR(50),
  id_comment NUMBER(25),
  id_collection INTEGER,
  password VARCHAR(50),
  PRIMARY KEY (id_user)
);

CREATE TABLE SUBSCRIBE (
  id_user INTEGER,
  id_user_1 INTEGER,
  PRIMARY KEY (id_user, id_user_1),
  FOREIGN KEY (id_user) REFERENCES PERSONNE (id_user),
  FOREIGN KEY (id_user_1) REFERENCES PERSONNE (id_user)
);

CREATE TABLE VIDEO (
	id_video INTEGER,
	title_video VARCHAR(140),
	length NUMBER(5),
	thumbnail VARCHAR(100),
	tag VARCHAR(500),
	status VARCHAR(20) DEFAULT 'public',
	date_upload_video DATE,
	video_count INTEGER,
	nb_like INTEGER,
	id_user INTEGER,
	PRIMARY KEY (id_video),
	FOREIGN KEY (id_user) REFERENCES USER (id_user)
);

CREATE TABLE COMMENT (
  id_comment NUMBER(25),
  texte_comment VARCHAR(140),
  date_comment DATE,
  nb_like INTEGER,
  id_comment_1 NUMBER(25),
  id_user INTEGER,
  PRIMARY KEY (id_comment),
  FOREIGN KEY (id_comment_1) REFERENCES COMMENT (id_comment),
  FOREIGN KEY (id_user) REFERENCES USER (id_user)
);

CREATE TABLE COLLECTION (
  id_collection INTEGER,
  name_collection VARCHAR(140),
  id_user INTEGER,
  FOREIGN KEY (id_user) REFERENCES USER (id_user)
  PRIMARY KEY (id_collection)
);

CREATE TABLE COMPOSITION_COLLECTION(
  id_video INTEGER,
  id_collection INTEGER,
  PRIMARY KEY (id_video, id_collection),
  FOREIGN KEY (id_video) REFERENCES VIDEO (id_video),
  FOREIGN KEY (id_collection) REFERENCES COLLECTION (id_collection)
  );

