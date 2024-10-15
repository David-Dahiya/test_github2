package com.bezkoder.spring.jpa.postgresql.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.security.Permissions;
import java.util.List;

@Data
public class RepositoryInfo {

    private Long id;

    @JsonProperty("nodea_id")
    private String nodeId;

    private String name;

    @JsonProperty("full_name")
    private String fullName;

    private OwnerInfo owner;

    private Boolean privateRepo; // 'private' is a reserved keyword in Java, so we rename it

    @JsonProperty("html_url")
    private String htmlUrl;

    private String description;

    private Boolean fork;

    private String url;

    @JsonProperty("archive_url")
    private String archiveUrl;

    @JsonProperty("assignees_url")
    private String assigneesUrl;

    @JsonProperty("blobs_url")
    private String blobsUrl;

    @JsonProperty("branches_url")
    private String branchesUrl;

    @JsonProperty("collaborators_url")
    private String collaboratorsUrl;

    @JsonProperty("comments_url")
    private String commentsUrl;

    @JsonProperty("commits_url")
    private String commitsUrl;

    @JsonProperty("compare_url")
    private String compareUrl;

    @JsonProperty("contents_url")
    private String contentsUrl;

    @JsonProperty("contributors_url")
    private String contributorsUrl;

    @JsonProperty("deployments_url")
    private String deploymentsUrl;

    @JsonProperty("downloads_url")
    private String downloadsUrl;

    @JsonProperty("events_url")
    private String eventsUrl;

    @JsonProperty("forks_url")
    private String forksUrl;

    @JsonProperty("git_commits_url")
    private String gitCommitsUrl;

    @JsonProperty("git_refs_url")
    private String gitRefsUrl;

    @JsonProperty("git_tags_url")
    private String gitTagsUrl;

    @JsonProperty("git_url")
    private String gitUrl;

    @JsonProperty("issue_comment_url")
    private String issueCommentUrl;

    @JsonProperty("issue_events_url")
    private String issueEventsUrl;

    @JsonProperty("issues_url")
    private String issuesUrl;

    @JsonProperty("keys_url")
    private String keysUrl;

    @JsonProperty("labels_url")
    private String labelsUrl;

    @JsonProperty("languages_url")
    private String languagesUrl;

    @JsonProperty("merges_url")
    private String mergesUrl;

    @JsonProperty("milestones_url")
    private String milestonesUrl;

    @JsonProperty("notifications_url")
    private String notificationsUrl;

    @JsonProperty("pulls_url")
    private String pullsUrl;

    @JsonProperty("releases_url")
    private String releasesUrl;

    @JsonProperty("ssh_url")
    private String sshUrl;

    @JsonProperty("stargazers_url")
    private String stargazersUrl;

    @JsonProperty("statuses_url")
    private String statusesUrl;

    @JsonProperty("subscribers_url")
    private String subscribersUrl;

    @JsonProperty("subscription_url")
    private String subscriptionUrl;

    @JsonProperty("tags_url")
    private String tagsUrl;

    @JsonProperty("teams_url")
    private String teamsUrl;

    @JsonProperty("trees_url")
    private String treesUrl;

    @JsonProperty("clone_url")
    private String cloneUrl;

    @JsonProperty("mirror_url")
    private String mirrorUrl;

    @JsonProperty("hooks_url")
    private String hooksUrl;

    @JsonProperty("svn_url")
    private String svnUrl;

    private String homepage;

    private Object language; // Could be a specific type if you know it

    @JsonProperty("forks_count")
    private Integer forksCount;

    @JsonProperty("stargazers_count")
    private Integer stargazersCount;

    @JsonProperty("watchers_count")
    private Integer watchersCount;

    private Integer size;

    @JsonProperty("default_branch")
    private String defaultBranch;

    @JsonProperty("open_issues_count")
    private Integer openIssuesCount;

    @JsonProperty("is_template")
    private Boolean isTemplate;

    private List<String> topics;

    @JsonProperty("has_issues")
    private Boolean hasIssues;

    @JsonProperty("has_projects")
    private Boolean hasProjects;

    @JsonProperty("has_wiki")
    private Boolean hasWiki;

    @JsonProperty("has_pages")
    private Boolean hasPages;

    @JsonProperty("has_downloads")
    private Boolean hasDownloads;

    @JsonProperty("has_discussions")
    private Boolean hasDiscussions;

    private Boolean archived;

    private Boolean disabled;

    private String visibility;

    @JsonProperty("pushed_at")
    private String pushedAt; // Consider using LocalDateTime

    @JsonProperty("created_at")
    private String createdAt; // Consider using LocalDateTime

    @JsonProperty("updated_at")
    private String updatedAt; // Consider using LocalDateTime

    private Permissions permissions;


}