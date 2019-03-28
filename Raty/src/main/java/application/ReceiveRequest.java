package application;

import application.Bank;

import javax.servlet.http.HttpServletRequest;

public class ReceiveRequest {

    public ReceiveRequest() {
    }

    public Bank receive(HttpServletRequest request) {
        Bank bank = new Bank();
        bank.setAmount(Float.parseFloat(request.getParameter("wnioskowanaKwotaKredytu")));
        bank.setFixedFee(Float.parseFloat(request.getParameter("oplataStala")));
        bank.setInterest(Float.parseFloat(request.getParameter("oprocentowanie")));
        bank.setNumberOfInstallments(Integer.parseInt(request.getParameter("iloscRat")));
        bank.setLoanType(request.getParameter("rodzajRat"));
        return bank;
    }
}
