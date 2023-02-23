use project1;
show tables;

desc member;	
desc product;
desc wish;
desc orderlist;
desc review;
desc img;

select * from member;
insert into member(user_create, user_email, user_name, user_phone, user_pw, user_role)
values(now(),'m1@gmail.com','m1','010-1111-1111','1111','ADMIN');
insert into member(user_create, user_email, user_name, user_phone, user_pw, user_role)
values(now(),'m2@gmail.com','m2','010-222-1111','2222','MEMBER');
insert into member(user_create, user_email, user_name, user_phone, user_pw, user_role)
values(now(),'m3@gmail.com','m3','010-3333-1111','3333','MEMBER');
insert into member(user_create, user_email, user_name, user_phone, user_pw, user_role)
values(now(),'m4@gmail.com','m4','010-4444-1111','4444','MEMBER');
insert into member(user_create, user_email, user_name, user_phone, user_pw, user_role)
values(now(),'m5@gmail.com','m5','010-5555-1111','5555','MEMBER');

select * from product;

insert into product(product_create, product_desc, product_name, product_price, product_type)
values(now(),'상품 A입니다.','A',10000,'it');
insert into product(product_create, product_desc, product_name, product_price, product_type)
values(now(),'상품 B입니다.','B',20000,'enter');
insert into product(product_create, product_desc, product_name, product_price, product_type)
values(now(),'상품 C입니다.','C',30000,'design');
insert into product(product_create, product_desc, product_name, product_price, product_type)
values(now(),'상품 D입니다.','D',40000,'office');

select w.wish_id,w.user_id, p.product_id, p.product_type, p.product_name, p.product_price
from wish w	
inner join member m on w.user_id=m.user_id
inner join product p on w.product_id=p.product_id
order by w.wish_id desc;

insert into wish(wish_order, user_id, product_id) values(0,1,1);
insert into wish(wish_order, user_id, product_id) values(0,1,4);
insert into wish(wish_order, user_id, product_id) values(0,2,4);
insert into wish(wish_order, user_id, product_id) values(0,3,1);
insert into wish(wish_order, user_id, product_id) values(0,3,2);
insert into wish(wish_order, user_id, product_id) values(0,4,3);
insert into wish(wish_order, user_id, product_id) values(0,4,3);
select * from orderlist;
select * from review;
select * from img;


