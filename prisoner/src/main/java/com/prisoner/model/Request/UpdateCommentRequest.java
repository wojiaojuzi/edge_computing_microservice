package com.prisoner.model.Request;

import lombok.Data;

@Data
public class UpdateCommentRequest {
    private String id;

    private String comment;

    private String token;

    public UpdateCommentRequest(String id, String comment, String token) {
        this.id = id;
        this.comment = comment;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
