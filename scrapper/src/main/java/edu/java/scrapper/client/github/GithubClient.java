package edu.java.scrapper.client.github;

import edu.java.scrapper.client.dto.github.RepositoryEventResponse;

public interface GithubClient {
    RepositoryEventResponse fetchLastModified(String owner, String repo);
}
