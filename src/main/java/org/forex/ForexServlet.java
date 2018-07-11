package org.forex;

import io.prometheus.client.Counter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import static java.lang.String.format;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

public class ForexServlet extends HttpServlet {

    private static final Map<String, Double> currencies = Map.of(
            "USD", 1.0,
            "EUR", 0.86,
            "GBP", 0.76,
            "JPY", 111.99
    );
    private Counter successRequestCounter;
    private Counter badRequestCounter;
    private Counter failureRequestCounter;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        // TODO create counter
        /* successRequestCounter = Counter.build()
                .name("forex_server_success_total_requests")
                .help("Total successful request counter.")
                .register();

        badRequestCounter = Counter.build()
                .name("forex_server_total_bad_requests")
                .help("Total bad request counter.")
                .register();

        failureRequestCounter = Counter.build()
                .name("forex_server_total_failure_requests")
                .help("Total failed request counter.")
                .register();
        */
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if (req.getPathInfo() == null) {
            // TODO inc counter
            //  badRequestCounter.inc();
            resp.setStatus(SC_BAD_REQUEST);
        } else {
            String[] split = req.getPathInfo().split("/");

            String ccy = split[1];

            if (currencies.containsKey(ccy)) {
                // TODO inc counter
                // successRequestCounter.inc();
                String response = format("{\"rate\": %f}", currencies.get(ccy));
                resp.setContentType("text/json");
                resp.setContentLength(response.length());
                PrintWriter out = resp.getWriter();
                out.println(response);
                out.close();
                out.flush();
            } else {
                // TODO inc counter
                // failureRequestCounter.inc();
                resp.setStatus(SC_NOT_FOUND);
            }
        }
    }
}
