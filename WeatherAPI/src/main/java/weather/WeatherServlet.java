package weather;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

@WebServlet("/WeatherServlet")
public class WeatherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WeatherServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OWM owm = new OWM("ac9e450fd7d739ff0e37f1e37f476046");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String city = request.getParameter("city").trim().toString();
		CurrentWeather cwd = null;
		try {
			switch (city) {
			case "warszawa":
				cwd = owm.currentWeatherByCityId(756135);
				break;
			case "gdansk":
				cwd = owm.currentWeatherByCityId(3099434);
				break;
			case "krakow":
				cwd = owm.currentWeatherByCityId(3094802);
				break;
			case "wroclaw":
				cwd = owm.currentWeatherByCityId(3081368);
				break;
			case "poznan":
				cwd = owm.currentWeatherByCityId(7530858);
				break;
			case "lodz":
				cwd = owm.currentWeatherByCityId(3093133);
				break;
			case "katowice":
				cwd = owm.currentWeatherByCityId(3096472);
				break;
			default:
				cwd = owm.currentWeatherByCityId(756135);
				break;
			}
		} catch (APIException e) {
			e.printStackTrace();
		}
		if (cwd.hasRespCode() && cwd.getRespCode() == 200) {
			out.println("Miasto: " + cwd.getCityName());
		}
		if (cwd.hasMainData() && cwd.getMainData().hasTemp() ) {
			out.println("<br>Temperatura wynosi: " + (cwd.getMainData().getTemp() - 273.15) + " C");
		}
		if (cwd.hasMainData() && cwd.getMainData().hasPressure()) {
			out.println("<br>Cisnienie wynosi: " + cwd.getMainData().getPressure() + " hPa");
		}
		if (cwd.hasMainData() && cwd.hasCloudData()) {
			out.println("<br>Zachmurzenie wynosi: " + cwd.getCloudData().getCloud() + " %");
		}
		if (cwd.hasMainData() && cwd.hasWindData()) {
			out.println("<br>Wiatr wynosi: " + cwd.getWindData().getSpeed() + " m/s");
		}
	}

}
