package com.digitoon.batman.room.rating;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.digitoon.batman.helper.Constant;
import com.digitoon.batman.room.detail.DetailRoom;

import java.io.Serializable;

@Entity(tableName = Constant.table_rating)
public class Rating implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rateId")
    private int rateId;
    @ForeignKey
            (entity = DetailRoom.class,
                    parentColumns = "id_detail",
                    childColumns = "id_fk",
                    onDelete = CASCADE
            )
    private long id_fk;

    public long getId_fk() {
        return id_fk;
    }

    public void setId_fk(long id_fk) {
        this.id_fk = id_fk;
    }

    public int getRateId() {
        return rateId;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
    }
    //-----------------------

    @ColumnInfo(name = "Source")
    private String Source;
    @ColumnInfo(name = "Value")
    private String Value;

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
