package board.board_Project.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BOARD") // 실제 테이블 이름과 매핑
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY: auto-increment
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT") // 긴 텍스트가 가능하도록
    private String content;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "password")
    private String password;


    // 기본 생성자 (JPA용)
    public Data() {}

    // 생성자
    public Data(String title, String name, String content) {
        this.title = title;
        this.name = name;
        this.content = content;
    }

    // Getter/Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
