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
(1, 1, 180),
(1, 2, 200),
(1, 3, 0),
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