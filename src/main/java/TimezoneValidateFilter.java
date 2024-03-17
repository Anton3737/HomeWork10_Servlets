import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Filter;
import java.util.regex.Pattern;

@WebFilter("/*")
public class TimezoneValidateFilter extends HttpFilter {

    private static final Pattern TIMEZONE_PATTERN = Pattern.compile("^-?1[0-3]|[+-]?1[0-4]$");

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String timezone = httpRequest.getParameter("timezone");

        if (timezone == null || !isValidTimezone(timezone)) {

        }else {
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpResponse.setContentType("text/html; charset=utf-8");
            PrintWriter printWriter = httpResponse.getWriter();
            printWriter.write(" <style>" +
                    "body { background-color: gray;}" +
                    "h1 {" +
                    "font-size: 50px;\n" +
                    "text-align: center;\n" +
                    "color: yellow;\n" +
                    "margin-top: 40%;\n" +
                    "}" +
                    "</style>");

            printWriter.write("<h1>" + "Invalid timezone " + "<br>" + "Status code " + httpResponse.getStatus() + "</h1>");
            printWriter.close();

            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    private boolean isValidTimezone(String timezone) {
        return TIMEZONE_PATTERN.matcher(timezone).matches();
    }

}
