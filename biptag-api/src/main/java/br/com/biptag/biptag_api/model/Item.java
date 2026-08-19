package br.com.biptag.biptag_api.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "tag_id", unique = true)
    private String tagId;

    @Column(name = "attach_tag_date")
    private OffsetDateTime attachTagDate;

    @Column(name = "nf_key", unique = true)
    private String nfKey;

    @Column(name = "nf_create_data")
    private LocalDate nfCreateData;

    @Column(name = "nf_photo_url")
    private String nfPhotoUrl;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    private Long category;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "image")
    private String image;

    @Column(name = "status", columnDefinition = "text")
    private String status;

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = OffsetDateTime.now();
        }
    }

    public Item() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public OffsetDateTime getAttachTagDate() {
        return attachTagDate;
    }

    public void setAttachTagDate(OffsetDateTime attachTagDate) {
        this.attachTagDate = attachTagDate;
    }

    public String getNfKey() {
        return nfKey;
    }

    public void setNfKey(String nfKey) {
        this.nfKey = nfKey;
    }

    public LocalDate getNfCreateData() {
        return nfCreateData;
    }

    public void setNfCreateData(LocalDate nfCreateData) {
        this.nfCreateData = nfCreateData;
    }

    public String getNfPhotoUrl() {
        return nfPhotoUrl;
    }

    public void setNfPhotoUrl(String nfPhotoUrl) {
        this.nfPhotoUrl = nfPhotoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}