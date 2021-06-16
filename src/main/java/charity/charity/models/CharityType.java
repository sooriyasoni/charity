package charity.charity.models;

public class CharityType {
    private int charityTypeId;
    private String type;
    private String desciprtion;

    public CharityType() {
    }

    public CharityType(int charityTypeId, String type, String desciprtion) {
        this.charityTypeId = charityTypeId;
        this.type = type;
        this.desciprtion = desciprtion;
    }

    public int getCharityTypeId() {
        return charityTypeId;
    }

    public void setCharityTypeId(int charityTypeId) {
        this.charityTypeId = charityTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesciprtion() {
        return desciprtion;
    }

    public void setDesciprtion(String desciprtion) {
        this.desciprtion = desciprtion;
    }
}
