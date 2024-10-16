package com.bezkoder.spring.jpa.postgresql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "repository_info")
public class PermissionsInfo {

    private Boolean admin;
    private Boolean push;
    private Boolean pull;
    @Id
    private Long permission_id;


}