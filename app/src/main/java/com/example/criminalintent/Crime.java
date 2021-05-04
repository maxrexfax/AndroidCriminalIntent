package com.example.criminalintent;

import java.util.UUID;

public class Crime {
    private UUID mId;
    private String mTitle;

    public UUID getmId() {
        return mId;
    }

    public void setmId(UUID mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Crime() {
        // Генерирование уникального идентификатора
        mId = UUID.randomUUID();
    }
}