use book_progress;

create table users(
	user_id int primary key,
    first_name varchar(30),
    last_name varchar(30),
    user_name varchar(15) not null, 
    user_pswd varchar(15) not null
);

create table books(
	book_id int primary key,
    title varchar(50),
    author_first_name varchar(30),
    author_last_name varchar(30),
    num_of_pages int
);

create table user_book(
	user_id int not null,
    book_id int not null,
    primary key (user_id, book_id),
    foreign key (user_id) references users(user_id),
    foreign key (book_id) references books(book_id)
);

	