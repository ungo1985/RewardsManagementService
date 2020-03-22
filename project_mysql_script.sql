CREATE DATABASE IF NOT EXISTS rms;
DROP TABLE IF exists rms.customer_info;
DROP TABLE IF exists rms.items;
DROP TABLE IF exists rms.purchased_items;

CREATE TABLE IF NOT EXISTS rms.customer_info(
   customer_id VARCHAR(10) PRIMARY KEY NOT NULL,
   first_name VARCHAR(40),
   last_name VARCHAR(255),
   street_address VARCHAR(255),
   city VARCHAR(255),
   state VARCHAR(2),
   zip_code VARCHAR(5),
   birthday DATE,
   points INT
);

INSERT INTO rms.customer_info VALUES('EXA6777', 'Eric', 'Acevedo', '8888 VININGS VINTAGE WAY', 'ATLANTA', 'GA', '30080', '1999-02-19', 100);
INSERT INTO rms.customer_info VALUES('PXN6778', 'Phuong', 'Nguyen', '9999 ACRES MILL ROAD', 'ATLANTA', 'GA', '30080', '1992-03-20', 1000);
INSERT INTO rms.customer_info VALUES('HXC6779', 'Himaja', 'Cherukuri', '2222 ROSWELL GREEN LANE', 'ROSWELL', 'GA', '30081', '1991-07-04', 500);
INSERT INTO rms.customer_info VALUES('MXM6772', 'Malik', 'McMullen', '3232 MABLETON PARKWAY', 'MABLETON', 'GA', '30040', '1988-05-22', 100);
INSERT INTO rms.customer_info VALUES('SXM6773', 'Surya', 'Malladi', '3892 AUSTELL ROAD', 'AUSTELL', 'GA', '30081', '1990-06-13', 200);
INSERT INTO rms.customer_info VALUES('PXX6774', 'Prachi', 'Prachi', '3844 CUMBERLAND PARKWAY', 'SMYRNA', 'GA', '30049', '1990-12-13', 800);

CREATE TABLE IF NOT EXISTS rms.items(
   item_id INT AUTO_INCREMENT PRIMARY KEY,
   available_item VARCHAR(40) NOT NULL,
   price FLOAT NOT NULL,
   type VARCHAR(50) NOT NULL
);

INSERT INTO rms.items VALUES (1, 'CHOCOLATE', 2.50, 'ICE CREAM');
INSERT INTO rms.items VALUES (2, 'VANILLA', 1.50, 'ICE CREAM');
INSERT INTO rms.items VALUES (3, 'STRAWBERRY', 3.0, 'ICE CREAM');
INSERT INTO rms.items VALUES (4, 'VANILLA', 3.50, 'YOGURT');
INSERT INTO rms.items VALUES (5, 'PEACH', 4.00, 'YOGURT');

CREATE TABLE IF NOT EXISTS rms.purchased_items(
   purchased_id INT AUTO_INCREMENT PRIMARY KEY,
   customer_id VARCHAR(10) NOT NULL,
   item_id INT NOT NULL, 
   purchased_date DATE,
   pre_ordered_flag VARCHAR(1)
);

INSERT INTO rms.purchased_items VALUES (1, 'EXA6777', 1, CURDATE(), 'N');
INSERT INTO rms.purchased_items VALUES (2, 'EXA6777', 2, '2020-01-20', 'Y');
INSERT INTO rms.purchased_items VALUES (3, 'EXA6777', 3, '2020-02-18', 'N');
INSERT INTO rms.purchased_items VALUES (4, 'EXA6777', 4, '2020-01-30', 'Y');
INSERT INTO rms.purchased_items VALUES (5, 'PXN6778', 1, '2020-02-03', 'N');
INSERT INTO rms.purchased_items VALUES (6, 'PXN6778', 2, CURDATE(), 'Y');
INSERT INTO rms.purchased_items VALUES (7, 'PXN6778', 3, '2020-02-18', 'N');
INSERT INTO rms.purchased_items VALUES (8, 'PXN6778', 4, '2020-01-30', 'Y');
INSERT INTO rms.purchased_items VALUES (9, 'HXC6779', 1, '2020-01-20', 'N');
INSERT INTO rms.purchased_items VALUES (10, 'HXC6779', 2, '2020-01-20', 'Y');
INSERT INTO rms.purchased_items VALUES (11, 'HXC6779', 3, CURDATE(), 'N');
INSERT INTO rms.purchased_items VALUES (12, 'HXC6779', 4, '2020-01-30', 'Y');
INSERT INTO rms.purchased_items VALUES (13, 'MXM6772', 1, CURDATE(), 'N');
INSERT INTO rms.purchased_items VALUES (14, 'MXM6772', 2, '2020-01-20', 'Y');
INSERT INTO rms.purchased_items VALUES (15, 'MXM6772', 3, '2020-02-18', 'N');
INSERT INTO rms.purchased_items VALUES (16, 'MXM6772', 4, '2020-01-30', 'Y');
INSERT INTO rms.purchased_items VALUES (17, 'SXM6773', 1, CURDATE(), 'N');
INSERT INTO rms.purchased_items VALUES (18, 'SXM6773', 2, '2020-01-20', 'Y');
INSERT INTO rms.purchased_items VALUES (19, 'SXM6773', 3, '2020-02-18', 'N');
INSERT INTO rms.purchased_items VALUES (20, 'SXM6773', 4, '2020-01-30', 'Y');
INSERT INTO rms.purchased_items VALUES (21, 'PXX6774', 1, CURDATE(), 'N');
INSERT INTO rms.purchased_items VALUES (22, 'PXX6774', 2, '2020-01-20', 'Y');
INSERT INTO rms.purchased_items VALUES (23, 'PXX6774', 3, '2020-02-18', 'N');
INSERT INTO rms.purchased_items VALUES (24, 'PXX6774', 4, '2020-01-30', 'Y');
INSERT INTO rms.purchased_items VALUES (25, 'EXA6777', 1, CURDATE(), 'N');
INSERT INTO rms.purchased_items VALUES (26, 'EXA6777', 2, CURDATE(), 'Y');
INSERT INTO rms.purchased_items VALUES (27, 'EXA6777', 3, CURDATE(), 'N');
INSERT INTO rms.purchased_items VALUES (28, 'EXA6777', 4, CURDATE(), 'Y');
INSERT INTO rms.purchased_items VALUES (29, 'EXA6777', 4, CURDATE(), 'Y');
INSERT INTO rms.purchased_items VALUES (30, 'EXA6777', 1, CURDATE(), 'N');
INSERT INTO rms.purchased_items VALUES (31, 'EXA6777', 2, CURDATE(), 'Y');
INSERT INTO rms.purchased_items VALUES (32, 'EXA6777', 3, CURDATE(), 'N');
INSERT INTO rms.purchased_items VALUES (33, 'EXA6777', 4, CURDATE(), 'Y');
INSERT INTO rms.purchased_items VALUES (34, 'EXA6777', 4, CURDATE(), 'Y');
INSERT INTO rms.purchased_items VALUES (35, 'EXA6777', 1, CURDATE(), 'N');
INSERT INTO rms.purchased_items VALUES (36, 'EXA6777', 2, CURDATE(), 'Y');
INSERT INTO rms.purchased_items VALUES (37, 'EXA6777', 3, CURDATE(), 'N');
INSERT INTO rms.purchased_items VALUES (38, 'EXA6777', 4, CURDATE(), 'Y');
INSERT INTO rms.purchased_items VALUES (39, 'EXA6777', 4, CURDATE(), 'Y');
INSERT INTO rms.purchased_items VALUES (40, 'EXA6777', 1, CURDATE(), 'N');
INSERT INTO rms.purchased_items VALUES (41, 'EXA6777', 2, CURDATE(), 'Y');
INSERT INTO rms.purchased_items VALUES (42, 'EXA6777', 3, CURDATE(), 'N');
INSERT INTO rms.purchased_items VALUES (43, 'EXA6777', 4, CURDATE(), 'Y');
INSERT INTO rms.purchased_items VALUES (44, 'EXA6777', 4, CURDATE(), 'Y');
INSERT INTO rms.purchased_items VALUES (45, 'EXA6777', 1, CURDATE(), 'N');
INSERT INTO rms.purchased_items VALUES (46, 'EXA6777', 2, CURDATE(), 'Y');
INSERT INTO rms.purchased_items VALUES (47, 'EXA6777', 3, CURDATE(), 'N');
INSERT INTO rms.purchased_items VALUES (48, 'EXA6777', 4, CURDATE(), 'Y');
INSERT INTO rms.purchased_items VALUES (49, 'EXA6777', 4, CURDATE(), 'Y');
INSERT INTO rms.purchased_items VALUES (50, 'EXA6777', 1, CURDATE(), 'N');
INSERT INTO rms.purchased_items VALUES (51, 'EXA6777', 2, CURDATE(), 'Y');
INSERT INTO rms.purchased_items VALUES (52, 'EXA6777', 3, CURDATE(), 'N');
INSERT INTO rms.purchased_items VALUES (53, 'EXA6777', 4, CURDATE(), 'Y');
INSERT INTO rms.purchased_items VALUES (54, 'EXA6777', 4, CURDATE(), 'Y');
INSERT INTO rms.purchased_items VALUES (55, 'EXA6777', 1, CURDATE(), 'N');
INSERT INTO rms.purchased_items VALUES (56, 'EXA6777', 2, CURDATE(), 'Y');
INSERT INTO rms.purchased_items VALUES (57, 'EXA6777', 3, CURDATE(), 'N');
INSERT INTO rms.purchased_items VALUES (58, 'EXA6777', 4, CURDATE(), 'Y');
INSERT INTO rms.purchased_items VALUES (59, 'EXA6777', 4, CURDATE(), 'Y');