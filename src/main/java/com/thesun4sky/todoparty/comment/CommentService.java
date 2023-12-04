package com.thesun4sky.todoparty.comment;

import com.thesun4sky.todoparty.todo.Todo;
import com.thesun4sky.todoparty.todo.TodoService;
import com.thesun4sky.todoparty.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TodoService todoService;

    public CommentResponseDTO createComment(CommentRequestDTO dto, User user) {
        Todo todo = todoService.getTodo(dto.getTodoId());

        Comment comment = new Comment(dto);
        comment.setUser(user);
        comment.setTodo(todo);

        commentRepository.save(comment);

        return new CommentResponseDTO(comment);
    }


    @Transactional // entity가 수정이 일어났으면 메서드가 끝날때 수정된 내용을 감지하여 DB에 반영 그래서 save 따로 x
    public CommentResponseDTO updateComment(Long commentId, CommentRequestDTO commentRequestDTO, User user) {
        Comment comment = getUserComment(commentId, user);

        comment.setText(commentRequestDTO.getText());

        return new CommentResponseDTO(comment);
    }

    public void deleteComment(Long commentId, User user) {
        Comment comment = getUserComment(commentId, user);

        commentRepository.delete(comment);
    }

    // 공통된 기능 메서드로 뺴는법 Refactor -> Extract Method..
    private Comment getUserComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 입니다."));

        if(!user.getId().equals(comment.getUser().getId())) {
            throw new RejectedExecutionException("작성자만 수정할 수 있습니다.");
        }
        return comment;
    }
}
