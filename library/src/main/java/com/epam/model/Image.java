package com.epam.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "image_size")
    private Long size;

    @Column(name = "file_extension", length = 40)
    private String fileExtension;

    @Column(name = "last_update",
            columnDefinition = "TIMESTAMP",
            insertable = false,
            updatable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    public Image() {
    }

    public Image(Long id, String name, Long size, String fileExtension, LocalDateTime lastUpdate) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.fileExtension = fileExtension;
        this.lastUpdate = lastUpdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
