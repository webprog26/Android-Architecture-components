package com.example.webprog26.androidarchitecturecomponents;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.Nullable;

import java.util.Date;

/**
 * Created by webprog26 on 20.04.18.
 */

@Entity
public class BorrowModel {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private final String itemName;
    private final String personName;
    @TypeConverters(DateConverter.class)
    private Date borrowDate;

    public BorrowModel(final String itemName, final String personName, final Date borrowDate) {
        this.itemName = itemName;
        this.personName = personName;
        this.borrowDate = borrowDate;
    }

    public String getItemName() {
        return itemName;
    }

    public String getPersonName() {
        return personName;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    @Nullable
    public static BorrowModel fromUserInput(final String itemName, final String personName, final Date borrowDate) {
        return itemName != null && personName != null && borrowDate != null
                ? new BorrowModel(itemName, personName, borrowDate)
                : null;
    }
}
