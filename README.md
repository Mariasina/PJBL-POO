Esse projeto foi construido para o MySQL, não foi testada a compatibilidade com outros softwares de banco de dados

```
create database poo_pjbl;
use poo_pjbl;
```

```
create table User(
	id int primary key auto_increment not null,
    username varchar(200),
    user_password varchar(100)
);
```

```
create table Score(
	id int primary key auto_increment not null,
    score_value int,
    id_user int,
    
    foreign key (id_user) references user(id) on delete cascade on update cascade
);
```