
-- SEQUENCE
--------------------------------------------------------
CREATE SEQUENCE seq_flower;
CREATE SEQUENCE seq_ordCust;
CREATE SEQUENCE seq_OrderItem;
CREATE SEQUENCE seq_user;
CREATE SEQUENCE seq_userRole;

-- -----------------------------------------------------
-- 1. Table `Flower`
-- -----------------------------------------------------
CREATE TABLE Flower (
  flower_id INT PRIMARY KEY,
  name VARCHAR(45) NOT NULL,
  price DECIMAL(30) NOT NULL,
  count INT NOT NULL,
  );

-- -----------------------------------------------------
-- 2. Table `UserRole`
-- -----------------------------------------------------

CREATE TABLE UserRole (
  userRole_id INT PRIMARY KEY,
  type VARCHAR(45) NOT NULL,
  );

-- -----------------------------------------------------
-- 3. Table `User`
-- -----------------------------------------------------
  CREATE TABLE Users (
    user_id INT NOT NULL PRIMARY KEY,
    firstName VARCHAR(45) NOT NULL,
    lastName VARCHAR(45) NOT NULL,
    address VARCHAR(60) NOT NULL,
    money DECIMAL(30) NOT NULL,
    discount DECIMAL NOT NULL,
    tel VARCHAR(45) NOT NULL,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    userRole_id INT NOT NULL ,
    CONSTRAINT "FK_USERROL" FOREIGN KEY (userRole_id) REFERENCES UserRole (userRole_id),
    );



--	CONSTRAINT "EMPLOYEE_PROJECT_MAP_EMPL_FK1" FOREIGN KEY ("ID") REFERENCES "EMPLOYEE" ("ID"),
--	CONSTRAINT "EMPLOYEE_PROJECT_MAP_PROJ_FK1" FOREIGN KEY ("ID") REFERENCES "PROJECT" ("ID")


-- -----------------------------------------------------
-- 4. Table `OrderCustomer`
-- -----------------------------------------------------
    CREATE TABLE OrderCustomer (
      orderCustomer_id INT NOT NULL PRIMARY KEY,
      user_id INT NOT NULL,
      sum DECIMAL(30) NOT NULL,
      dateOrder DATE NOT NULL,
      status VARCHAR(45) NOT NULL,
      CONSTRAINT "FK_USERS" FOREIGN KEY (user_id) REFERENCES Users (user_id),
     );

-- -----------------------------------------------------
-- 5. Table `OrderItem`
-- -----------------------------------------------------
CREATE TABLE OrderItem (
      orderItem_id INT NULL PRIMARY KEY,
      flower_id INT NOT NULL,
      orderCustomer_id INT NOT NULL,
      count INT NOT NULL,
      CONSTRAINT "FK_Flower" FOREIGN KEY (flower_id) REFERENCES Flower (flower_id),
      CONSTRAINT "FK_OrderCustomer" FOREIGN KEY (orderCustomer_id) REFERENCES OrderCustomer (orderCustomer_id),
      );

-- -----------------------------------------------------
-- INSERT
-- -----------------------------------------------------

--Insert into UserRole (userType_id , type) values (1, 'admin');
--Insert into UserRole (userType_id , type) values (2, 'user');


-- -------------------------Flower
Insert into Flower (flower_id , name , price, count) values (seq_flower.NEXTVAL, 'Rose', '120', 30);
Insert into Flower (flower_id , name , price, count) values (seq_flower.NEXTVAL, 'Astra', '80', 40);
Insert into Flower (flower_id , name , price, count) values (seq_flower.NEXTVAL, 'Lily', '55', 44);
Insert into Flower (flower_id , name , price, count) values (seq_flower.NEXTVAL, 'Narcissus', '35.4', 32);
Insert into Flower (flower_id , name , price, count) values (seq_flower.NEXTVAL, 'Orchid', '357', 47);
Insert into Flower (flower_id , name , price, count) values (seq_flower.NEXTVAL, 'Violet', '456', 5);
Insert into Flower (flower_id , name , price, count) values (seq_flower.NEXTVAL, 'Pansy', '1337', 1337);




-- -------------------------UserRole
Insert into UserRole (userRole_id , type) values (seq_userRole.NEXTVAL, 'admin');
Insert into UserRole (userRole_id , type) values (seq_userRole.NEXTVAL, 'user');



-- -------------------------Users
Insert into Users (user_id , firstName, lastName, address, money, discount, tel, username, password, email, userRole_id)
       values (seq_user.NEXTVAL, 'Admin', 'Admin', 'Admin Home', 0, 0, ' ', 'admin', 'admin123', 'admin@email.com', 1);
Insert into Users (user_id , firstName, lastName, address, money, discount, tel, username, password, email, userRole_id)
       values (seq_user.NEXTVAL, 'lalala', 'lalalla', 'lalala', 2000, 3, '333-444-5555', 'lalala', 'lalala', 'lalala@email.com', 2);
Insert into Users (user_id , firstName, lastName, address, money, discount, tel, username, password, email, userRole_id)
        values (seq_user.NEXTVAL, 'papapa', 'lalalapa', 'lalala', 2000, 40, '333-444-555', 'papapa', 'papapa', 'papapa@mail.com', 2);
