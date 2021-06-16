package charity.charity.models;

public class Member {

    private int memberId;
    private String firstName;
    private String lastName;
    private String occupation;
    private String phone;
    private String email;
    private String address;
    private int isMemberActive;

    public Member() {
    }

    public Member(int memberId, String firstName, String lastName, String occupation, String phone, String email, String address) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.occupation = occupation;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIsMemberActive() {
        return isMemberActive;
    }

    public void setIsMemberActive(int isMemberActive) {
        this.isMemberActive = isMemberActive;
    }
}