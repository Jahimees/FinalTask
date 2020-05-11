package by.epam.ft.entity;

public class CustomSelection {
    private String vacancyName;
    private int passedCount;
    private int failedCount;
    private double percent;

    public String getVacancyName() {
        return vacancyName;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }

    public int getPassedCount() {
        return passedCount;
    }

    public void setPassedCount(int passedCount) {
        this.passedCount = passedCount;
    }

    public int getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(int failedCount) {
        this.failedCount = failedCount;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
