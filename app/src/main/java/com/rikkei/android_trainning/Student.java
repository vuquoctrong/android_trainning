package com.rikkei.android_trainning;

public class Student {
    private String mName;
    private int birthYear;

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
    public Student(String mName, int birthYear) {
        this.mName = mName;
        this.birthYear = birthYear;
    }

    public String getmName() {
        return mName;
    }

    public int getBirthYear() {
        return birthYear;
    }
}
