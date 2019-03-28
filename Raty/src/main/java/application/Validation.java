package application;

import javax.servlet.http.HttpServletRequest;

public class Validation {

    public Validation() {
    }

    public boolean input(HttpServletRequest request) {
        if (request.getParameter("wnioskowanaKwotaKredytu").equals("0") || request.getParameter("iloscRat").equals("0")
                || request.getParameter("wnioskowanaKwotaKredytu").isEmpty()
                || request.getParameter("iloscRat").isEmpty() || request.getParameter("oprocentowanie").equals("0")
                || request.getParameter("oplataStala").isEmpty() || request.getParameter("rodzajRat").equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public double ceil(double ceil) {
        return Math.ceil(ceil * 100) / 100;
    }
}
