package com.example.Resource;

import com.example.Model.Book;
import com.example.Model.User;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.*;
import jakarta.inject.Singleton;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Singleton
@Path("/library")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookUserResource {

    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    private ExecutorService executor = Executors.newFixedThreadPool(5);

    public BookUserResource() {

        books.add(new Book(1, "Java Basics", "Kowsigan", "Tech", 10));
        books.add(new Book(2, "Python Guide", "Arun", "Tech", 5));

        User u1 = new User("Kowsi", "kowsi@example.com", "1234", "admin");
        u1.setUserid(1);
        users.add(u1);

        User u2 = new User("Arun", "arun@example.com", "abcd", "student");
        u2.setUserid(2);
        users.add(u2);
    }

    @GET
    @Path("/books")
    public Response getBooks(@QueryParam("category") @DefaultValue("") String category) {

        if (!category.isEmpty()) {
            List<Book> result = new ArrayList<>();
            for (Book b : books) {
                if (b.getCategory().equalsIgnoreCase(category)) {
                    result.add(b);
                }
            }
            return Response.ok(result).build();
        }
        return Response.ok(books).build();
    }

    @GET
    @Path("/books/{id}")
    public Response getBookById(
            @PathParam("id") int id,
            @HeaderParam("Client-Name") String clientName
    ) {
        for (Book b : books) {
            if (b.getBookId() == id) {
                return Response.ok(b)
                        .header("Requested-By", clientName)
                        .build();
            }
        }
        throw new NotFoundException("Book not found: " + id);
    }

    @POST
    @Path("/books")
    public Response addBook(@Valid Book book) {

        book.setBookId(books.size() + 1);
        books.add(book);

        return Response.status(201).entity(book).build();
    }

    @PUT
    @Path("/books/{id}")
    public Response updateBook(@PathParam("id") int id, Book update) {

        for (Book b : books) {
            if (b.getBookId() == id) {

                b.setBookName(update.getBookName());
                b.setAuthor(update.getAuthor());
                b.setCategory(update.getCategory());
                b.setNoOfBooks(update.getNoOfBooks());
                return Response.ok(b).build();
            }
        }
        throw new NotFoundException("Book not found: " + id);
    }

    @PATCH
    @Path("/books/{id}")
    public Response patchBook(@PathParam("id") int id, Book partial) {

        for (Book b : books) {
            if (b.getBookId() == id) {

                if (partial.getBookName() != null)
                    b.setBookName(partial.getBookName());

                if (partial.getAuthor() != null)
                    b.setAuthor(partial.getAuthor());

                if (partial.getCategory() != null)
                    b.setCategory(partial.getCategory());

                if (partial.getNoOfBooks() != 0)
                    b.setNoOfBooks(partial.getNoOfBooks());

                return Response.ok(b).build();
            }
        }
        throw new NotFoundException("Book not found: " + id);
    }

    @DELETE
    @Path("/books/{id}")
    public Response deleteBook(@PathParam("id") int id) {

        books.removeIf(b -> b.getBookId() == id);
        return Response.ok("{\"message\":\"Book deleted\"}").build();
    }

    @GET
    @Path("/users")
    public Response getUsers() {
        return Response.ok(users).build();
    }

    @GET
    @Path("/users/{id}")
    public Response getUser(@PathParam("id") int id){
        for(User user : users){
            if(id==user.getUserid()){
                return Response.ok(user).build();
            }
        }
        throw new NotFoundException("User Not Exist : "+id);
    }

    @POST
    @Path("/users")
    public Response addUser(@Valid User user){
        users.add(user);
        return Response.ok("{\"message\":\"User Added\"}").build();
    }

    @GET
    @Path("/books/async")
    public void asyncBooks(@Suspended AsyncResponse asyncResponse) {

        executor.submit(() -> {
            try { Thread.sleep(2000); } catch (Exception e) {}

            asyncResponse.resume(Response.ok(books).build());
        });
    }
}
