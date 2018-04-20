package com.example.webprog26.androidarchitecturecomponents;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by webprog26 on 20.04.18.
 */

public class DateConverter {

    @TypeConverter
    public static Date toDate(final Long timeStamp) {
        return timeStamp == null ? null : new Date(timeStamp);
    }

    @TypeConverter
    public static Long toTimeStamp(final Date date) {
        return date == null ? null : date.getTime();
    }
}
