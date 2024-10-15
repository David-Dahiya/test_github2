package com.bezkoder.spring.jpa.postgresql.model;

import lombok.Data;


@Data
public class PermissionsInfo {
    private Boolean admin;
    private Boolean push;
    private Boolean pull;
}