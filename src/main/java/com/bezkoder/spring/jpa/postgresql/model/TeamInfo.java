package com.bezkoder.spring.jpa.postgresql.model;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "team_info")
@Data
public class TeamInfo {

    //{"name":"Bot-Users","id":7639119,"node_id":"T_kwDNe_vOAHSQTw","slug":"bot-users","description":"Automated workflow bot users (commonly with shared PAT)",
    // "privacy":"closed","notification_setting":"notifications_enabled","url":"https://api.github.com/organizations/31739/team/7639119","html_url":"https://github.com/orgs/newrelic/teams/bot-users","members_url":"https://api.github.com/organizations/31739/team/7639119/members{/member}","repositories_url":"https://api.github.com/organizations/31739/team/7639119/repos","permission":"pull","parent":null}


    @Id
    private Long id;
    private String name;
    private String node_id;
    private String slug;
    private String description;
    private String privacy;
    private String notification_setting;
    private String url;
    private String html_url;
    private String members_url;
    private String repositories_url;
    private String permission;
    private String parent;



    public TeamInfo(String name, Long id, String node_id, String slug, String description, String privacy, String notification_setting, String url, String html_url, String members_url, String repositories_url, String permission, String parent) {
        this.name = name;
        this.id = id;
        this.node_id = node_id;
        this.slug = slug;
        this.description = description;
        this.privacy = privacy;
        this.notification_setting = notification_setting;
        this.url = url;
        this.html_url = html_url;
        this.members_url = members_url;
        this.repositories_url = repositories_url;
        this.permission = permission;
        this.parent = parent;
    }

    public TeamInfo() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}