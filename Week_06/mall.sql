create table my_user
(
	id bigint auto_increment primary key,
  account varchar(64) not null comment '账号',
  password varchar(64) not null comment '密码',
  alias varchar(32) not null comment '昵称',
  phone varchar(32) null comment '联系方式',
  is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
  constraint uk_my_order_account unique (account)
) comment '用户' charset=utf8mb4;

create table my_user_address
(
	id bigint auto_increment primary key,
	country_id bigint null comment '国家id',
	province_id bigint null comment '省份id',
	city_id bigint null comment '城市id',
	district_id bigint null comment '区id',
	street_id bigint null comment '街道id',
  address varchar(128) null comment '详细地址',
	user_id bigint not null default 0 comment '用户id',
  is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
  index idx_user_id(user_id)
) comment '用户地址表' charset=utf8mb4;

create table my_order
(
	id bigint auto_increment primary key,
  order_no varchar(32) not null comment '订单编码',
	shop_id bigint not null default 0 comment '店铺id',
	user_id bigint not null default 0 comment '购买者id',
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
  constraint uk_my_order_no unique (order_no)
) comment '订单表' charset=utf8mb4;

create table my_goods
(
	id bigint auto_increment primary key,
  goods_no varchar(32) not null comment '商品编码',
  goods_name varchar(128) not null comment '商品名称',
  goods_desc varchar(128) not null comment '商品描述',
  doc varchar(128) not null comment '商品图片',
  ver int not null comment '版本号',
	status varchar(32) null comment '状态',
	shop_id bigint not null default 0 comment '店铺id',
  is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
  constraint uk_my_goods_no unique (goods_no)
) comment '商品spu表' charset=utf8mb4;

create table my_goods_sku
(
	id bigint auto_increment primary key,
  sku_name varchar(128) not null comment '商品名称',
  sku_desc varchar(128) not null comment '商品描述',
  doc varchar(128) not null comment '商品图片',
  money decimal(12,3) not null comment '商品单价',
  stock_num int not null comment '库存',
  occupy_stock_num int not null comment '冻结的库存',
  ver int not null comment '版本号',
	status varchar(32) null comment '状态',
	goods_id bigint not null  comment '商品id',
  is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者'
) comment '商品sku表' charset=utf8mb4;


create table my_goods_snapshot
(
	id bigint auto_increment primary key,
	goods_id bigint not null  comment '商品id',
	sku_id bigint not null  comment 'sku的id',
  goods_ver int not null comment '商品版本号',
  sku_ver int not null comment 'sku版本号',
  snapshot json null comment '快照json',
  is_deleted varchar(1) default 'N' null comment '是否删除',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
  constraint uk_snapshot_gsgs unique (goods_id,goods_ver,sku_id,sku_ver)
) comment '商品版本快照表' charset=utf8mb4;


create table my_order_goods
(
	id bigint auto_increment primary key,
	order_id bigint not null  comment '商品id',
	goods_id bigint not null  comment '商品id',
	sku_id bigint not null  comment '商品id',
	num int not null  comment '购买数量',
	price decimal(12,3) not null  comment '购买单价',
	discount_money decimal(12,3) not null  comment '优惠价格',
	total_money decimal(12,3) not null  comment '购买总价',
	snapshot_id bigint not null  comment '商品快照id',
	create_time timestamp null comment '创建时间',
	create_by bigint null comment '创建者',
	update_time bigint null comment '更新时间',
	update_by bigint null comment '更新者',
  constraint uk_order_id_sku_id unique (order_id,sku_id)
) comment '订单商品表' charset=utf8mb4;