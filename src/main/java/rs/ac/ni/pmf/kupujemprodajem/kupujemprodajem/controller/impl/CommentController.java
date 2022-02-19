package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.impl;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.ICommentController;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.CommentDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.service.CommentService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@AllArgsConstructor
public class CommentController implements ICommentController {

    private final CommentService _commentService;

    @Override
    public EntityModel<CommentDTO> getComment(final Long id){
        return _commentService.getComment(id);
    }

    @Override
    public CollectionModel<EntityModel<CommentDTO>> getAllComments() {
        return _commentService.getAllComments();
    }

    @Override
    public ResponseEntity<EntityModel<CommentDTO>> createComment(final CommentDTO commentDto){
        return _commentService.createComment(commentDto);
    }

    @Override
    public ResponseEntity<?> updateComment(final Long id, final CommentDTO commentDTO){
        return _commentService.updateComment(id,commentDTO);
    }

    @Override
    public ResponseEntity<?> deleteComment(final Long id) {
        return _commentService.deleteComment(id);
    }

}
