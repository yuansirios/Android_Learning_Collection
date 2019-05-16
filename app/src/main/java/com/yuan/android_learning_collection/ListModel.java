package com.yuan.android_learning_collection;

public class ListModel {

    private String title;
    private String value;
    private String event;

    public ListModel(String title, String value, String event) {
        this.title = title;
        this.value = value;
        this.event = event;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
