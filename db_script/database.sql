create database storedb;


drop table if exists storedb.mainuser;
create table storedb.user
(mainuser_id_pk integer primary key not null auto_increment,
mainuser_username string not null,
mainuser_firstname timestamp not null,
mainuser_lastname double not null,
mainuser_state int not null,
mainuser_role_id_fk int not null
);


drop table if exist storedb.user_group
create table storedb.user_group
(group_id_pk integer primary key not null auto_increment,
group_name string not null);

drop table if exists storedb.items;
create table storedb.items 
(item_id_pk integer primary key not null auto_increment,
item_name varchar(250) not null,
item_barcode varchar(250) not null,
item_mrp double not null,
item_weight double not null,
item_weight_unit varchar(50) not null,
item_actual_price double not null,
item_selling_price double not null,
item_hasfree boolean not null
);

drop table if exists storedb.stocks;
create table storedb.stocks
(stock_id_pk integer primary key not null auto_increment,
stock_item_id_fk integer not null,
stock_quantity bigint not null,
stock_quantity_unit varchar(50) not null
);

drop table if exists storedb.purchase;
create table storedb.purchase
(purchase_id_pk integer primary key not null auto_increment,
purchase_number integer not null,
purchase_distibutor_id_fk integer not null,
purchase_received_date timestamp not null
);


drop table if exists storedb.purchase_details;
create table storedb.purchase_details
(purchase_details_id_pk integer primary key not null auto_increment,
purchase_id_fk integer not null,
purchase_distibutor_id_fk integer not null,
purchase_received_date timestamp not null
);


drop table if exists storedb.invoice;
create table storedb.invoice
(invoice_id_pk integer primary key not null auto_increment,
invoice_user_id_fk integer not null,
invoice_date timestamp not null,
invoice_total double not null
);


drop table if exists storedb.invoice_details;
create table storedb.invoice_details
(invoice_details_id_pk integer primary key not null auto_increment,
invoice_id_fk integer not null,
item_id_fk integer not null,
invoice_details_quantity double not null,
invoice_details_total double not null
);


