package charity.charity.models;

import java.time.LocalDate;

public class Candidate {
    private int candidateId;
    private String name;
    private String title;
    private LocalDate dob;
    private int isCandidateActive;

    public Candidate() {
    }

    public Candidate(int candidateId, String name, String title, LocalDate dob) {
        this.candidateId = candidateId;
        this.name = name;
        this.title = title;
        this.dob = dob;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getIsCandidateActive() {
        return isCandidateActive;
    }

    public void setIsCandidateActive(int isCandidateActive) {
        this.isCandidateActive = isCandidateActive;
    }
}
