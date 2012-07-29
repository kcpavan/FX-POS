create database storedb;

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

drop table if exists storedb.invoice;
create table storedb.invoice
(invoice_id_pk integer primary key not null auto_increment,
invoice_number integer not null,
invoice_distibutor_id_fk integer not null,
invoice_received_date timestamp not null
);


drop table if exists storedb.invoice_details;
create table storedb.invoice_details
(invoicedetails_id_pk integer primary key not null auto_increment,
invoice_id_fk integer not null,
invoice_distibutor_id_fk integer not null,
invoice_received_date timestamp not null
);