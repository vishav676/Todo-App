package com.vishavlakhtia.todo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Task {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
    public Task(String title)
    {
        this.title = title;
    }
    private Task(Parcel in) {
        title = in.readString();
    }
}
