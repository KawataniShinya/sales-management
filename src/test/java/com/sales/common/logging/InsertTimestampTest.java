package com.sales.common.logging;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class InsertTimestampTest {

    private final int digitsStringTimestamp = 23;

    @Test
    public void setDateTestNormal01() {
        String dateString = "2022-05-20 16:46:36.934";
        InsertTimestamp insertTimestamp = new InsertTimestamp();
        insertTimestamp.setDate(dateString);
        Timestamp timestamp = insertTimestamp.getDateFormatTimestamp();
        assertEquals(this.getZeroPaddingString(dateString), this.getZeroPaddingString(timestamp.toString()));
    }

    @Test
    public void setDateTestNormal02() {
        String dateString = "2022-05-20 16:46:36.930";
        InsertTimestamp insertTimestamp = new InsertTimestamp();
        insertTimestamp.setDate(dateString);
        Timestamp timestamp = insertTimestamp.getDateFormatTimestamp();
        assertEquals(this.getZeroPaddingString(dateString), this.getZeroPaddingString(timestamp.toString()));
    }

    @Test
    public void setDateTestNormal03() {
        String dateString = "2022-05-20 16:46:36.093";
        InsertTimestamp insertTimestamp = new InsertTimestamp();
        insertTimestamp.setDate(dateString);
        Timestamp timestamp = insertTimestamp.getDateFormatTimestamp();
        assertEquals(this.getZeroPaddingString(dateString), this.getZeroPaddingString(timestamp.toString()));
    }

    @Test
    public void setDateTestNormal04() {
        String dateString = "2022-05-20 16:46:36.93";
        InsertTimestamp insertTimestamp = new InsertTimestamp();
        insertTimestamp.setDate(dateString);
        Timestamp timestamp = insertTimestamp.getDateFormatTimestamp();
        assertEquals(this.getZeroPaddingString(dateString), this.getZeroPaddingString(timestamp.toString()));
    }

    @Test
    public void setDateTestNormal05() {
        String dateString = "2022-05-20 16:46:36.9";
        InsertTimestamp insertTimestamp = new InsertTimestamp();
        insertTimestamp.setDate(dateString);
        Timestamp timestamp = insertTimestamp.getDateFormatTimestamp();
        assertEquals(this.getZeroPaddingString(dateString), this.getZeroPaddingString(timestamp.toString()));
    }

    @Test
    public void setDateTestException01() {
        String dateString = "2022-05-32 16:46:36.934";
        InsertTimestamp insertTimestamp = new InsertTimestamp();
        try {
            insertTimestamp.setDate(dateString);
            fail();
        } catch(DateTimeParseException e) {
            assertTrue(true);
        }
    }

    @Test
    public void setDateTestException02() {
        String dateString = "202A-05-20 16:46:36.934";
        InsertTimestamp insertTimestamp = new InsertTimestamp();
        try {
            insertTimestamp.setDate(dateString);
            fail();
        } catch(DateTimeParseException e) {
            assertTrue(true);
        }
    }

    private String getZeroPaddingString(String stringTimestamp) {
        StringBuilder stringBuilderToFixStringTimestamp = new StringBuilder(stringTimestamp).append("000");
        stringBuilderToFixStringTimestamp.setLength(this.digitsStringTimestamp);
        String fixedStringTimestamp = stringBuilderToFixStringTimestamp.toString();
        return fixedStringTimestamp;
    }
}