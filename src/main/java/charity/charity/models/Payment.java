package charity.charity.models;

public class Payment {
    private int paymentId;
    private String type;
    private String status;

    public Payment(int paymentId , String type, String status) {
        this.paymentId = paymentId;
        this.type = type;
        this.status = status;
    }

    public Payment() {
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
