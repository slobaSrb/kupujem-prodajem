package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(long id){ super("Comment with id " + id + " does not exist."); }
}
