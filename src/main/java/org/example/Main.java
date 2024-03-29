package org.example;

import jakarta.persistence.Query;
import org.example.config.FactoryConfiguration;
import org.example.entity.Author;
import org.example.entity.Book;
import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        Author author = new Author();
        author.setId("A009");
        author.setName("Manel");
        author.setCountry("India");

        Book book1 = new Book();
        book1.setId("B008");
        book1.setTitle("Love");
        book1.setPublicationYear(2019);
        book1.setPrice(1950);
        book1.setAuthor(author);

        Book book2 = new Book();
        book2.setId("B009");
        book2.setTitle("Book");
        book2.setPublicationYear(2007);
        book2.setPrice(1500);
        book2.setAuthor(author);

        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(author);
        session.save(book1);
        session.save(book2);


        Query query = session.createQuery(" SELECT title FROM Book b WHERE b.publicationYear>2010");//01
        List list = ((org.hibernate.query.Query<?>) query).list();
        System.out.println(list);


        Query q = session.createQuery(" UPDATE Book b SET b.price = b.price+(b.price*10)/100 WHERE b.author.id = :authorId").setParameter("authorId", "A006");//02
        double i = q.executeUpdate();
        System.out.println("Update value: "+i);


        Query qu = session.createQuery(" SELECT avg(price) FROM Book b "); //04
        List li =  ((org.hibernate.query.Query<?>) qu).list();
        System.out.println(li);

         Query que = session.createQuery(" SELECT a,count(b) FROM Author a JOIN a.books b group by a.id",Object[].class); //05
        List<Object[]> resultList = que.getResultList();
        for (Object[] result : resultList){
            Author a = (Author) result[0];
            Long bookCount = (Long) result[1];
            System.out.println("Author: " + a.getName() + ", Book Count: " + bookCount);
        }




        List<Author> authors = session.createQuery("SELECT a FROM Author a " + "WHERE SIZE(a.books) > (" + "   SELECT AVG(SIZE(b.books)) FROM Author b" + ")", Author.class).getResultList();//10


        for (Author a : authors) {
            System.out.println("Author ID: " + a.getId() + ", Name: " + a.getName());
        }


        Query qry = session.createQuery("FROM Book b WHERE b.author IN(SELECT a FROM Author a WHERE a.country = :country)").setParameter("country","India");//06
        List<Book> books = qry.getResultList();
        for (Book book:books){
            System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor().getName());
        }
        transaction.commit();
        session.close();
        deleteAuthorAndBooks("A003");//03
    }
    public static void deleteAuthorAndBooks(String authorId) {  //03
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        Author authorToDelete = session.get(Author.class, authorId);

        if (authorToDelete != null) {

            session.delete(authorToDelete);
        }


        transaction.commit();
        session.close();
    }

}
