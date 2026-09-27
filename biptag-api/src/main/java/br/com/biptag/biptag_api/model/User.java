package br.com.biptag.biptag_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.UUID;

@Entity
@Immutable
@Table(name = "app_users")
public class User {

    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    private String email;

    @Column(name = "full_name")
    private String fullName;

    public User() {
    }

    @JsonProperty("name")
    public String getName() {
        return fullName;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}