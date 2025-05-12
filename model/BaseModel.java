package model;

import java.time.LocalDateTime;

public class BaseModel {

    private long id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private User createdUser;
    private User updatedUser;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }

    public User getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(User updatedUser) {
        this.updatedUser = updatedUser;
    }
}
