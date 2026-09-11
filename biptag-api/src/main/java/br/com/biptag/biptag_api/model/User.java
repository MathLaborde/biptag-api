package br.com.biptag.biptag_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

@Entity
@Immutable
@Table(name = "users", schema = "auth")
public class User {

    @Id
    private UUID id;

    private String email;

    @Column(name = "raw_user_meta_data")
    private String userMetadata;

    public User() {
    }

    @JsonProperty("name")
    public String getName() {
        if (userMetadata == null || userMetadata.isBlank()) return null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(userMetadata);
            return node.has("name") ? node.get("name").asText() : null;
        } catch (Exception e) {
            return null;
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserMetadata() {
        return userMetadata;
    }

    public void setUserMetadata(String userMetadata) {
        this.userMetadata = userMetadata;
    }
}