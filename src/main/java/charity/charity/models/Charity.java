package charity.charity.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Charity {
    private int charityId;
    private Member member;
    private Candidate candidate;
    private Payment payment;
    private CharityType charityType;
    private BigDecimal donatedAmount;
    private LocalDate date;

    public Charity(int charityId, Member member, Candidate candidate, Payment payment, CharityType charityType, BigDecimal donatedAmount, LocalDate date) {
        this.charityId = charityId;
        this.member = member;
        this.candidate = candidate;
        this.payment = payment;
        this.charityType = charityType;
        this.donatedAmount = donatedAmount;
        this.date = date;
    }

    public Charity() {
    }

    public int getCharityId() {
        return charityId;
    }

    public void setCharityId(int charityId) {
        this.charityId = charityId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public BigDecimal getDonatedAmount() {
        return donatedAmount;
    }

    public void setDonatedAmount(BigDecimal donatedAmount) {
        this.donatedAmount = donatedAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public CharityType getCharityType() {
        return charityType;
    }

    public void setCharityType(CharityType charityType) {
        this.charityType = charityType;
    }
}
