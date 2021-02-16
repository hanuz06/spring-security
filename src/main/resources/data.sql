INSERT INTO users (user_name, password, active) VALUES ('admin', 'admin', 1);
SET @admin_id = LAST_INSERT_ID();
INSERT INTO roles (role) VALUES ('ROLE_USER');
SET @user_role_id = LAST_INSERT_ID();
INSERT INTO users_roles (user_id, role_id) VALUES (@admin_id, @user_role_id);
INSERT INTO roles (role) VALUES ('ROLE_ADMIN');
SET @admin_role_id = LAST_INSERT_ID();
INSERT INTO users_roles (user_id, role_id) VALUES (@admin_id, @admin_role_id);



INSERT INTO users (user_name, password, active) VALUES ('user', 'user', 1);
SET @user_id = LAST_INSERT_ID();
INSERT INTO users_roles (user_id, role_id) VALUES (@user_id, @user_role_id);