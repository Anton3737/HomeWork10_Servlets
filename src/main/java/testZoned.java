import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class testZoned {

    public static String zonedTime(int zone) {

        ZonedDateTime currentZonedDateTime = ZonedDateTime.now(ZoneOffset.ofHours(zone));

        return currentZonedDateTime.toString();
    }


    public static void main(String[] args) {
        System.out.println(zonedTime(-2));
        System.out.println(zonedTime(-3));
        System.out.println(zonedTime(-4));
        System.out.println(zonedTime(-10));
        System.out.println(zonedTime(-12));

    }
}
