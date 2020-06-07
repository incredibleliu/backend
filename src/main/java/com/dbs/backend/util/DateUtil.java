package com.dbs.backend.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DateUtil {
	
	//2020-06-07T09:12:50.024+00:00
	//2020-06-07 10:13:27.061
	
	private static Logger log = LoggerFactory.getLogger(DateUtil.class);
	
	public String getTimeStringOfNow() {
		
	    LocalDateTime myDateObj = LocalDateTime.now();
	    log.info("Before formatting: {}", myDateObj);
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
	    log.info("After formatting: {}", formattedDate);
	    return formattedDate;
		
	}
	
	public Timestamp getTimestamp(String str) {
		
		log.info("input: {}", str);
		OffsetDateTime odt = OffsetDateTime.parse( str );
		Timestamp ts = Timestamp.valueOf(odt.atZoneSameInstant(ZoneId.of("Z")).toLocalDateTime());
		log.info("output: {}", ts);
		return ts;
		
		
	}

}
