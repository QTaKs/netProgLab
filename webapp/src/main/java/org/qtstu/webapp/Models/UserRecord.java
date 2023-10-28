package org.qtstu.webapp.Models;

import java.time.ZonedDateTime;
import java.util.TimeZone;

public record UserRecord(Long id, Long registrationDate,String username) {
}
