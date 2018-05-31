package ConsumersProducers;

import Models.*;

/**
 * This class represents one line in the JList in Loan Broker. This class stores
 * all objects that belong to one LoanRequest: - LoanRequest, -
 * BankInterestRequest, and - BankInterestReply. Use objects of this class to
 * add them to the JList.
 *
 * @author 884294
 *
 */
class JListLine {

    private CheckFinanciering checkFinanciering;
    private CheckReply checkReply;
    private CheckedFinanciering checkedFinanciering;
    private Financiering financiering;
    private FinancieringsReply financieringsReply;
    private TypeFinanciering typeFinanciering;

    public JListLine(Financiering financiering) {
        this.setFinanciering(financiering);
    }
    
    public JListLine(CheckReply financiering) {
        this.setCheckReply(financiering);
    }

    public CheckFinanciering getCheckFinanciering() {
        return checkFinanciering;
    }

    public void setCheckFinanciering(CheckFinanciering checkFinanciering) {
        this.checkFinanciering = checkFinanciering;
    }

    public CheckReply getCheckReply() {
        return checkReply;
    }

    public void setCheckReply(CheckReply checkReply) {
        this.checkReply = checkReply;
    }

    public CheckedFinanciering getCheckedFinanciering() {
        return checkedFinanciering;
    }

    public void setCheckedFinanciering(CheckedFinanciering checkedFinanciering) {
        this.checkedFinanciering = checkedFinanciering;
    }

    public Financiering getFinanciering() {
        return financiering;
    }

    public void setFinanciering(Financiering financiering) {
        this.financiering = financiering;
    }

    public FinancieringsReply getFinancieringsReply() {
        return financieringsReply;
    }

    public void setFinancieringsReply(FinancieringsReply financieringsReply) {
        this.financieringsReply = financieringsReply;
    }

    public TypeFinanciering getTypeFinanciering() {
        return typeFinanciering;
    }

    public void setTypeFinanciering(TypeFinanciering typeFinanciering) {
        this.typeFinanciering = typeFinanciering;
    }

    @Override
    public String toString() {
        return financiering.toString() + " || " + ((financieringsReply != null) ? financieringsReply.toString() : "waiting for reply...");
    }
}
