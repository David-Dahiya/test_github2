package com.bezkoder.spring.jpa.postgresql.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OwnerInfo {
    private Long id;
    private String login;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    // Other fields can be added as needed
}