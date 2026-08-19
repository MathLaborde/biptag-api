package br.com.biptag.biptag_api.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "alerts")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "last_seen_lat")
    private Double lastSeenLat;

    @Column(name = "last_seen_lng")
    private Double lastSeenLng;

    @Column(name = "last_seen_address", columnDefinition = "text")
    private String lastSeenAddress;

    @Column(name = "incident_date", nullable = false)
    private String incidentDate;

    @Column(name = "radius_km")
    private Integer radiusKm;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    // Executado automaticamente antes de salvar no banco
    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = OffsetDateTime.now();
        }
    }

    public Alert() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getLastSeenLat() {
        return lastSeenLat;
    }

    public void setLastSeenLat(Double lastSeenLat) {
        this.lastSeenLat = lastSeenLat;
    }

    public Double getLastSeenLng() {
        return lastSeenLng;
    }

    public void setLastSeenLng(Double lastSeenLng) {
        this.lastSeenLng = lastSeenLng;
    }

    public String getLastSeenAddress() {
        return lastSeenAddress;
    }

    public void setLastSeenAddress(String lastSeenAddress) {
        this.lastSeenAddress = lastSeenAddress;
    }

    public String getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(String incidentDate) {
        this.incidentDate = incidentDate;
    }

    public Integer getRadiusKm() {
        return radiusKm;
    }

    public void setRadiusKm(Integer radiusKm) {
        this.radiusKm = radiusKm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}