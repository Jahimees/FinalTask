package by.epam.ft.entity;

public class Vacancy extends MyEntity {
    private int idVacancy;
    private String name;
    private String description;
    private int candidateCount;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdVacancy() {
        return idVacancy;
    }

    public void setIdVacancy(int idVacancy) {
        this.idVacancy = idVacancy;
    }

    public int getCandidateCount() { return candidateCount; }

    public void setCandidateCount(int candidateCount) { this.candidateCount = candidateCount; }
}
