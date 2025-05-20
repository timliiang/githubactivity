package com.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        if (args.length != 1) {
            System.out.println("github-activity <username>");
            return;
        }

        HttpRequest getRequest = HttpRequest.newBuilder()
        .uri(new URI("https://api.github.com/users/" + args[0] + "/events"))
        .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> getResponse = httpClient.send(getRequest, BodyHandlers.ofString());

        Gson gson = new Gson();
        
        Event[] events = gson.fromJson(getResponse.body(), Event[].class);
        
        for (Event event : events) {
            printEvent(event);
        }

    }

    public static void printEvent(Event event) {

        String type = event.getType();
        String repo = event.getRepo().getName();
        String msg = "Invalid Type";

        if (type.equals("PushEvent")) {
            msg = "Pushed " + event.getPayload().getSize() + " commits to " + repo;
        } else if (type.equals("PublicEvent")) {
            msg = "Made " + repo + " public";
        } else if (type.equals("WatchEvent")) {
            msg = "Starred " + repo;
        } else if (type.equals("PullRequestEvent")) {
            msg = event.getPayload().getAction() + " pull request " + event.getPayload().getNumber() + " in " + repo;
        } else if (type.equals("ReleaseEvent")) {
            msg = event.getPayload().getAction() + " release to " + repo;
        } else if (type.equals("IssuesEvent")) {
            msg = event.getPayload().getAction() + " an issue to " + repo;
        } else if (type.equals("CreateEvent")) {
            msg = "Created a new " + event.getPayload() + " in " + repo;
        }

        System.out.println(capitalize(msg));
    }

    public static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
