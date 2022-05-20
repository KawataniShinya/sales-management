package com.sales.domain.logging;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;

public class InsertTimestamp {
    Date date;
    private final int digitsStringTimestamp = 23;

    public void setDate(String stringTimestamp) {
        String fixedStringTimestamp = getZeroPaddingString(stringTimestamp);
        try {
            LocalDate.parse(fixedStringTimestamp, DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.SSS").withResolverStyle(ResolverStyle.STRICT));
            this.date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(fixedStringTimestamp);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String getZeroPaddingString(String stringTimestamp) {
        StringBuilder stringBuilderToFixStringTimestamp = new StringBuilder(stringTimestamp).append("000");
        stringBuilderToFixStringTimestamp.setLength(this.digitsStringTimestamp);
        String fixedStringTimestamp = stringBuilderToFixStringTimestamp.toString();
        return fixedStringTimestamp;
    }

    public Timestamp getDateFormatTimestamp() {
        return new Timestamp(this.date.getTime());
    }
}
