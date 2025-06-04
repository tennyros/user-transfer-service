--changeset vadim:1
--comment: Insert test user with account, email and phone

-- Insert user
INSERT INTO users (name, password, date_of_birth)
VALUES ('Test User', '$2a$12$662ZEQpD5iDD190Qk6IkzuYElDxUk2IZ9tD8y8pmJrRKEFfMbjQe6', '1993-05-01');

-- Insert account (user_id = last inserted user)
INSERT INTO accounts (balance, user_id)
SELECT 1000.00, id FROM users WHERE name = 'Test User';

-- Insert email
INSERT INTO email_data (email, user_id)
SELECT 'test@example.com', id FROM users WHERE name = 'Test User';

-- Insert phone
INSERT INTO phone_data (phone, user_id)
SELECT '+79001234567', id FROM users WHERE name = 'Test User';
