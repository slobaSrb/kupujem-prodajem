package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(long id){super("User with id " + id + " not found.");}
}
