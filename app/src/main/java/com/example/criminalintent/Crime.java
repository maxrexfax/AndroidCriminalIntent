package com.example.criminalintent;

import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID _Id;
    private String _Title;
    private Date _Date;
    private boolean _Solved;

    public Crime() {
        // Генерирование уникального идентификатора
        _Id = UUID.randomUUID();
        _Date = new Date();
    }

    public UUID getId() {
        return _Id;
    }

    public void setId(UUID mId) {
        this._Id = mId;
    }

    public String getTitle() {
        return _Title;
    }

    public void setTitle(String mTitle) {
        this._Title = mTitle;
    }

    public Date getDate() {
        return _Date;
    }

    public void setDate(Date mDate) {
        this._Date = mDate;
    }

    public boolean isSolved() {
        return _Solved;
    }

    public void setSolved(boolean mSolved) {
        this._Solved = mSolved;
    }

}