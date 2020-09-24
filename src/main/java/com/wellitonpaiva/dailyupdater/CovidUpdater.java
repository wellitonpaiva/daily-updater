package com.wellitonpaiva.dailyupdater;

import lombok.SneakyThrows;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;

public class CovidUpdater {

    String apiUrl = "https://api.coronavirus.data.gov.uk/v1/data?";
    String totalCasesParameters = "filters=areaType=nation&structure={" +
            "\"date\":\"date\"" +
            ",\"newCasesByPublishDate\":\"newCasesByPublishDate\"}" +
            "&latestBy=newCasesByPublishDate";

    String totalCasesInLondonParameters = "filters=areaType=region;areaName=London&structure={" +
            "\"date\":\"date\"" +
            ",\"newCasesByPublishDate\":\"newCasesByPublishDate\"}" +
            "&latestBy=newCasesByPublishDate&page=1";

    Integer getTotalNumberOfCases() {
        return WebClient.create()
                .get()
                .uri(getUri(totalCasesParameters))
                .retrieve()
                .bodyToMono(CovidResponse.class)
                .blockOptional()
                .orElseThrow()
                .getData()
                .stream()
                .map(CovidData::getNewCasesByPublishDate)
                .mapToInt(Integer::intValue).sum();
    }

    Integer getLondonNumberOfCases() {
        return WebClient.create()
                .get()
                .uri(getUri(totalCasesInLondonParameters))
                .retrieve()
                .bodyToMono(CovidResponse.class)
                .blockOptional()
                .orElseThrow()
                .getData()
                .stream()
                .map(CovidData::getNewCasesByPublishDate)
                .mapToInt(Integer::intValue).sum();
    }
    @SneakyThrows
    private String getUri(String parameters) {
        return apiUrl + parameters
                .replace("{", "%7B")
                .replace("}", "%7D")
                .replace("\"", "%22");
    }

}
