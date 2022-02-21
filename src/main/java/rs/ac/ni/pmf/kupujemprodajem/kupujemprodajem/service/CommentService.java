package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller.impl.CommentController;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions.ApiError;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions.BadRequestException;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions.ResourceNotFoundException;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.ModelBuilder;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.dto.CommentDTO;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.CommentEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.mapper.CommentMapper;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories.CommentRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public EntityModel<CommentDTO> getComment(final Long id){
        final CommentEntity commentEntity = findComment(id);

        return ModelBuilder.buildCommentModel(CommentMapper.toDto(commentEntity));
    }

    public CollectionModel<EntityModel<CommentDTO>> getAllComments() {
        final List<EntityModel<CommentDTO>> ads = commentRepository.findAll().stream()
                .map(CommentMapper::toDto)
                .map(ModelBuilder::buildCommentModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                ads,
                linkTo(methodOn(CommentController.class).getAllComments()).withSelfRel());
    }

    public ResponseEntity<EntityModel<CommentDTO>> createComment(final CommentDTO commentDto){

        final CommentEntity commentEntity = CommentMapper.toEntity(commentDto);

        final CommentEntity savedEntity = commentRepository.save(commentEntity);

        return ResponseEntity
                .created(linkTo(methodOn(CommentController.class).getComment(savedEntity.getCommentID())).toUri())
                .body(ModelBuilder.buildCommentModel(CommentMapper.toDto(savedEntity)));
    }

    public ResponseEntity<?> updateComment(final Long id, final CommentDTO commentDTO){
        final CommentEntity entity = findComment(id);

        if(commentDTO.getComment() != null || commentDTO.getComment().length() > 5) { entity.setComment(commentDTO.getComment()); } else { throw new BadRequestException("Comment not valid."); }
        entity.setCommentDate(OffsetDateTime.now());
        commentRepository.save(entity);
        CommentEntity savedEntity = commentRepository.save(entity);
        return ResponseEntity.ok().body(ModelBuilder.buildCommentModel(CommentMapper.toDto(savedEntity)));
    }

    public ResponseEntity<?> deleteComment(final Long id) {
        final CommentEntity entity = findComment(id);

        commentRepository.delete(entity);

        return ResponseEntity.noContent().build();
    }

    private CommentEntity findComment(final Long commentID) {
        return commentRepository.findById(commentID)
                .orElseThrow(()->new ResourceNotFoundException(ApiError.ResourceType.COMMENT,"Comment with id " + commentID + "not found."));
    }
}
