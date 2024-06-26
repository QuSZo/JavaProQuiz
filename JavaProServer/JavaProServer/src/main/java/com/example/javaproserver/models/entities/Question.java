package com.example.javaproserver.models.entities;

import com.example.javaproserver.enums.InputTypeEnum;
import jakarta.persistence.*;
import jakarta.xml.bind.DatatypeConverter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.Type;
import org.hibernate.type.descriptor.jdbc.VarbinaryJdbcType;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Entity
@Table
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private InputTypeEnum inputType;
    @Column(columnDefinition="TEXT")
    private String text;
    @Column(columnDefinition="TEXT")
    private String code;
    @JdbcType(VarbinaryJdbcType.class)
    private byte[] image;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "questionId")
    private List<Answer> answers;

    private UUID quizId;

    public Question() {
        this.answers = new ArrayList<>();
    }

    public Question(InputTypeEnum inputType, String text, String code, byte[] image, List<Answer> answers) {
        this.inputType = inputType;
        this.text = text;
        this.code = code;
        this.image = image;
        this.answers = answers;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public InputTypeEnum getInputType() {
        return inputType;
    }

    public void setInputType(InputTypeEnum inputType) {
        this.inputType = inputType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
