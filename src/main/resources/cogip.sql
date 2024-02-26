CREATE TABLE IF NOT EXISTS company (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  country VARCHAR(255) NOT NULL,
  vat VARCHAR(255) UNIQUE NOT NULL,
  type ENUM('provider', 'client') NOT NULL,
  timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE IF NOT EXISTS contact (
  id INT AUTO_INCREMENT PRIMARY KEY,
  firstname VARCHAR(255) NOT NULL,
  lastname VARCHAR(255) NOT NULL,
  phone VARCHAR(15) NOT NULL,
  email VARCHAR(255) NOT NULL,
  timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  contact_company_id INT,
  FOREIGN KEY (contact_company_id) REFERENCES company(id)
);


CREATE TABLE IF NOT EXISTS `user` (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  role ENUM('admin', 'accountant', 'intern') NOT NULL
);


CREATE TABLE IF NOT EXISTS invoice (
  id INT AUTO_INCREMENT PRIMARY KEY,
  invoice_company_id INT,
  invoice_contact_id INT,
  invoice_number VARCHAR(255) NOT NULL,
  value DECIMAL(8,2) NOT NULL,
  currency ENUM('EUR', 'USD') NOT NULL,
  type ENUM('incoming', 'outgoing') NOT NULL,
  status ENUM('open', 'paid') NOT NULL,
  timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (invoice_company_id) REFERENCES company(id),
  FOREIGN KEY (invoice_contact_id) REFERENCES contact(id)
);