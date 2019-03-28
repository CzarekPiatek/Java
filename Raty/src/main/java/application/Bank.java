package application;

public class Bank {
    float amount = 0;
    int numberOfInstallments = 0;
    float interest = 0;
    float fixedFee = 0;
    String loanType = "";

    public Bank() {
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(int numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public float getFixedFee() {
        return fixedFee;
    }

    public void setFixedFee(float fixedFee) {
        this.fixedFee = fixedFee;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }
}
