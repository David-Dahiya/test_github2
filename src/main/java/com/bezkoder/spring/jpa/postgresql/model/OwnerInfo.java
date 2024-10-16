package com.bezkoder.spring.jpa.postgresql.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "repository_info")
public class OwnerInfo {
    @Id
    private Long owner_id;
    private String login;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    // Other fields can be added as needed
}