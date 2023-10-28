package org.qtstu.webapp.Models;

import java.time.Duration;
import java.time.ZonedDateTime;

public record VideoRecord(Long id, Long userUploader,String name, Long duration, Long uploadDate) {
}
