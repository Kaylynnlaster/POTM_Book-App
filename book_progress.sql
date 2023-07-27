drop schema if exists book_progress;
create schema `book_progress`;
use book_progress;

drop table if exists users;
create table users(
	user_id int primary key auto_increment,
    first_name varchar(30),
    last_name varchar(30),
    user_name varchar(15) not null unique, 
    user_pswd varchar(15) not null unique
);

drop table if exists books;
create table books(
	book_id int primary key auto_increment,
    title varchar(50),
    author_first_name varchar(30),
    author_last_name varchar(30),
    num_of_pages int
);

drop table if exists users_books;
create table users_books(
	user_id int not null,
    book_id int not null,
    pages_read int,
    primary key (user_id, book_id),
    foreign key (user_id) references users(user_id) ON DELETE CASCADE,
    foreign key (book_id) references books(book_id) ON DELETE CASCADE
);


	