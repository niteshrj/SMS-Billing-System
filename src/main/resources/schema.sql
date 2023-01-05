DROP TABLE IF EXISTS Customer;

DROP TABLE IF EXISTS Message;

CREATE TABLE Customer (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          plan ENUM('BASIC', 'SILVER', 'GOLD') NOT NULL
);



CREATE TABLE Message  (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             customer_id INT NOT NULL,
                             text VARCHAR(100) NOT NULL,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);