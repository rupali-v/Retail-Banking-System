CREATE DATABASE IF NOT EXISTS retail_banking;

USE retail_banking;

-- ===========================
-- Accounts Table
-- ===========================

CREATE TABLE accounts (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    account_holder_name VARCHAR(100) NOT NULL,
    account_number VARCHAR(20) UNIQUE NOT NULL,
    account_type VARCHAR(30),
    balance DECIMAL(10,2),
    created_at DATETIME,
    status VARCHAR(20)
);

INSERT INTO accounts
(account_holder_name, account_number, account_type, balance, created_at, status)
VALUES
('Rupali k', 'ACC1004', 'CURRENT', 0.00, '2026-06-28 22:18:43', 'ACTIVE'),
('Rupali', 'ACC1005', 'SAVINGS', 10500.00, '2026-07-01 10:47:12', 'ACTIVE'),
('Rupali karmore', 'ACC1006', 'SAVINGS', 19500.00, '2026-07-01 10:50:10', 'ACTIVE');


-- ===========================
-- Transactions Table
-- ===========================

CREATE TABLE transactions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    amount DECIMAL(10,2),
    from_account VARCHAR(20),
    status VARCHAR(20),
    to_account VARCHAR(20),
    transaction_date DATETIME,
    transaction_type VARCHAR(50)
);

INSERT INTO transactions
(amount, from_account, status, to_account, transaction_date, transaction_type)
VALUES
(5000.00, 'ACC1001', 'SUCCESS', 'ACC1002', '2026-06-28 23:19:05', 'FUND_TRANSFER'),
(5000.00, 'ACC1004', 'SUCCESS', 'ACC1002', '2026-06-30 20:11:01', 'FUND_TRANSFER'),
(1000.00, 'ACC1004', 'SUCCESS', 'ACC1002', '2026-06-30 22:07:51', 'FUND_TRANSFER'),
(500.00, 'ACC1006', 'SUCCESS', 'ACC1005', '2026-07-01 11:03:02', 'FUND_TRANSFER');