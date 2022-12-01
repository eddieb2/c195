package utils;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;


/**
 * Methods for timezone conversion.
 */
public class TimeConverter {

    private static final ZoneId originZoneId = ZoneId.of("Etc/UTC"); // UTC timezone
    private static final ZoneId targetZoneId = ZoneId.systemDefault(); // Local timezone

    /**
     * Converts from UTC to a local timezone.
     * @param utcLDT
     * @return
     */
    public static LocalDateTime utcToLocalLDT(LocalDateTime utcLDT) {
        ZonedDateTime utcZDT = utcLDT.atZone(originZoneId);
        ZonedDateTime localZDT = utcZDT.withZoneSameInstant(targetZoneId);

        return localZDT.toLocalDateTime();
    }

    /**
     * Converts from a local timezone to UTC
     * @param localLDT
     * @return
     */
    public static LocalDateTime localToUtcLDT(LocalDateTime localLDT) {
        ZonedDateTime localZDT = localLDT.atZone(targetZoneId);
        ZonedDateTime utcZDT = localZDT.withZoneSameInstant(originZoneId);

        return utcZDT.toLocalDateTime();
    }

}


