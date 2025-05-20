package com.example;

public class Event {
    
    private String id;
    private String type;
    private Repo repo; // Json
    private Payload payload; // Json

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Repo getRepo() {
        return this.repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }


    public Payload getPayload() {
        return this.payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }
    

}
