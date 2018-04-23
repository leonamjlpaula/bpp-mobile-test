package br.com.leonam.bppmbiletest.objects;

public class Transaction {

    private String transactionId;
    private String  transactionDate;
    private String transactionFormattedDate;
    private String  transactionCurrency;
    private double transactionAmount;
    private String billingCurrency;
    private double billingAmount;
    private String transactionStatus;
    private String transactionName;
    private String merchantName;
    private String mccCode;
    private String mccDescription;

    public Transaction() {
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setTransactionFormattedDate(String transactionFormattedDate) {
        this.transactionFormattedDate = transactionFormattedDate;
    }

    public void setTransactionCurrency(String transactionCurrency) {
        this.transactionCurrency = transactionCurrency;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public void setBillingCurrency(String billingCurrency) {
        this.billingCurrency = billingCurrency;
    }

    public void setBillingAmount(double billingAmount) {
        this.billingAmount = billingAmount;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public void setMccCode(String mccCode) {
        this.mccCode = mccCode;
    }

    public void setMccDescription(String mccDescription) {
        this.mccDescription = mccDescription;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionFormattedDate() {
        return transactionFormattedDate;
    }

    public String getTransactionCurrency() {
        return transactionCurrency;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public String getBillingCurrency() {
        return billingCurrency;
    }

    public double getBillingAmount() {
        return billingAmount;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getMccCode() {
        return mccCode;
    }

    public String getMccDescription() {
        return mccDescription;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", transactionFormattedDate='" + transactionFormattedDate + '\'' +
                ", transactionCurrency='" + transactionCurrency + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", billingCurrency='" + billingCurrency + '\'' +
                ", billingAmount=" + billingAmount +
                ", transactionStatus='" + transactionStatus + '\'' +
                ", transactionName='" + transactionName + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", mccCode='" + mccCode + '\'' +
                ", mccDescription='" + mccDescription + '\'' +
                '}';
    }
}
