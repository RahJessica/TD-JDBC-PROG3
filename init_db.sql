create role product_manager_user login password '123456';
create database product_management_db;

-- donner accès à l'user de se connecter à la base de données
grant connect on database product_management_db to product_manager_user;

-- donner l'accès au schéma, sinon accès aux tables refusé
grant usage on schema public to product_manager_user;

-- permettre la creation de table
alter database product_management_db owner TO product_manager_user;

-- privilèges sur les séquences
grandt usage, select on all sequences in schema public to product_manager_user;