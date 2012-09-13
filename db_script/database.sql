set sql_safe_updates=0;

drop database  if exists storedb;
create database storedb;

drop table if exists storedb.user_group;
create table storedb.user_group
(group_id_pk integer primary key not null auto_increment,
group_name varchar(20) not null);

INSERT INTO storedb.user_group
(group_id_pk,group_name)
values(1,'admin');

drop table if exists storedb.mainuser;
create table storedb.mainuser
(mainuser_id_pk integer primary key not null auto_increment,
mainuser_firstname varchar(50) not null,
mainuser_lastname varchar(50) not null,
mainuser_username varchar(20) not null,
mainuser_password varchar(20) not null,
mainuser_state int not null,
mainuser_group_id_fk int not null,
CONSTRAINT fk_mainuser_group_id
FOREIGN KEY(`mainuser_group_id_fk`) 
REFERENCES `user_group`(`group_id_pk`));

INSERT INTO storedb.mainuser
(mainuser_firstname,mainuser_lastname,mainuser_username,mainuser_password,
mainuser_state,mainuser_group_id_fk)
values('prakash','b','kt','kt',1,1);



drop table if exists storedb.item_category;
create table storedb.item_category
(category_id_pk integer primary key not null auto_increment,
category_name varchar(250) not null);


drop table if exists storedb.items;
create table storedb.items 
(item_id_pk integer primary key not null auto_increment,
item_name varchar(250) not null,
item_barcode varchar(250) not null,
item_category_id_fk int null,
item_mrp double not null,
item_weight double not null,
item_weight_unit varchar(50) not null,
item_actual_price double not null,
item_selling_price double not null,
item_hasfree boolean not null,
item_modified_user_id_fk int not null,
item_modified_date timestamp,
CONSTRAINT fk_item_category_id
	FOREIGN KEY(`item_category_id_fk`) 
	REFERENCES `item_category`(`category_id_pk`),
CONSTRAINT fk_item_modified_user
	FOREIGN KEY(`item_modified_user_id_fk`) 
	REFERENCES `mainuser`(`mainuser_id_pk`)
);



drop table if exists storedb.stocks;
create table storedb.stocks
(stock_id_pk integer primary key not null auto_increment,
stock_item_id_fk integer not null,
stock_quantity bigint not null,
stock_quantity_unit varchar(50) not null,
stock_modified_user_id_fk int not null,
stock_modified_date timestamp,
CONSTRAINT fk_stocks_item_id
	FOREIGN KEY(`stock_item_id_fk`) 
	REFERENCES `items`(`item_id_pk`),
CONSTRAINT fk_stock_modified_user
	FOREIGN KEY(`stock_modified_user_id_fk`) 
	REFERENCES `mainuser`(`mainuser_id_pk`)
);

drop table if exists storedb.distributor;
create table storedb.distributor
(distributor_id_pk integer primary key not null auto_increment,
distributor_name integer not null,
distributor_address varchar(100) not null,
distributor_phone_number integer(12) not null,
distributor_modified_user_id_fk int not null,
distributor_modified_date timestamp,
CONSTRAINT fk_distributor_modified_user
	FOREIGN KEY(`distributor_modified_user_id_fk`) 
	REFERENCES `mainuser`(`mainuser_id_pk`)
);


drop table if exists storedb.purchase;
create table storedb.purchase
(purchase_id_pk integer primary key not null auto_increment,
purchase_number integer not null,
purchase_distibutor_id_fk integer not null,
purchase_received_date timestamp not null,
purchase_modified_user_id_fk int not null,
purchase_modified_date timestamp,
CONSTRAINT fk_purchase_modified_user
	FOREIGN KEY(`purchase_modified_user_id_fk`) 
	REFERENCES `mainuser`(`mainuser_id_pk`),
CONSTRAINT fk_purchase_distributor_id
	FOREIGN KEY(`purchase_distibutor_id_fk`) 
	REFERENCES `distributor`(`distributor_id_pk`)
);



drop table if exists storedb.purchase_details;
create table storedb.purchase_details
(purchase_details_id_pk integer primary key not null auto_increment,
purchase_item_id_fk integer not null,
purchase_item_quantity integer not null,
purchase_free_item integer null,
purchase_id_fk integer not null,
CONSTRAINT fk_purchase_det_item_id
	FOREIGN KEY(`purchase_item_id_fk`) 
	REFERENCES `items`(`item_id_pk`),
CONSTRAINT fk_purchase_id
	FOREIGN KEY(`purchase_id_fk`) 
	REFERENCES `purchase`(`purchase_id_pk`)
);


drop table if exists storedb.invoice;
create table storedb.invoice
(invoice_id_pk integer primary key not null auto_increment,
invoice_total_items integer not null,
invoice_total_amount double not null,
invoice_modified_user_id_fk int not null,
invoice_modified_date timestamp,
CONSTRAINT fk_invoice_modified_user
	FOREIGN KEY(`invoice_modified_user_id_fk`) 
	REFERENCES `mainuser`(`mainuser_id_pk`),
CONSTRAINT fk_invoice_user_id
	FOREIGN KEY(`invoice_modified_user_id_fk`) 
	REFERENCES `mainuser`(`mainuser_id_pk`)
);

drop table if exists storedb.invoice_details;
create table storedb.invoice_details
(invoice_det_id_pk integer primary key not null auto_increment,
invoice_det_invoice_id_fk integer not null,
invoice_det_item_id_fk integer not null,
invoice_det_quantity double not null,
invoice_det_total double not null,
CONSTRAINT fk_invoicedet_invoice_id
	FOREIGN KEY(`invoice_det_invoice_id_fk`) 
	REFERENCES `invoice`(`invoice_id_pk`),
CONSTRAINT fk_invoice_item_id
	FOREIGN KEY(`invoice_det_item_id_fk`) 
	REFERENCES `items`(`item_id_pk`)
);