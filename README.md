01. SELECT title FROM Book b WHERE b.publicationYear>2010
02. UPDATE Book b SET b.price = b.price+(b.price*10)/100 WHERE b.author.id = :authorId
03. public static void deleteAuthorAndBooks(String authorId) {  
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
         Author authorToDelete = session.get(Author.class, authorId);
         if (authorToDelete != null) {
         session.delete(authorToDelete);
        }
         transaction.commit();
        session.close();
}
04. SELECT avg(price) FROM Book b
05. SELECT a,count(b) FROM Author a JOIN a.books b group by a.id",Object[].class

06. FROM Book b WHERE b.author IN(SELECT a FROM Author a WHERE a.country = :country)
    
07.The @JoinColumn annotation in Hibernate is used to specify the mapping of a foreign key column in a relationship between two entities.When the author id is given to the name , it will be given to the book entity as the foreign key

10. SELECT a FROM Author a " + "WHERE SIZE(a.books) > (" + "   SELECT AVG(SIZE(b.books)) FROM Author b" + ")
