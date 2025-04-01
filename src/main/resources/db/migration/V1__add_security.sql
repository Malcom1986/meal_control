CREATE SEQUENCE  IF NOT EXISTS roles_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE roles (
  id BIGINT NOT NULL,
   role_name VARCHAR(255),
   CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE user_role (
  role_id BIGINT NOT NULL,
   user_id BIGINT NOT NULL,
   CONSTRAINT pk_user_role PRIMARY KEY (role_id, user_id)
);

ALTER TABLE users ADD password_digest VARCHAR(255);

ALTER TABLE users ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE user_role ADD CONSTRAINT fk_user_role_on_role FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE user_role ADD CONSTRAINT fk_user_role_on_user FOREIGN KEY (user_id) REFERENCES users (id);