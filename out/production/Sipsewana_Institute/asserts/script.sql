SHOW DATABASES;
DROP DATABASE IF EXISTS sipsewana;
CREATE DATABASE IF NOT EXISTS sipsewana;
SHOW DATABASES;
USE sipsewana;

DROP TABLE IF EXISTS Student;
CREATE TABLE IF NOT EXISTS Student(
    studentId VARCHAR(15),
    studentName VARCHAR(15),
    address VARCHAR(45) ,
    programName VARCHAR(45),
    CONSTRAINT PRIMARY KEY (studentId)
    );
SHOW TABLES;
DESCRIBE Student;
#==============================================================
DROP TABLE IF EXISTS Program;
CREATE TABLE IF NOT EXISTS Program(
    programId VARCHAR(15),
    programName VARCHAR(15),
    Duration VARCHAR(45) ,
    programFee VARCHAR(45),
    CONSTRAINT PRIMARY KEY (programId)
    );
SHOW TABLES;
DESCRIBE Program;
#==============================================================
DROP TABLE IF EXISTS student_program;
CREATE TABLE IF NOT EXISTS student_program(
    studentId VARCHAR(15),
    programId VARCHAR(15),
    CONSTRAINT PRIMARY KEY (studentId, programId),
    CONSTRAINT FOREIGN KEY (studentId) REFERENCES Student (studentId) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (programId) REFERENCES Program (programId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES;
DESCRIBE student_program;
