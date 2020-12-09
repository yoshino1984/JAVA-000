create database ss_test00;
create database ss_test01;
create table ss_test01.my_order_0
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test00.my_order_0
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test00.my_order_1
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test01.my_order_1
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test01.my_order_10
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test00.my_order_10
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test00.my_order_11
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test01.my_order_11
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test01.my_order_12
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test00.my_order_12
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test01.my_order_13
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test00.my_order_13
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test01.my_order_14
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test00.my_order_14
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test01.my_order_15
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test00.my_order_15
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test00.my_order_2
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test01.my_order_2
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test00.my_order_3
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test01.my_order_3
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test01.my_order_4
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test00.my_order_4
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test00.my_order_5
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test01.my_order_5
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test00.my_order_6
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test01.my_order_6
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test01.my_order_7
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test00.my_order_7
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test01.my_order_8
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test00.my_order_8
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test00.my_order_9
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;

create table ss_test01.my_order_9
(
	id bigint auto_increment
		primary key,
	order_no varchar(32) not null comment '订单编码',
	shop_id bigint default '0' not null comment '店铺id',
	user_id bigint default '0' not null comment '购买者id',
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
	address varchar(128) null comment '详细地址',
	phone varchar(20) null comment '联系方式',
	money varchar(64) null comment '订单总金额',
	remark varchar(128) charset utf8 null comment '备注',
	status varchar(64) null comment '订单状态',
	is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
	constraint uk_my_order_no
		unique (order_no)
)
comment '订单表'
;
