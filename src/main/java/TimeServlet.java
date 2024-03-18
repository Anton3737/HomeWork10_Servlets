import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {


    public static String showTimeNow(String timezone) {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime;

        if (timezone == null || timezone.isEmpty()) {
            zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("UTC"));
            timezone = "<br>It's your local time !";
        } else {
            try {

                int getTimeZone = Integer.parseInt(timezone.replace("UTC", "").trim());
                zonedDateTime = ZonedDateTime.now(ZoneOffset.ofHours(getTimeZone));

            } catch (NumberFormatException e) {
                return "Invalid timezone format";
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        return zonedDateTime.format(formatter) + " " + timezone;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String timezone = req.getParameter("timezone");

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(" <style>" +
                "body { background-color: gray;}" +
                "h1 {" +
                "font-size: 50px;\n" +
                "text-align: center;\n" +
                "color: chartreuse;\n" +
                "margin-top: 25%;\n" +
                "}" +
                "</style>");

        printWriter.write("<h1>" + showTimeNow(timezone) + "</h1>");
        printWriter.close();
    }
}

