package ru.bestprice.backend.exlibrary.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Receipt {

    @JsonProperty("ecashTotalSum")
    private int ecashTotalSum;

    @JsonProperty("userInn")
    private String userInn;

    @JsonProperty("items")
    private List<Item> items = null;

    @JsonProperty("requestNumber")
    private int requestNumber;

    @JsonProperty("provisionSum")
    private int provisionSum;

    @JsonProperty("fiscalSign")
    private int fiscalSign;

    @JsonProperty("fiscalDocumentNumber")
    private int fiscalDocumentNumber;

    @JsonProperty("operationType")
    private int operationType;

    @JsonProperty("taxationType")
    private int taxationType;

    @JsonProperty("messageFiscalSign")
    private long messageFiscalSign;

    @JsonProperty("fiscalDriveNumber")
    private String fiscalDriveNumber;

    @JsonProperty("machineNumber")
    private String machineNumber;

    @JsonProperty("cashTotalSum")
    private int cashTotalSum;

    @JsonProperty("internetSign")
    private int internetSign;

    @JsonProperty("shiftNumber")
    private int shiftNumber;

    @JsonProperty("operator")
    private String operator;

    @JsonProperty("retailPlace")
    private String retailPlace;

    @JsonProperty("dateTime")
    private String dateTime;

    @JsonProperty("ndsNo")
    private int ndsNo;

    @JsonProperty("totalSum")
    private int totalSum;

    @JsonProperty("fiscalDocumentFormatVer")
    private int fiscalDocumentFormatVer;

    @JsonProperty("rawData")
    private String rawData;

    @JsonProperty("prepaidSum")
    private int prepaidSum;

    @JsonProperty("paymentAgentType")
    private int paymentAgentType;

    @JsonProperty("sellerAddress")
    private String sellerAddress;

    @JsonProperty("fnsSite")
    private String fnsSite;

    @JsonProperty("user")
    private String user;

    @JsonProperty("receiptCode")
    private int receiptCode;

    @JsonProperty("creditSum")
    private int creditSum;

    @JsonProperty("kktRegId")
    private String kktRegId;

    @JsonProperty("buyerAddress")
    private String buyerAddress;

    public int getEcashTotalSum() {
        return ecashTotalSum;
    }

    public void setEcashTotalSum(int ecashTotalSum) {
        this.ecashTotalSum = ecashTotalSum;
    }

    public String getUserInn() {
        return userInn;
    }

    public void setUserInn(String userInn) {
        this.userInn = userInn;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(int requestNumber) {
        this.requestNumber = requestNumber;
    }

    public int getProvisionSum() {
        return provisionSum;
    }

    public void setProvisionSum(int provisionSum) {
        this.provisionSum = provisionSum;
    }

    public int getFiscalSign() {
        return fiscalSign;
    }

    public void setFiscalSign(int fiscalSign) {
        this.fiscalSign = fiscalSign;
    }

    public int getFiscalDocumentNumber() {
        return fiscalDocumentNumber;
    }

    public void setFiscalDocumentNumber(int fiscalDocumentNumber) {
        this.fiscalDocumentNumber = fiscalDocumentNumber;
    }

    public int getOperationType() {
        return operationType;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }

    public int getTaxationType() {
        return taxationType;
    }

    public void setTaxationType(int taxationType) {
        this.taxationType = taxationType;
    }

    public long getMessageFiscalSign() {
        return messageFiscalSign;
    }

    public void setMessageFiscalSign(long messageFiscalSign) {
        this.messageFiscalSign = messageFiscalSign;
    }

    public String getFiscalDriveNumber() {
        return fiscalDriveNumber;
    }

    public void setFiscalDriveNumber(String fiscalDriveNumber) {
        this.fiscalDriveNumber = fiscalDriveNumber;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    public int getCashTotalSum() {
        return cashTotalSum;
    }

    public void setCashTotalSum(int cashTotalSum) {
        this.cashTotalSum = cashTotalSum;
    }

    public int getInternetSign() {
        return internetSign;
    }

    public void setInternetSign(int internetSign) {
        this.internetSign = internetSign;
    }

    public int getShiftNumber() {
        return shiftNumber;
    }

    public void setShiftNumber(int shiftNumber) {
        this.shiftNumber = shiftNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRetailPlace() {
        return retailPlace;
    }

    public void setRetailPlace(String retailPlace) {
        this.retailPlace = retailPlace;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getNdsNo() {
        return ndsNo;
    }

    public void setNdsNo(int ndsNo) {
        this.ndsNo = ndsNo;
    }

    public int getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }

    public int getFiscalDocumentFormatVer() {
        return fiscalDocumentFormatVer;
    }

    public void setFiscalDocumentFormatVer(int fiscalDocumentFormatVer) {
        this.fiscalDocumentFormatVer = fiscalDocumentFormatVer;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public int getPrepaidSum() {
        return prepaidSum;
    }

    public void setPrepaidSum(int prepaidSum) {
        this.prepaidSum = prepaidSum;
    }

    public int getPaymentAgentType() {
        return paymentAgentType;
    }

    public void setPaymentAgentType(int paymentAgentType) {
        this.paymentAgentType = paymentAgentType;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getFnsSite() {
        return fnsSite;
    }

    public void setFnsSite(String fnsSite) {
        this.fnsSite = fnsSite;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(int receiptCode) {
        this.receiptCode = receiptCode;
    }

    public int getCreditSum() {
        return creditSum;
    }

    public void setCreditSum(int creditSum) {
        this.creditSum = creditSum;
    }

    public String getKktRegId() {
        return kktRegId;
    }

    public void setKktRegId(String kktRegId) {
        this.kktRegId = kktRegId;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }
}
