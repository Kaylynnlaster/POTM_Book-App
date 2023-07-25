drop schema if exists book_progress;
create schema `book_progress`;
use book_progress;

drop table if exists users;
create table users(
	user_id int primary key auto_increment,
    first_name varchar(30),
    last_name varchar(30),
    user_name varchar(15) not null, 
    user_pswd varchar(15) not null
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
    foreign key (user_id) references users(user_id),
    foreign key (book_id) references books(book_id)
);


insert into users(first_name, last_name, user_name, user_pswd) 
values
("John", "Smith", "john123", "password123"),
("Sarah", "Johnson", "sarahj", "pass456"),
("Michael", "Williams", "mike22", "secret321"),
("Emily", "Brown", "emilyb", "abcdefg123"),
("Daniel", "Davis", "danny_d", "qwerty789"),
("Jessica", "Wilson", "jessy22", "p@ssw0rd"),
("David", "Anderson", "david_and", "dA12345"),
("William", "Thompson", "willT91", "p@ssw0rd!"),
("Sophia", "Scott", "sophiaS", "Ss34567"),
("Olivia", "Martinez", "o_mart", "oliviaM2023");

insert into books(title, author_first_name, author_last_name, num_of_pages) 
values
("The Great Gatsby", "F. Scott", "Fitzgerald", 180),
("To Kill a Mockingbird", "Harper", "Lee", 281),
("1984", "George", "Orwell", 328),
("Pride and Prejudice", "Jane", "Austen", 279),
("The Catcher in the Rye", "J.D.", "Salinger", 234),
("The Intelligent Investor", "Benjamin", "Graham", 640),
("Harry Potter and the Sorcerer's Stone", "J.K.", "Rowling", 320),
("The Hobbit", "J.R.R.", "Tolkien", 310),
("The Lord of the Rings", "J.R.R.", "Tolkien", 1178),
("Brave New World", "Aldous", "Huxley", 288);

insert into users_books(user_id, book_id, pages_read) 
values
(1, 1, 0),
(1, 2, 200),
(2, 3, 50),
(2, 4, 279),
(3, 1, 0),
(3, 2, 20),
(4, 3, 328),
(5, 2, 0),
(5, 4, 200),
(7, 7, 100),
(10, 5, 0),
(9, 9, 600),
(8, 1, 100);


select * from users;
select * from books;
select * from users_books;

	