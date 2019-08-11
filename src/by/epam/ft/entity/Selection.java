package by.epam.ft.entity;

import java.sql.Date;

public class Selection extends MyEntity {
    private int idSelection;
    private int idHr;
    private int idCandidate;
    private Date selectionDate;
    private int idVacancy;
    private String status;

    public void setIdVacancy(int idVacancy) {
        this.idVacancy = idVacancy;
    }

    public int getIdVacancy() { return idVacancy; }

    public Date getSelectionDate() {
        return selectionDate;
    }

    public void setSelectionDate(Date selectionDate) {
        this.selectionDate = selectionDate;
    }

    public int getIdCandidate() {
        return idCandidate;
    }

    public void setIdCandidate(int idCandidate) {
        this.idCandidate = idCandidate;
    }

    public int getIdHr() {
        return idHr;
    }

    public void setIdHr(int idHr) {
        this.idHr = idHr;
    }

    public int getIdSelection() {
        return idSelection;
    }

    public void setIdSelection(int idSelection) {
        this.idSelection = idSelection;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
