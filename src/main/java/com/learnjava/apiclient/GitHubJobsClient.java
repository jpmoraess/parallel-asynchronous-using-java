package com.learnjava.apiclient;

import com.learnjava.domain.github.GitHubPosition;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static com.learnjava.util.LoggerUtil.log;

public class GitHubJobsClient {

    private final WebClient webClient;

    public GitHubJobsClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<GitHubPosition> invokeGithubJobsAPI_withPageNumber(int page, String description) {
        String uri = UriComponentsBuilder.fromUriString("/positions.json")
                .queryParam("description", description)
                .queryParam("page", page)
                .buildAndExpand()
                .toUriString();
        log("URI IS: " + uri);
        List<GitHubPosition> gitHubPostitions = webClient.get().uri(uri)
                .retrieve()
                .bodyToFlux(GitHubPosition.class)
                .collectList()
                .block();
        return gitHubPostitions;
    }

    public List<GitHubPosition> invokeGithubJobsAPI_usingMultiplePageNumbers(List<Integer> pageNumbers, String description) {

        startTimer();
        List<GitHubPosition> gitHubPostitions = pageNumbers.stream()
                .map(page -> invokeGithubJobsAPI_withPageNumber(page, description))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        timeTaken();
        return gitHubPostitions;
    }

    public List<GitHubPosition> invokeGithubJobsAPI_usingMultiplePageNumbers_CompletableFuture(List<Integer> pageNumbers, String description) {

        startTimer();

        List<CompletableFuture<List<GitHubPosition>>> gitHubPostitions = pageNumbers.stream()
                .map(page -> CompletableFuture.supplyAsync(() -> invokeGithubJobsAPI_withPageNumber(page, description)))
                .collect(Collectors.toList());

        List<GitHubPosition> gitHubPostitionsList = gitHubPostitions.stream()
                .map(CompletableFuture::join)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        timeTaken();

        return gitHubPostitionsList;
    }

    public List<GitHubPosition> invokeGithubJobsAPI_usingMultiplePageNumbers_CompletableFuture_Approach2(List<Integer> pageNumbers, String description) {

        startTimer();

        List<CompletableFuture<List<GitHubPosition>>> gitHubPostitions = pageNumbers.stream()
                .map(page -> CompletableFuture.supplyAsync(() -> invokeGithubJobsAPI_withPageNumber(page, description)))
                .collect(Collectors.toList());

        CompletableFuture<Void> cfAllOf = CompletableFuture.allOf(gitHubPostitions.toArray(new CompletableFuture[gitHubPostitions.size()]));

        List<GitHubPosition> gitHubPositionList = cfAllOf.thenApply(v -> gitHubPostitions.stream()
                .map(CompletableFuture::join)
                .flatMap(Collection::stream)
                .collect(Collectors.toList()))
                .join();

        timeTaken();

        return gitHubPositionList;
    }
}
