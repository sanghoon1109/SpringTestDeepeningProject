package com.thesun4sky.todoparty.comment;

import com.thesun4sky.todoparty.todo.Todo;
import com.thesun4sky.todoparty.todo.TodoRequestDTO;
import com.thesun4sky.todoparty.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Long id;

    @Column
    private String text;
    @Column
    private LocalDateTime createDate;

    @ManyToOne // 다수 : 1 예) 댓글은 '한' 사람이 '여러'개 달 수 있음
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne // 다수 : 1 예) 댓글은 '한' 코멘트에 '여러'개 달 수 있음
    @JoinColumn(name = "todo_id")
    private Todo todo;


    public Comment(CommentRequestDTO commentRequestDTO) {
        this.text = commentRequestDTO.getText();
        this.createDate = LocalDateTime.now();
    }

    // 연관 관계
    public void setUser(User user) {
        this.user = user;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
        todo.getComments().add(this);
    }

    // 서비스 메서드
    public void setText(String text) {
        this.text = text;
    }
}
