

create table card(
  id int not null primary  key commit '唯一主键',
  card_name varchar(200) not null commit '名称',
  bank int not null commit '银行'
  account_date
  user_Name char(15) not null check(user_Name !=''),
  user_Password char(15) not null,
  user_emial varchar(20) not null unique,
  primary key(user_Name)
)engine=innodb default charset=utf8 auto_increment=1;



