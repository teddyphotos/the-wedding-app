package com.example.tulips;

public class EventItem {
    String title;
    String isRelegious;
    String description;
    String venue;
    String scheduledTime;
    String dress;
    String location;
    String date;
    String eventID;
    boolean isRecietful;




    EventItem(String eventID, String title, String isRelegious, String description, String venue, String scheduledTime, String dress, String date, String location, boolean isRecietful){
        this.eventID = eventID;
        this.title = title;
        this.isRelegious = isRelegious;
        this.description = description;
        this.venue = venue;
        this.scheduledTime = scheduledTime;
        this.dress = dress;
        this.date = date;
        this.location = location;
        this.isRecietful = isRecietful;
    }

    public boolean isRecietful() {
        return isRecietful;
    }

    public void setRecietful(boolean recietful) {
        isRecietful = recietful;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getRelegious() {
        return isRelegious;
    }

    public String getDescription() {
        return description;
    }

    public String getVenue() {
        return venue;
    }

    public String getScheduledTime() {
        return scheduledTime;
    }

    public String getDress() {
        return dress;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRelegious(String relegious) {
        isRelegious = relegious;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setScheduledTime(String scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public void setDress(String dress) {
        this.dress = dress;
    }
}
