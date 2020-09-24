package com.wellitonpaiva.dailyupdater;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

public class CovidUpdater {

    String apiUrl = "https://api.coronavirus.data.gov.uk/v1/data?";
    String parameters = "filters=areaType=nation&structure={\"date\":\"date\",\"newCasesByPublishDate\":\"newCasesByPublishDate\"}&latestBy=newCasesByPublishDate";

    int getNumberOfCases() throws IOException {
        URL url = new URL(apiUrl + parameters);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();

        try (GZIPInputStream gzipInputStream = new GZIPInputStream(httpsURLConnection.getInputStream());
             BufferedReader reader = new BufferedReader(new InputStreamReader(gzipInputStream, StandardCharsets.UTF_8))) {
            StringBuilder builder = new StringBuilder();
            String input;
            while ((input = reader.readLine()) != null) {
                builder.append(input);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            CovidResponse covidData = objectMapper.readValue(builder.toString(), CovidResponse.class);
            return covidData.getData().stream().mapToInt(CovidData::getNewCasesByPublishDate).sum();
        }
    }

}
