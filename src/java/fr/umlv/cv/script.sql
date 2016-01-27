CREATE TABLE FORMATION (ID BIGINT NOT NULL, PRIMARY KEY (ID))
CREATE TABLE USER (ID BIGINT NOT NULL, PRIMARY KEY (ID))
CREATE TABLE CV (ID BIGINT NOT NULL, EXPERIENCE_ID BIGINT, FORMATION_ID BIGINT, INTEREST_ID BIGINT, LANGAGE_ID BIGINT, SKILL_ID BIGINT, USER_ID BIGINT, PRIMARY KEY (ID))
CREATE TABLE SKILL (ID BIGINT NOT NULL, PRIMARY KEY (ID))
CREATE TABLE INTEREST (ID BIGINT NOT NULL, DESCRIPTION VARCHAR(255), NAME VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE ANNONCE (ID BIGINT NOT NULL, PRIMARY KEY (ID))
CREATE TABLE LANGAGE (ID BIGINT NOT NULL, LEVEL INTEGER, NAME VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE EXPERIENCE (ID BIGINT NOT NULL, DATEBEGIN VARCHAR(255), DATEEND VARCHAR(255), DESCRIPTION VARCHAR(255), MISSION VARCHAR(255), PLACE VARCHAR(255), PRIMARY KEY (ID))
ALTER TABLE CV ADD CONSTRAINT FK_CV_SKILL_ID FOREIGN KEY (SKILL_ID) REFERENCES SKILL (ID)
ALTER TABLE CV ADD CONSTRAINT FK_CV_INTEREST_ID FOREIGN KEY (INTEREST_ID) REFERENCES INTEREST (ID)
ALTER TABLE CV ADD CONSTRAINT FK_CV_USER_ID FOREIGN KEY (USER_ID) REFERENCES USER (ID)
ALTER TABLE CV ADD CONSTRAINT CV_EXPERIENCE_ID FOREIGN KEY (EXPERIENCE_ID) REFERENCES EXPERIENCE (ID)
ALTER TABLE CV ADD CONSTRAINT FK_CV_FORMATION_ID FOREIGN KEY (FORMATION_ID) REFERENCES FORMATION (ID)
ALTER TABLE CV ADD CONSTRAINT FK_CV_LANGAGE_ID FOREIGN KEY (LANGAGE_ID) REFERENCES LANGAGE (ID)
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT DECIMAL(15), PRIMARY KEY (SEQ_NAME))
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0)
