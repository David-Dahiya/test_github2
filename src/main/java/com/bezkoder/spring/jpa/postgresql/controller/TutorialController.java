package com.bezkoder.spring.jpa.postgresql.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bezkoder.spring.jpa.postgresql.model.OwnerInfo;
import com.bezkoder.spring.jpa.postgresql.model.RepositoryInfo;
import com.bezkoder.spring.jpa.postgresql.model.TeamInfo;
import com.bezkoder.spring.jpa.postgresql.repository.RepositoryInfoRepository;
import com.bezkoder.spring.jpa.postgresql.repository.TestRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import okhttp3.OkHttpClient;

import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.jpa.postgresql.model.Tutorial;
import com.bezkoder.spring.jpa.postgresql.repository.TutorialRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {

	@Autowired
	TutorialRepository tutorialRepository;


   @Autowired
	TestRepository testRepository;

   @Autowired
   RepositoryInfoRepository repositoryInformation;

   @GetMapping ("/teamsInfo")
   @Operation(summary = "fetch all teams info in an org", description = "Returns a list of all teamInfo like team name, repos etc .")
   public List<TeamInfo> teamInfoList() throws IOException {

	   OkHttpClient client = new OkHttpClient();
	   Request request = new Request.Builder()
			   .url("https://api.github.com/orgs/test-org-newRelic/teams")
			   .header("Accept", "application/vnd.github.+json")
			   .header("Authorization", "Bearer  " + "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")
			   .header("User-Agent", "OkHttp")
			   .header("X-GitHub-Api-Version", "2022-11-28")
			   .build();
	   List<TeamInfo> teamInfoList = new ArrayList<>();
	   try (Response response = client.newCall(request).execute()) {
		   String responseBody = response.body().string();
		   if (!response.isSuccessful()) {
			   System.out.println("response is not successful");
			   return teamInfoList;
		   }
		   ObjectMapper mapper = new ObjectMapper();
		   JsonNode teams = mapper.readTree(responseBody);
		   try {
			   for (JsonNode team : teams) {
				   teamInfoList.add(new TeamInfo(
						   team.get("name").asText(),
						   team.get("id").asLong(),
						   team.get("node_id").asText(),
						   team.get("slug").asText(),
						   team.get("description").asText(),
						   team.get("privacy").asText(),
						   team.get("notification_setting").asText(),
						   team.get("url").asText(),
						   team.get("html_url").asText(),
						   team.get("members_url").asText(),
						   team.get("repositories_url").asText(),
						   team.get("permission").asText(),
						   team.get("parent").isNull() ? null : team.get("parent").asText()
				   ));
				   System.out.println("team name is " + team.get("name").asText());
			   }

			   testRepository.saveAll(teamInfoList);
			   System.out.println("teamInfoList is " + teamInfoList);
		   }
		   catch (Exception e){
			   System.out.println("ERROR is " + e.getMessage());
		   }

		   return teamInfoList;
	   }
   }


	@GetMapping ("/teamProject")
	@Operation(summary = "fetch team projects", description = "Returns a list of all projects of a team ")
	public List<RepositoryInfo> teamProjects() throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("https://api.github.com/orgs/test-org-newRelic/teams/new-relic-workflow-2/repos")
				.header("Accept", "application/vnd.github.+json")
				.header("Authorization", "Bearer  " + "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")
				.header("User-Agent", "OkHttp")
				.header("X-GitHub-Api-Version", "2022-11-28")
				.build();

		List<RepositoryInfo> repositoryInfoList = new ArrayList<>();
		try (Response response = client.newCall(request).execute()) {
			String responseBody = response.body().string();
			if (!response.isSuccessful()) {
				System.out.println("response is not successful");
				return repositoryInfoList;
			}
			ObjectMapper mapper = new ObjectMapper();
			JsonNode teams = mapper.readTree(responseBody);
			try {
				for (JsonNode team : teams) {
					RepositoryInfo repositoryInfo = new RepositoryInfo();

					repositoryInfo.setId(team.get("id").asLong());
					repositoryInfo.setNodeId(team.get("node_id").asText());
					repositoryInfo.setName(team.get("name").asText());
					repositoryInfo.setFullName(team.get("full_name").asText());

					// Create and set OwnerInfo
					OwnerInfo owner = new OwnerInfo();
					owner.setLogin(team.get("owner").get("login").asText());
					owner.setOwner_id(team.get("owner").get("id").asLong());
					repositoryInfo.setOwner(owner);

					repositoryInfo.setPrivateRepo(team.get("private").asBoolean());
					repositoryInfo.setHtmlUrl(team.get("html_url").asText());
					repositoryInfo.setDescription(team.get("description").asText());
					repositoryInfo.setFork(team.get("fork").asBoolean());
					repositoryInfo.setUrl(team.get("url").asText());
					repositoryInfo.setArchiveUrl(team.path("archive_url").asText(null)); // Use path() to avoid exceptions for missing fields
					repositoryInfo.setAssigneesUrl(team.path("assignees_url").asText(null));
					repositoryInfo.setBlobsUrl(team.path("blobs_url").asText(null));
					repositoryInfo.setBranchesUrl(team.path("branches_url").asText(null));
					repositoryInfo.setCollaboratorsUrl(team.path("collaborators_url").asText(null));
					repositoryInfo.setCommentsUrl(team.path("comments_url").asText(null));
					repositoryInfo.setCommitsUrl(team.path("commits_url").asText(null));
					repositoryInfo.setCompareUrl(team.path("compare_url").asText(null));
					repositoryInfo.setContentsUrl(team.path("contents_url").asText(null));
					repositoryInfo.setContributorsUrl(team.path("contributors_url").asText(null));
					repositoryInfo.setDeploymentsUrl(team.path("deployments_url").asText(null));
					repositoryInfo.setDownloadsUrl(team.path("downloads_url").asText(null));
					repositoryInfo.setEventsUrl(team.path("events_url").asText(null));
					repositoryInfo.setForksUrl(team.path("forks_url").asText(null));
					repositoryInfo.setGitCommitsUrl(team.path("git_commits_url").asText(null));
					repositoryInfo.setGitRefsUrl(team.path("git_refs_url").asText(null));
					repositoryInfo.setGitTagsUrl(team.path("git_tags_url").asText(null));
					repositoryInfo.setGitUrl(team.path("git_url").asText(null));
					repositoryInfo.setIssueCommentUrl(team.path("issue_comment_url").asText(null));
					repositoryInfo.setIssueEventsUrl(team.path("issue_events_url").asText(null));
					repositoryInfo.setIssuesUrl(team.path("issues_url").asText(null));
					repositoryInfo.setKeysUrl(team.path("keys_url").asText(null));
					repositoryInfo.setLabelsUrl(team.path("labels_url").asText(null));
					repositoryInfo.setLanguagesUrl(team.path("languages_url").asText(null));
					repositoryInfo.setMergesUrl(team.path("merges_url").asText(null));
					repositoryInfo.setMilestonesUrl(team.path("milestones_url").asText(null));
					repositoryInfo.setNotificationsUrl(team.path("notifications_url").asText(null));
					repositoryInfo.setPullsUrl(team.path("pulls_url").asText(null));
					repositoryInfo.setReleasesUrl(team.path("releases_url").asText(null));
					repositoryInfo.setSshUrl(team.path("ssh_url").asText(null));
					repositoryInfo.setStargazersUrl(team.path("stargazers_url").asText(null));

					// Set additional fields if available
					repositoryInfo.setForksCount(team.hasNonNull("forks_count") ? team.get("forks_count").asInt() : null);
					repositoryInfo.setStargazersCount(team.hasNonNull("stargazers_count") ? team.get("stargazers_count").asInt() : null);
					repositoryInfo.setWatchersCount(team.hasNonNull("watchers_count") ? team.get("watchers_count").asInt() : null);
					repositoryInfo.setSize(team.hasNonNull("size") ? team.get("size").asInt() : null);
					repositoryInfo.setArchived(team.hasNonNull("archived") ? team.get("archived").asBoolean() : null);
					repositoryInfo.setDisabled(team.hasNonNull("disabled") ? team.get("disabled").asBoolean() : null);
					repositoryInfo.setLanguage((team.hasNonNull("language")) ? String.valueOf(team.get("language")) : null); // Assuming language can be an object or null

					// Add the populated Repository Info to the list
					repositoryInfoList.add(repositoryInfo);
				}

				// Output the populated list of repositories
				System.out.println(repositoryInfoList);

			}
			catch (Exception e){
				System.out.println("ERROR is " + e.getMessage());
			}

			return repositoryInfoList;
		}


	}













//	@GetMapping("/tutorials")
//	public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
//		try {
//			List<Tutorial> tutorials = new ArrayList<Tutorial>();
//
//			if (title == null)
//				tutorialRepository.findAll().forEach(tutorials::add);
//			else
//				tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
//
//			if (tutorials.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//
//			return new ResponseEntity<>(tutorials, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	@GetMapping("/tutorials/{id}")
//	public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
//		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
//
//		if (tutorialData.isPresent()) {
//			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//
//	@PostMapping("/tutorials")
//	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
//		try {
//			Tutorial _tutorial = tutorialRepository
//					.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
//			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	@PutMapping("/tutorials/{id}")
//	public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
//		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
//
//		if (tutorialData.isPresent()) {
//			Tutorial _tutorial = tutorialData.get();
//			_tutorial.setTitle(tutorial.getTitle());
//			_tutorial.setDescription(tutorial.getDescription());
//			_tutorial.setPublished(tutorial.isPublished());
//			return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//
//	@DeleteMapping("/tutorials/{id}")
//	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
//		try {
//			tutorialRepository.deleteById(id);
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	@DeleteMapping("/tutorials")
//	public ResponseEntity<HttpStatus> deleteAllTutorials() {
//		try {
//			tutorialRepository.deleteAll();
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//	}
//
//	@GetMapping("/tutorials/published")
//	public ResponseEntity<List<Tutorial>> findByPublished() {
//		try {
//			List<Tutorial> tutorials = tutorialRepository.findByPublished(true);
//
//			if (tutorials.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//			return new ResponseEntity<>(tutorials, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

}
