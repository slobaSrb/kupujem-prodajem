package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.impl;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.ICommentController;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions.CommentNotFoundException;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.ModelBuilder;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.CommentDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.CommentEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.mapper.CommentMapper;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories.CommentRepository;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.service.CommentService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
public class CommentController implements ICommentController {

    private final CommentService _commentService;

    @Override
    public EntityModel<CommentDTO> getComment(@PathVariable final Long id){
        return _commentService.getComment(id);
    }

    @Override
    public CollectionModel<EntityModel<CommentDTO>> getAllComments() {
        return _commentService.getAllComments();
    }

    @Override
    public ResponseEntity<EntityModel<CommentDTO>> createComment(@RequestBody final CommentDTO commentDto){
        return _commentService.createComment(commentDto);
    }

    @Override
    public ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO){
        return _commentService.updateComment(id,commentDTO);
    }

    @Override
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        return _commentService.deleteComment(id);
    }

}
