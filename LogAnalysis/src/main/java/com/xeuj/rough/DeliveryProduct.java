package com.xeuj.rough;

public class DeliveryProduct {
    private String productId;
    private int totalAmount;
    private int amountToBePaid;
    private int OfferAmount;
    private int oldAmount;
    private String status;
    private String productName;

    public DeliveryProduct(String productId, int totalAmount) {
        this.productId = productId;
        this.totalAmount = totalAmount;
    }
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getAmountToBePaid() {
        return amountToBePaid;
    }

    public void setAmountToBePaid(int amountToBePaid) {
        this.amountToBePaid = amountToBePaid;
    }

    public int getOfferAmount() {
        return OfferAmount;
    }

    public void setOfferAmount(int offerAmount) {
        OfferAmount = offerAmount;
    }

    public int getOldAmount() {
        return oldAmount;
    }

    public void setOldAmount(int oldAmount) {
        this.oldAmount = oldAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
