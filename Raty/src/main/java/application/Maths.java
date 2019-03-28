package application;

import application.servlets.Pdf;

import java.io.PrintWriter;

public class Maths {

    Bank bank;
    Validation val = new Validation();
    PrintWriter pri;
    Pdf pdf = new Pdf();

    public Maths(Bank b, PrintWriter p) {
        bank = b;
        pri = p;
    }

    public Maths(Bank b, Pdf p) {
        bank = b;
        pdf = p;
    }

    public void constant(boolean type) {

        double q = 1 + ((1 / 12.0) * (bank.getInterest() / 100)), TempAmount = bank.getAmount(), Installment = bank.getAmount() * (java.lang.Math.pow(q, bank.getNumberOfInstallments()) * (q - 1))
                / ((java.lang.Math.pow(q, bank.getNumberOfInstallments())) - 1), sumInterest = 0, sumAmount = 0;
        for (int i = 1; i <= bank.getNumberOfInstallments(); i++) {
            double Interest = TempAmount * ((bank.getInterest() / 100) / 12), Amount = Installment - Interest;
            if (type) {
                Output.constOutInstallments(pri, i, Interest, Amount, bank.getFixedFee(), (Installment + bank.getFixedFee()));
            } else {
                Output.constOutPdf(pdf, i, Interest, Amount, bank.getFixedFee(), (Installment + bank.getFixedFee()));
            }
            TempAmount = TempAmount - (Installment - Interest);
            sumInterest = sumInterest + Interest;
            sumAmount = sumAmount + Amount;
        }
        if (type) {
            Output.constOutInstallmentsEnd(pri, sumInterest, sumAmount, (bank.getFixedFee() * bank.getNumberOfInstallments()),
                    (sumInterest + sumAmount + (bank.getFixedFee() * bank.getNumberOfInstallments())));
        } else {
            Output.constOutPdfEnd(pdf, sumInterest, sumAmount, (bank.getFixedFee() * bank.getNumberOfInstallments()),
                    (sumInterest + sumAmount + (bank.getFixedFee() * bank.getNumberOfInstallments())));
        }
    }

    public void decreasing(boolean type) {
        double TempAmount = bank.getAmount(), Installment = bank.getAmount() / bank.getNumberOfInstallments(), sumq = 0;
        for (int i = 1; i <= bank.getNumberOfInstallments(); i++) {
            double q = (1.0 / 12.0) * (bank.getInterest() / 100) * TempAmount;
            if (type)
                Output.constOutInstallments(pri, i, q, Installment, bank.getFixedFee(), (Installment + q + bank.getFixedFee()));
            else Output.constOutPdf(pdf, i, q, Installment, bank.getFixedFee(), (Installment + q + bank.getFixedFee()));
            TempAmount = TempAmount - Installment;
            sumq = sumq + q;
        }
        if (type)
            Output.constOutInstallmentsEnd(pri, sumq, bank.getAmount(), (bank.getFixedFee() * bank.getNumberOfInstallments()), (sumq + bank.getAmount()
                    + (bank.getFixedFee() * bank.getNumberOfInstallments())));
        else
            Output.constOutPdfEnd(pdf, sumq, bank.getAmount(), (bank.getFixedFee() * bank.getNumberOfInstallments()), (sumq + bank.getAmount()
                    + (bank.getFixedFee() * bank.getNumberOfInstallments())));
    }
}
