package br.com.biptag.biptag_api.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "found_reports")
public class FoundReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "finder_id")
    private UUID finderId;

    @Column(name = "found_lat")
    private Double foundLat;

    @Column(name = "found_lng")
    private Double foundLng;

    @Column(name = "found_address", columnDefinition = "text")
    private String foundAddress;

    @Column(name = "found_date", nullable = false)
    private OffsetDateTime foundDate;

    @Column(name = "notes", columnDefinition = "text")
    private String notes;

    @Column(name = "is_anonymous")
    private Boolean isAnonymous;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "alert_id")
    private Long alertId;

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = OffsetDateTime.now();
        }
    }

    public FoundReport() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public UUID getFinderId() {
        return finderId;
    }

    public void setFinderId(UUID finderId) {
        this.finderId = finderId;
    }

    public Double getFoundLat() {
        return foundLat;
    }

    public void setFoundLat(Double foundLat) {
        this.foundLat = foundLat;
    }

    public Double getFoundLng() {
        return foundLng;
    }

    public void setFoundLng(Double foundLng) {
        this.foundLng = foundLng;
    }

    public String getFoundAddress() {
        return foundAddress;
    }

    public void setFoundAddress(String foundAddress) {
        this.foundAddress = foundAddress;
    }

    public OffsetDateTime getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(OffsetDateTime foundDate) {
        this.foundDate = foundDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(Boolean anonymous) {
        isAnonymous = anonymous;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getAlertId() {
        return alertId;
    }

    public void setAlertId(Long alertId) {
        this.alertId = alertId;
    }
}
