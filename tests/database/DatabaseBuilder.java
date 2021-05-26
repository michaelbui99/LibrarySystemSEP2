package database;

import database.BaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseBuilder extends BaseDAO
{

  public void createDummyDataWithoutInfo() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement ddl = connection.prepareStatement(
          "DROP SCHEMA IF EXISTS librarysystem CASCADE;\n"
              + "CREATE SCHEMA librarysystem;\n"
              + "SET SCHEMA 'librarysystem';\n" + "\n" + "--DOMAINS\n"
              + "CREATE DOMAIN cpr CHAR(11) CHECK (value LIKE ('%-%'));\n"
              + "CREATE DOMAIN id INT;\n" + "CREATE DOMAIN copy_no SMALLINT;\n"
              + "CREATE DOMAIN f_name VARCHAR(20);\n"
              + "CREATE DOMAIN l_name VARCHAR(40);\n"
              + "CREATE DOMAIN title VARCHAR(60);\n"
              + "CREATE DOMAIN genre VARCHAR(40);\n"
              + "CREATE DOMAIN author VARCHAR(100);\n"
              + "CREATE DOMAIN country VARCHAR(40);\n"
              + "CREATE DOMAIN city VARCHAR(40);\n"
              + "CREATE DOMAIN street_name VARCHAR(60);\n"
              + "CREATE DOMAIN tel_no CHAR(11)\n"
              + "    CHECK ( VALUE LIKE '+45%');\n" + "\n" + "--TABLES\n"
              + "CREATE TABLE IF NOT EXISTS address\n" + "(\n"
              + "    address_id  INT GENERATED BY DEFAULT AS IDENTITY,\n"
              + "    city        city                             NOT NULL,\n"
              + "    zip_code    INT\n"
              + "        CHECK ( zip_code BETWEEN 1000 AND 10000) NOT NULL,\n"
              + "    street_name street_name                      NOT NULL,\n"
              + "    street_no   VARCHAR(10),\n"
              + "    PRIMARY KEY (address_id)\n" + ");\n" + "\n"
              + "CREATE TABLE IF NOT EXISTS place\n" + "(\n"
              + "    place_id       INT GENERATED BY DEFAULT AS IDENTITY,\n"
              + "    hall_no        SMALLINT NOT NULL,\n"
              + "    department     CHAR(1)  NOT NULL, --??\n"
              + "    creator_l_name l_name   NOT NULL,\n"
              + "    genre          genre    NOT NULL,\n"
              + "    PRIMARY KEY (place_id)\n" + ");\n" + "\n" + "/*\n"
              + "vi kan have en tabel kaldet \"person\", hvor vi\n"
              + "kan forbinde \"borrower\" og \"librarian\" til i\n"
              + "stedet at have 3 individuelle tabeller\n" + "*/\n" + "\n"
              + "CREATE TABLE IF NOT EXISTS material_creator\n" + "(\n"
              + "    person_id INT GENERATED BY DEFAULT AS IDENTITY,\n"
              + "    f_name    f_name NOT NULL,\n"
              + "    l_name    l_name NOT NULL,\n" + "    dob       DATE,\n"
              + "    country   country,\n" + "    PRIMARY KEY (person_id)\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS librarian\n" + "(\n"
              + "    employee_no INT,\n"
              + "    f_name      f_name            NOT NULL,\n"
              + "    l_name      l_name            NOT NULL,\n"
              + "    cpr_no      cpr               NOT NULL, -- den kan være en PRIMARY KEY\n"
              + "    tel_no      tel_no UNIQUE     NOT NULL,\n"
              + "    email       VARCHAR(50)\n"
              + "        CHECK ( email LIKE '%@%') NOT NULL UNIQUE,\n"
              + "    address_id  id,\n"
              + "    password    VARCHAR(100)      NOT NULL,\n"
              + "    PRIMARY KEY (employee_no),\n"
              + "    FOREIGN KEY (address_id) REFERENCES address (address_id)\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS borrower\n" + "(\n"
              + "    cpr_no     cpr,\n"
              + "    f_name     f_name             NOT NULL,\n"
              + "    l_name     l_name             NOT NULL,\n"
              + "    email      VARCHAR(50)\n"
              + "        CHECK ( email LIKE '%@%') NOT NULL UNIQUE,\n"
              + "    tel_no     tel_no,\n" + "    address_id id,\n"
              + "    password   VARCHAR(100)       NOT NULL,\n"
              + "    PRIMARY KEY (cpr_no),\n"
              + "    FOREIGN KEY (address_id) REFERENCES address (address_id)\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS material\n" + "(\n"
              + "    material_id                SERIAL                                                                                NOT NULL,\n"
              + "    title                      title                                                                                 NOT NULL,\n"
              + "    audience                   VARCHAR(20) CHECK ( audience IN\n"
              + "                                                   ('Voksen', 'Barn', 'Teenager', 'Familie', 'Ældre', 'Studerende')) NOT NULL,\n"
              + "    description_of_the_content VARCHAR(1000),\n"
              + "    publisher                  VARCHAR(40)                                                                           NOT NULL,\n"
              + "    language_                  VARCHAR(7) CHECK ( language_ IN ('Engelsk', 'Dansk', 'Arabisk'))                      NOT NULL,\n"
              + "    release_date               DATE,\n"
              + "    genre                      genre                                                                                 NOT NULL,\n"
              + "    url                        VARCHAR,\n"
              + "    PRIMARY KEY (material_id)\n" + ");\n"
              + "CREATE TABLE IF NOT EXISTS material_keywords\n" + "(\n"
              + "    material_id INT,\n"
              + "    keyword     VARCHAR(50) NOT NULL,\n"
              + "    PRIMARY KEY (material_id, keyword),\n"
              + "    FOREIGN KEY (material_id) REFERENCES material (material_id) on delete CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS material_copy\n"
              + "(\n" + "    material_id SMALLINT,\n"
              + "    copy_no     INT GENERATED BY DEFAULT AS IDENTITY,\n"
              + "    available   BOOLEAN DEFAULT TRUE,\n"
              + "    PRIMARY KEY (material_id, copy_no),\n"
              + "    FOREIGN KEY (material_id) REFERENCES material (material_id) on delete CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS e_book\n" + "(\n"
              + "    material_id id  NOT NULL,\n"
              + "    page_no     INT NOT NULL,\n"
              + "    license_no  INT NOT NULL,\n"
              + "    author      INT NOT NULL,\n"
              + "    PRIMARY KEY (material_id),\n"
              + "    FOREIGN KEY (material_id) REFERENCES material (material_id)  on delete CASCADE,\n"
              + "    FOREIGN KEY (author) REFERENCES material_creator (person_id)  on delete CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS book\n" + "(\n"
              + "    material_id id      NOT NULL,\n"
              + "    page_no     INT     NOT NULL,\n"
              + "    author      INT     NOT NULL,\n"
              + "    isbn        VARCHAR NOT NULL,\n" + "    place_id    id,\n"
              + "    PRIMARY KEY (material_id),\n"
              + "    FOREIGN KEY (material_id) REFERENCES material (material_id)  on delete CASCADE,\n"
              + "    FOREIGN KEY (place_id) REFERENCES place (place_id)  on delete CASCADE,\n"
              + "    FOREIGN KEY (author) REFERENCES material_creator (person_id)  on delete CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS dvd\n" + "(\n"
              + "    material_id   id          NOT NULL,\n"
              + "    subtitle_lang VARCHAR(10) NOT NULL,\n"
              + "    length_       INT         NOT NULL,\n"
              + "    place_id      id,\n" + "    PRIMARY KEY (material_id),\n"
              + "    FOREIGN KEY (material_id) REFERENCES material (material_id)  on delete CASCADE,\n"
              + "    FOREIGN KEY (place_id) REFERENCES place (place_id)  on delete CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS cd\n" + "(\n"
              + "    material_id id      NOT NULL,\n"
              + "    length_     DECIMAL NOT NULL,\n" + "    place_id    id,\n"
              + "    PRIMARY KEY (material_id),\n"
              + "    FOREIGN KEY (material_id) REFERENCES material (material_id)  on delete CASCADE,\n"
              + "    FOREIGN KEY (place_id) REFERENCES place (place_id)  on delete CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS audiobook\n" + "(\n"
              + "    material_id id       NOT NULL,\n"
              + "    length_     SMALLINT NOT NULL,\n"
              + "    author      INT,\n" + "    PRIMARY KEY (material_id),\n"
              + "    FOREIGN KEY (material_id) REFERENCES material (material_id)  on delete CASCADE,\n"
              + "    FOREIGN KEY (author) REFERENCES material_creator (person_id)  on delete CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS loan\n" + "(\n"
              + "    loan_no     SERIAL NOT NULL,\n"
              + "    loan_date   DATE   NOT NULL,\n"
              + "    deadline    DATE   NOT NULL,\n" + "    return_date DATE,\n"
              + "    cpr_no      cpr,\n" + "    material_id SMALLINT,\n"
              + "    copy_no     SMALLINT,\n" + "    PRIMARY KEY (loan_no),\n"
              + "    FOREIGN KEY (cpr_no) REFERENCES borrower (cpr_no),\n"
              + "    FOREIGN KEY (material_id, copy_no) REFERENCES material_copy (material_id, copy_no)  on delete CASCADE--?\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS reservation\n"
              + "(\n" + "    reservation_date DATE   NOT NULL,\n"
              + "    reservation_id   SERIAL NOT NULL,\n"
              + "    cpr_no           cpr,\n"
              + "    material_id      SMALLINT,\n"
              + "    ready            BOOLEAN,\n"
              + "    PRIMARY KEY (reservation_id),\n"
              + "    FOREIGN KEY (cpr_no) REFERENCES borrower (cpr_no),\n"
              + "    FOREIGN KEY (material_id) REFERENCES material(material_id)  on delete CASCADE--?\n"
              + ");\n");
      ddl.executeUpdate();
      connection.commit();
    }
  }

  public void insertDummyReservationInfo() throws SQLException
  {
    createDummyDataWithoutInfo();
    try (Connection connection = getConnection())
    {
      PreparedStatement ddl = connection.prepareStatement(
       " insert into material\n"
           + "    (title, audience, description_of_the_content, publisher, language_, release_date, genre, url)\n"
           + " values ('one','Voksen','cat live', 'catman','Dansk', '2000-10-10','Fantasy', 'null');\n"
           + "\n" + "insert into place values (2, 1, 'p' ,'John','Fantasy');\n"
           + "\n"
           + "insert into material_creator values (2, 'John', 'Johnsen', '2000-10-10' , 'Denmark');\n"
           + "insert into book values (1,200,2,'5444',2);\n"
           + "insert into material_copy values (1,4,false);\n"
           + "insert into address values (1,'horsens',8700,'doc',4);\n"
           + "insert into borrower values ('111111-1122','Lilian', 'Bittar', 'bittarlily@gmail.com', '+4526700792', 1, 'password');\n"
           + "insert into reservation values ('2000-10-10',1, '111111-1122',1,'true'); \n"
           + "insert into material\n"
           + "    (title, audience, description_of_the_content, publisher, language_, release_date, genre, url)\n"
           + " values ('to','Voksen','cat ', 'cats','Dansk', '2000-11-10','Fantasy', 'null');\n"
           + "\n" + "insert into place values (3, 2, 'p' ,'John','Fantasy');\n"
           + "insert into material_creator values (5, 'Joh', 'John', '2000-10-10' , 'Denmark');\n"
           + "insert into book values (2,200,5,'5444',3);\n"
           + "insert into material_copy values (2,4,false);\n"
           + "insert into reservation values ('2000-10-10',2, '111111-1122',2,'true'); \n"
      );
          ddl.executeUpdate();
          connection.commit();
    }
  }

  public void insertDummyWithOutReservationInfo() throws SQLException
  {
    createDummyDataWithoutInfo();
    try (Connection connection = getConnection())
    {
      PreparedStatement ddl = connection.prepareStatement(
          " insert into material\n"
              + "    (title, audience, description_of_the_content, publisher, language_, release_date, genre, url)\n"
              + " values ('one','Voksen','cat live', 'catman','Dansk', '2000-10-10','Fantasy', 'null');\n"
              + "\n" + "insert into place values (2, 1, 'p' ,'John','Fantasy');\n"
              + "\n"
              + "insert into material_creator values (2, 'John', 'Johnsen', '2000-10-10' , 'Denmark');\n"
              + "insert into book values (1,200,2,'5444',2);\n"
              + "insert into material_copy values (1,4,false);\n"
              + "insert into address values (1,'horsens',8700,'doc',4);\n"
              + "insert into borrower values ('111111-1122','Lilian', 'Bittar', 'bittarlily@gmail.com', '+4526700792', 1, 'password');\n"
      );
      ddl.executeUpdate();
      connection.commit();
    }
  }


  public void createDummyDatabaseDataWithLoan() throws SQLException
  { //LoanQueries.sql
    try (Connection connection = getConnection())
    {
      PreparedStatement ddl = connection.prepareStatement(
          "DROP SCHEMA IF EXISTS librarysystem CASCADE;\n"
              + "CREATE SCHEMA librarysystem;\n"
              + "SET SCHEMA 'librarysystem';\n" + "\n" + "--DOMAINS\n"
              + "CREATE DOMAIN cpr CHAR(11) CHECK (value LIKE ('%-%'));\n"
              + "CREATE DOMAIN id INT;\n" + "CREATE DOMAIN copy_no SMALLINT;\n"
              + "CREATE DOMAIN f_name VARCHAR(20);\n"
              + "CREATE DOMAIN l_name VARCHAR(40);\n"
              + "CREATE DOMAIN title VARCHAR(60);\n"
              + "CREATE DOMAIN genre VARCHAR(40);\n"
              + "CREATE DOMAIN author VARCHAR(100);\n"
              + "CREATE DOMAIN country VARCHAR(40);\n"
              + "CREATE DOMAIN city VARCHAR(40);\n"
              + "CREATE DOMAIN street_name VARCHAR(60);\n"
              + "CREATE DOMAIN tel_no CHAR(11)\n"
              + "    CHECK ( VALUE LIKE '+45%');\n" + "\n" + "--TABLES\n"
              + "CREATE TABLE IF NOT EXISTS address\n" + "(\n"
              + "    address_id  INT GENERATED BY DEFAULT AS IDENTITY,\n"
              + "    city        city                             NOT NULL,\n"
              + "    zip_code    INT\n"
              + "        CHECK ( zip_code BETWEEN 1000 AND 10000) NOT NULL,\n"
              + "    street_name street_name                      NOT NULL,\n"
              + "    street_no   VARCHAR(10),\n"
              + "    PRIMARY KEY (address_id)\n" + ");\n" + "\n"
              + "CREATE TABLE IF NOT EXISTS place\n" + "(\n"
              + "    place_id       INT GENERATED BY DEFAULT AS IDENTITY,\n"
              + "    hall_no        SMALLINT NOT NULL,\n"
              + "    department     CHAR(1)  NOT NULL, --??\n"
              + "    creator_l_name l_name   NOT NULL,\n"
              + "    genre          genre    NOT NULL,\n"
              + "    PRIMARY KEY (place_id)\n" + ");\n" + "\n" + "/*\n"
              + "vi kan have en tabel kaldet \"person\", hvor vi\n"
              + "kan forbinde \"borrower\" og \"librarian\" til i\n"
              + "stedet at have 3 individuelle tabeller\n" + "*/\n" + "\n"
              + "CREATE TABLE IF NOT EXISTS material_creator\n" + "(\n"
              + "    person_id INT GENERATED BY DEFAULT AS IDENTITY,\n"
              + "    f_name    f_name NOT NULL,\n"
              + "    l_name    l_name NOT NULL,\n" + "    dob       DATE,\n"
              + "    country   country,\n" + "    PRIMARY KEY (person_id)\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS librarian\n" + "(\n"
              + "    employee_no INT,\n"
              + "    f_name      f_name            NOT NULL,\n"
              + "    l_name      l_name            NOT NULL,\n"
              + "    cpr_no      cpr               NOT NULL, -- den kan være en PRIMARY KEY\n"
              + "    tel_no      tel_no UNIQUE     NOT NULL,\n"
              + "    email       VARCHAR(50)\n"
              + "        CHECK ( email LIKE '%@%') NOT NULL UNIQUE,\n"
              + "    address_id  id,\n"
              + "    password    VARCHAR(100)      NOT NULL,\n"
              + "    PRIMARY KEY (employee_no),\n"
              + "    FOREIGN KEY (address_id) REFERENCES address (address_id) ON DELETE CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS borrower\n" + "(\n"
              + "    cpr_no     cpr,\n"
              + "    f_name     f_name             NOT NULL,\n"
              + "    l_name     l_name             NOT NULL,\n"
              + "    email      VARCHAR(50)\n"
              + "        CHECK ( email LIKE '%@%') NOT NULL UNIQUE,\n"
              + "    tel_no     tel_no,\n" + "    address_id id,\n"
              + "    password   VARCHAR(100)       NOT NULL,\n"
              + "    PRIMARY KEY (cpr_no),\n"
              + "    FOREIGN KEY (address_id) REFERENCES address (address_id) ON DELETE CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS material\n" + "(\n"
              + "    material_id                SERIAL                                                                                NOT NULL,\n"
              + "    title                      title                                                                                 NOT NULL,\n"
              + "    audience                   VARCHAR(20) CHECK ( audience IN\n"
              + "                                                   ('Voksen', 'Barn', 'Teenager', 'Familie', 'Ældre', 'Studerende')) NOT NULL,\n"
              + "    description_of_the_content VARCHAR(1000),\n"
              + "    publisher                  VARCHAR(40)                                                                           NOT NULL,\n"
              + "    language_                  VARCHAR(7) CHECK ( language_ IN ('Engelsk', 'Dansk', 'Arabisk'))                      NOT NULL,\n"
              + "    release_date               DATE,\n"
              + "    genre                      genre                                                                                 NOT NULL,\n"
              + "    url                        VARCHAR,\n"
              + "    PRIMARY KEY (material_id)\n" + ");\n"
              + "CREATE TABLE IF NOT EXISTS material_keywords\n" + "(\n"
              + "    material_id INT,\n"
              + "    keyword     VARCHAR(50) NOT NULL,\n"
              + "    PRIMARY KEY (material_id, keyword),\n"
              + "    FOREIGN KEY (material_id) REFERENCES material (material_id) ON DELETE CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS material_copy\n"
              + "(\n" + "    material_id SMALLINT,\n"
              + "    copy_no     INT GENERATED BY DEFAULT AS IDENTITY,\n"
              + "    available   BOOLEAN DEFAULT true,\n"
              + "    PRIMARY KEY (material_id, copy_no),\n"
              + "    FOREIGN KEY (material_id) REFERENCES material (material_id) ON DELETE CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS e_book\n" + "(\n"
              + "    material_id id  NOT NULL,\n"
              + "    page_no     INT NOT NULL,\n"
              + "    license_no  INT NOT NULL,\n"
              + "    author      INT NOT NULL,\n"
              + "    PRIMARY KEY (material_id),\n"
              + "    FOREIGN KEY (material_id) REFERENCES material (material_id) ON DELETE CASCADE ,\n"
              + "    FOREIGN KEY (author) REFERENCES material_creator (person_id) ON DELETE CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS book\n" + "(\n"
              + "    material_id id      NOT NULL,\n"
              + "    page_no     INT     NOT NULL,\n"
              + "    author      INT     NOT NULL,\n"
              + "    isbn        VARCHAR NOT NULL,\n" + "    place_id    id,\n"
              + "    PRIMARY KEY (material_id),\n"
              + "    FOREIGN KEY (material_id) REFERENCES material (material_id) ON DELETE CASCADE ,\n"
              + "    FOREIGN KEY (place_id) REFERENCES place (place_id) ON DELETE CASCADE ,\n"
              + "    FOREIGN KEY (author) REFERENCES material_creator (person_id) ON DELETE CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS dvd\n" + "(\n"
              + "    material_id   id          NOT NULL,\n"
              + "    subtitle_lang VARCHAR(10) NOT NULL,\n"
              + "    length_       INT         NOT NULL,\n"
              + "    place_id      id,\n" + "    PRIMARY KEY (material_id),\n"
              + "    FOREIGN KEY (material_id) REFERENCES material (material_id) ON DELETE CASCADE ,\n"
              + "    FOREIGN KEY (place_id) REFERENCES place (place_id) ON DELETE CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS cd\n" + "(\n"
              + "    material_id id      NOT NULL,\n"
              + "    length_     DECIMAL NOT NULL,\n" + "    place_id    id,\n"
              + "    PRIMARY KEY (material_id),\n"
              + "    FOREIGN KEY (material_id) REFERENCES material (material_id) ON DELETE CASCADE ,\n"
              + "    FOREIGN KEY (place_id) REFERENCES place (place_id) ON DELETE CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS audiobook\n" + "(\n"
              + "    material_id id       NOT NULL,\n"
              + "    length_     SMALLINT NOT NULL,\n"
              + "    author      INT,\n" + "    PRIMARY KEY (material_id),\n"
              + "    FOREIGN KEY (material_id) REFERENCES material (material_id) ON DELETE CASCADE ,\n"
              + "    FOREIGN KEY (author) REFERENCES material_creator (person_id) ON DELETE CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS loan\n" + "(\n"
              + "    loan_no     SERIAL NOT NULL,\n"
              + "    loan_date   DATE   NOT NULL,\n"
              + "    deadline    DATE   NOT NULL,\n" + "    return_date DATE,\n"
              + "    cpr_no      cpr,\n" + "    material_id SMALLINT,\n"
              + "    copy_no     SMALLINT,\n"
              + "    number_of_extensions SMALLINT check (number_of_extensions < 3) DEFAULT 0,\n"
              + "    PRIMARY KEY (loan_no),\n"
              + "    FOREIGN KEY (cpr_no) REFERENCES borrower (cpr_no) ON DELETE CASCADE ,\n"
              + "    FOREIGN KEY (material_id, copy_no) REFERENCES material_copy (material_id, copy_no) ON DELETE CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS reservation\n"
              + "(\n" + "    reservation_date DATE   NOT NULL,\n"
              + "    reservation_id   SERIAL NOT NULL,\n"
              + "    cpr_no           cpr,\n"
              + "    material_id      SMALLINT,\n"
              + "    ready            BOOLEAN,\n"
              + "    PRIMARY KEY (reservation_id),\n"
              + "    FOREIGN KEY (cpr_no) REFERENCES borrower (cpr_no) ON DELETE CASCADE ,\n"
              + "    FOREIGN KEY (material_id) REFERENCES material (material_id) ON DELETE CASCADE\n"
              + ");\n" + "\n");
      ddl.executeUpdate();

      PreparedStatement sql = connection.prepareStatement(
          "SET SCHEMA 'librarysystem';\n" + "\n"
              + "INSERT INTO material (title, audience, description_of_the_content, publisher, language_,\n"
              + "                      release_date, genre, url)\n"
              + "VALUES ('Title1', 'Voksen', 'HELLO DESC', 'Publisher1', 'Dansk', '2020-12-12', 'Fantasy', NULL);\n"
              + "INSERT INTO material (title, audience, description_of_the_content, publisher, language_,\n"
              + "                      release_date, genre, url)\n"
              + "VALUES ('Title2', 'Voksen', 'HELLO DESC', 'Publisher2', 'Dansk', '2020-12-12', 'Fantasy', NULL);\n"
              + "\n"
              + "INSERT INTO place (place_id, hall_no, department, creator_l_name, genre)\n"
              + "VALUES (1, 1, 'M', 'Bob', 'Fantasy');\n" + "\n"
              + "INSERT INTO material_creator (person_id, f_name, l_name, dob, country)\n"
              + "VALUES (1, 'Bob', 'Bobsen', '2020-12-12', 'Denmark');\n" + "\n"
              + "INSERT INTO book (material_id, page_no, author, isbn, place_id)\n"
              + "VALUES (1, 200, 1, '321432432', 1);\n" + "\n"
              + "INSERT INTO material_copy (material_id, copy_no)\n"
              + "VALUES (1, 1);\n" + "\n"
              + "INSERT INTO dvd (material_id, subtitle_lang, length_, place_id)\n"
              + "VALUES (2, 'Dansk', 120, 1);\n"
              + "INSERT INTO material_copy (material_id, copy_no)\n"
              + "VALUES (2, 1);\n" + "\n"
              + "INSERT INTO address(address_id, city, zip_code, street_name, street_no)\n"
              + "VALUES (1, 'Horsens', 8700, 'Axelborg', '8');\n" + "\n"
              + "INSERT INTO borrower (cpr_no, f_name, l_name, email, tel_no, address_id, password)\n"
              + "VALUES ('111111-1111', 'Michael', 'Bui', 'michael@gmail.com', '+4512345678', 1, 'password');\n"
              + "\n"
              + "INSERT INTO borrower (cpr_no, f_name, l_name, email, tel_no, address_id, password)\n"
              + "VALUES ('222222-2222', 'B2', 'B2L', 'TEST@gmail.com', '+4587654321', 1, 'password');\n"
              + "\n"
              + "INSERT INTO loan (loan_date, deadline, return_date, cpr_no, material_id, copy_no)\n"
              + "VALUES ('2021-01-01', '2021-01-04', NULL, '111111-1111', 1, 1);");
      sql.executeUpdate();
      connection.commit();
    }
  }

  public void createDummyDatabaseDataWithoutLoan() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement ddl = connection.prepareStatement(
          "DROP SCHEMA IF EXISTS librarysystem CASCADE;\n"
              + "CREATE SCHEMA librarysystem;\n"
              + "SET SCHEMA 'librarysystem';\n" + "\n" + "--DOMAINS\n"
              + "CREATE DOMAIN cpr CHAR(11) CHECK (value LIKE ('%-%'));\n"
              + "CREATE DOMAIN id INT;\n" + "CREATE DOMAIN copy_no SMALLINT;\n"
              + "CREATE DOMAIN f_name VARCHAR(20);\n"
              + "CREATE DOMAIN l_name VARCHAR(40);\n"
              + "CREATE DOMAIN title VARCHAR(60);\n"
              + "CREATE DOMAIN genre VARCHAR(40);\n"
              + "CREATE DOMAIN author VARCHAR(100);\n"
              + "CREATE DOMAIN country VARCHAR(40);\n"
              + "CREATE DOMAIN city VARCHAR(40);\n"
              + "CREATE DOMAIN street_name VARCHAR(60);\n"
              + "CREATE DOMAIN tel_no CHAR(11)\n"
              + "    CHECK ( VALUE LIKE '+45%');\n" + "\n" + "--TABLES\n"
              + "CREATE TABLE IF NOT EXISTS address\n" + "(\n"
              + "    address_id  INT GENERATED BY DEFAULT AS IDENTITY,\n"
              + "    city        city                             NOT NULL,\n"
              + "    zip_code    INT\n"
              + "        CHECK ( zip_code BETWEEN 1000 AND 10000) NOT NULL,\n"
              + "    street_name street_name                      NOT NULL,\n"
              + "    street_no   VARCHAR(10),\n"
              + "    PRIMARY KEY (address_id)\n" + ");\n" + "\n"
              + "CREATE TABLE IF NOT EXISTS place\n" + "(\n"
              + "    place_id       INT GENERATED BY DEFAULT AS IDENTITY,\n"
              + "    hall_no        SMALLINT NOT NULL,\n"
              + "    department     CHAR(1)  NOT NULL, --??\n"
              + "    creator_l_name l_name   NOT NULL,\n"
              + "    genre          genre    NOT NULL,\n"
              + "    PRIMARY KEY (place_id)\n" + ");\n" + "\n" + "/*\n"
              + "vi kan have en tabel kaldet \"person\", hvor vi\n"
              + "kan forbinde \"borrower\" og \"librarian\" til i\n"
              + "stedet at have 3 individuelle tabeller\n" + "*/\n" + "\n"
              + "CREATE TABLE IF NOT EXISTS material_creator\n" + "(\n"
              + "    person_id INT GENERATED BY DEFAULT AS IDENTITY,\n"
              + "    f_name    f_name NOT NULL,\n"
              + "    l_name    l_name NOT NULL,\n" + "    dob       DATE,\n"
              + "    country   country,\n" + "    PRIMARY KEY (person_id)\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS librarian\n" + "(\n"
              + "    employee_no INT,\n"
              + "    f_name      f_name            NOT NULL,\n"
              + "    l_name      l_name            NOT NULL,\n"
              + "    cpr_no      cpr               NOT NULL, -- den kan være en PRIMARY KEY\n"
              + "    tel_no      tel_no UNIQUE     NOT NULL,\n"
              + "    email       VARCHAR(50)\n"
              + "        CHECK ( email LIKE '%@%') NOT NULL UNIQUE,\n"
              + "    address_id  id,\n"
              + "    password    VARCHAR(100)      NOT NULL,\n"
              + "    PRIMARY KEY (employee_no),\n"
              + "    FOREIGN KEY (address_id) REFERENCES address (address_id) ON DELETE CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS borrower\n" + "(\n"
              + "    cpr_no     cpr,\n"
              + "    f_name     f_name             NOT NULL,\n"
              + "    l_name     l_name             NOT NULL,\n"
              + "    email      VARCHAR(50)\n"
              + "        CHECK ( email LIKE '%@%') NOT NULL UNIQUE,\n"
              + "    tel_no     tel_no,\n" + "    address_id id,\n"
              + "    password   VARCHAR(100)       NOT NULL,\n"
              + "    PRIMARY KEY (cpr_no),\n"
              + "    FOREIGN KEY (address_id) REFERENCES address (address_id) ON DELETE CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS material\n" + "(\n"
              + "    material_id                SERIAL                                                                                NOT NULL,\n"
              + "    title                      title                                                                                 NOT NULL,\n"
              + "    audience                   VARCHAR(20) CHECK ( audience IN\n"
              + "                                                   ('Voksen', 'Barn', 'Teenager', 'Familie', 'Ældre', 'Studerende')) NOT NULL,\n"
              + "    description_of_the_content VARCHAR(1000),\n"
              + "    publisher                  VARCHAR(40)                                                                           NOT NULL,\n"
              + "    language_                  VARCHAR(7) CHECK ( language_ IN ('Engelsk', 'Dansk', 'Arabisk'))                      NOT NULL,\n"
              + "    release_date               DATE,\n"
              + "    genre                      genre                                                                                 NOT NULL,\n"
              + "    url                        VARCHAR,\n"
              + "    PRIMARY KEY (material_id)\n" + ");\n"
              + "CREATE TABLE IF NOT EXISTS material_keywords\n" + "(\n"
              + "    material_id INT,\n"
              + "    keyword     VARCHAR(50) NOT NULL,\n"
              + "    PRIMARY KEY (material_id, keyword),\n"
              + "    FOREIGN KEY (material_id) REFERENCES material (material_id) ON DELETE CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS material_copy\n"
              + "(\n" + "    material_id SMALLINT,\n"
              + "    copy_no     INT GENERATED BY DEFAULT AS IDENTITY,\n"
              + "    available   BOOLEAN DEFAULT true,\n"
              + "    PRIMARY KEY (material_id, copy_no),\n"
              + "    FOREIGN KEY (material_id) REFERENCES material (material_id) ON DELETE CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS e_book\n" + "(\n"
              + "    material_id id  NOT NULL,\n"
              + "    page_no     INT NOT NULL,\n"
              + "    license_no  INT NOT NULL,\n"
              + "    author      INT NOT NULL,\n"
              + "    PRIMARY KEY (material_id),\n"
              + "    FOREIGN KEY (material_id) REFERENCES material (material_id) ON DELETE CASCADE ,\n"
              + "    FOREIGN KEY (author) REFERENCES material_creator (person_id) ON DELETE CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS book\n" + "(\n"
              + "    material_id id      NOT NULL,\n"
              + "    page_no     INT     NOT NULL,\n"
              + "    author      INT     NOT NULL,\n"
              + "    isbn        VARCHAR NOT NULL,\n" + "    place_id    id,\n"
              + "    PRIMARY KEY (material_id),\n"
              + "    FOREIGN KEY (material_id) REFERENCES material (material_id) ON DELETE CASCADE ,\n"
              + "    FOREIGN KEY (place_id) REFERENCES place (place_id) ON DELETE CASCADE ,\n"
              + "    FOREIGN KEY (author) REFERENCES material_creator (person_id) ON DELETE CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS dvd\n" + "(\n"
              + "    material_id   id          NOT NULL,\n"
              + "    subtitle_lang VARCHAR(10) NOT NULL,\n"
              + "    length_       INT         NOT NULL,\n"
              + "    place_id      id,\n" + "    PRIMARY KEY (material_id),\n"
              + "    FOREIGN KEY (material_id) REFERENCES material (material_id) ON DELETE CASCADE ,\n"
              + "    FOREIGN KEY (place_id) REFERENCES place (place_id) ON DELETE CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS cd\n" + "(\n"
              + "    material_id id      NOT NULL,\n"
              + "    length_     DECIMAL NOT NULL,\n" + "    place_id    id,\n"
              + "    PRIMARY KEY (material_id),\n"
              + "    FOREIGN KEY (material_id) REFERENCES material (material_id) ON DELETE CASCADE ,\n"
              + "    FOREIGN KEY (place_id) REFERENCES place (place_id) ON DELETE CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS audiobook\n" + "(\n"
              + "    material_id id       NOT NULL,\n"
              + "    length_     SMALLINT NOT NULL,\n"
              + "    author      INT,\n" + "    PRIMARY KEY (material_id),\n"
              + "    FOREIGN KEY (material_id) REFERENCES material (material_id) ON DELETE CASCADE ,\n"
              + "    FOREIGN KEY (author) REFERENCES material_creator (person_id) ON DELETE CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS loan\n" + "(\n"
              + "    loan_no     SERIAL NOT NULL,\n"
              + "    loan_date   DATE   NOT NULL,\n"
              + "    deadline    DATE   NOT NULL,\n" + "    return_date DATE,\n"
              + "    cpr_no      cpr,\n" + "    material_id SMALLINT,\n"
              + "    copy_no     SMALLINT,\n"
              + "    number_of_extensions SMALLINT check (number_of_extensions < 3) DEFAULT 0,\n"
              + "    PRIMARY KEY (loan_no),\n"
              + "    FOREIGN KEY (cpr_no) REFERENCES borrower (cpr_no) ON DELETE CASCADE ,\n"
              + "    FOREIGN KEY (material_id, copy_no) REFERENCES material_copy (material_id, copy_no) ON DELETE CASCADE\n"
              + ");\n" + "\n" + "CREATE TABLE IF NOT EXISTS reservation\n"
              + "(\n" + "    reservation_date DATE   NOT NULL,\n"
              + "    reservation_id   SERIAL NOT NULL,\n"
              + "    cpr_no           cpr,\n"
              + "    material_id      SMALLINT,\n"
              + "    ready            BOOLEAN,\n"
              + "    PRIMARY KEY (reservation_id),\n"
              + "    FOREIGN KEY (cpr_no) REFERENCES borrower (cpr_no) ON DELETE CASCADE ,\n"
              + "    FOREIGN KEY (material_id) REFERENCES material (material_id) ON DELETE CASCADE\n"
              + ");\n" + "\n");
      ddl.executeUpdate();

      PreparedStatement sql = connection.prepareStatement(
          "SET SCHEMA 'librarysystem';\n" + "\n"
              + "INSERT INTO material (title, audience, description_of_the_content, publisher, language_,\n"
              + "                      release_date, genre, url)\n"
              + "VALUES ('Title1', 'Voksen', 'HELLO DESC', 'Publisher1', 'Dansk', '2020-12-12', 'Fantasy', NULL);\n"
              + "INSERT INTO material (title, audience, description_of_the_content, publisher, language_,\n"
              + "                      release_date, genre, url)\n"
              + "VALUES ('Title2', 'Voksen', 'HELLO DESC', 'Publisher2', 'Dansk', '2020-12-12', 'Fantasy', NULL);\n"
              + "\n"
              + "INSERT INTO place (place_id, hall_no, department, creator_l_name, genre)\n"
              + "VALUES (1, 1, 'M', 'Bob', 'Fantasy');\n" + "\n"
              + "INSERT INTO material_creator (person_id, f_name, l_name, dob, country)\n"
              + "VALUES (1, 'Bob', 'Bobsen', '2020-12-12', 'Denmark');\n" + "\n"
              + "INSERT INTO book (material_id, page_no, author, isbn, place_id)\n"
              + "VALUES (1, 200, 1, '321432432', 1);\n" + "\n"
              + "INSERT INTO material_copy (material_id, copy_no)\n"
              + "VALUES (1, 1);\n" + "\n"
              + "INSERT INTO dvd (material_id, subtitle_lang, length_, place_id)\n"
              + "VALUES (2, 'Dansk', 120, 1);\n"
              + "INSERT INTO material_copy (material_id, copy_no)\n"
              + "VALUES (2, 1);\n" + "\n"
              + "INSERT INTO address(address_id, city, zip_code, street_name, street_no)\n"
              + "VALUES (1, 'Horsens', 8700, 'Axelborg', '8');\n" + "\n"
              + "INSERT INTO borrower (cpr_no, f_name, l_name, email, tel_no, address_id, password)\n"
              + "VALUES ('111111-1111', 'Michael', 'Bui', 'michael@gmail.com', '+4512345678', 1, 'password');\n"
              + "\n"
              + "INSERT INTO borrower (cpr_no, f_name, l_name, email, tel_no, address_id, password)\n"
              + "VALUES ('222222-2222', 'B2', 'B2L', 'TEST@gmail.com', '+4587654321', 1, 'password');\n"
              + "INSERT INTO material (title, audience, description_of_the_content, publisher, language_, release_date, genre, url)\n"
              + "VALUES ('eboktest', 'Barn', 'test', 'me', 'Dansk', '2005-01-01', 'love', NULL);\n"
              + "\n"
              + "INSERT INTO e_book (material_id, page_no, license_no, author)\n"
              + "VALUES (3, 500, 12345, 1);\n" + "\n"
              + "INSERT INTO material_copy (material_id, available)\n"
              + "VALUES (3, TRUE);\n" + "\n"
              + "INSERT INTO material_keywords (material_id, keyword)\n"
              + "VALUES (1, 'Magi');\n"
              + "INSERT INTO material_keywords (material_id, keyword)\n"
              + "VALUES (1, 'Fantasy');\n"
              + "INSERT INTO material_keywords (material_id, keyword)\n"
              + "VALUES (2, 'Troldmænd');\n"
              + "INSERT INTO material_keywords (material_id, keyword)\n"
              + "VALUES (2, 'Trold');\n"
              + "INSERT INTO material_keywords (material_id, keyword)\n"
              + "VALUES (3, 'Fred');\n"
              + "INSERT INTO material_keywords (material_id, keyword)\n"
              + "VALUES (3, 'Ro');"

      );
      sql.executeUpdate();
      connection.commit();
    }
  }

}
