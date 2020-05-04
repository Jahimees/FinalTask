package by.epam.ft.constant;

/**
 * All prepared statement constants
 */
public abstract class PreparedConstant {

    //////////////////////////////SELECTION//////////////////
    //////////////////////////////GET///////////////////////
    public static final String GET_ALL_SELECTION = "SELECT * FROM selection;";
    public static final String GET_SELECTION_BY_VACANCY = "SELECT * FROM selection WHERE idVacancy=?;";
    public static final String GET_SELECTIONS_BY_VACANCY_AND_STATUS = "SELECT * FROM selection WHERE idVacancy=? AND status=?;";
    public static final String GET_SELECTION_BY_IDS = "SELECT idSelection FROM selection, candidate WHERE candidate.idCandidate=selection.idCandidate and candidate.idCandidate=? and idVacancy=?;";
    public static final String GET_SELECTION_BY_ID = "SELECT * FROM selection WHERE idSelection=?;";
    public static final String GET_SELECTION_BY_STATUS = "SELECT * FROM selection WHERE status=?;";
    public static final String GET_SELECTION_WITHOUT_HR = "SELECT * FROM selection WHERE idHR is null;";
    public static final String GET_SELECTION_BY_DATE = "SELECT * FROM selection where selectionDate=?;";
    public static final String GET_SELECTION_BY_REGISTRATION_DATE = "SELECT * FROM selection where registrationDate=?;";

    /////////////////////////////INSERT//////////////////////
    public static final String INSERT_INTO_SELECTION = "INSERT INTO selection (idCandidate, idVacancy, status, registrationDate) VALUES (?, ?, ?, ?);";

    /////////////////////////////DELETE///////////////////////
    public static final String DELETE_SELECTION_BY_ID = "DELETE FROM selection WHERE idSelection=?";

    ///////////////////////////UPDATE////////////////////////
    public static final String UPDATE_SELECTION = "UPDATE selection SET idHr=?, selectionDate=?, status=? WHERE idSelection=?";



    ///////////////////////////VACANCY///////////////////////
    ///////////////////////////GET///////////////////////////
    public static final String GET_ALL_VACANCY = "SELECT * FROM vacancy;";
    public static final String GET_VACANCY = "SELECT * FROM vacancy WHERE idVacancy=?;";
    public static final String GET_VACANCIES_BY_NAME = "SELECT * FROM vacancy where name=?;";

    public static final String GET_ALL_VACANCIES_WITH_COUNT = "select vacancy.idVacancy, count(*), name, description" +
            " from vacancy join selection on " +
            "vacancy.idVacancy=selection.idVacancy group by vacancy.idVacancy order by count(*) desc;";
    public static final String GET_VACANCIES_WITH_COUNT_AND_RANGE = "select vacancy.idVacancy, count(*), name, description" +
            " from vacancy join selection on " +
            "vacancy.idVacancy=selection.idVacancy where selection.registrationDate>? and selection.registrationDate<? " +
            "group by vacancy.idVacancy order by count(*) desc;";
    public static final String GET_OPENED_VACANCIES_WITH_COUNT = "select vacancy.idVacancy, count(selection.idVacancy), name, description, vacancy.status" +
            " from vacancy left outer join selection on " +
            "vacancy.idVacancy=selection.idVacancy where vacancy.status = 'opened' group by vacancy.idVacancy order by count(selection.idVacancy) desc;";
    public static final String GET_CLOSED_VACANCIES_WITH_COUNT = "select vacancy.idVacancy, count(selection.idVacancy), name, description, vacancy.status" +
            " from vacancy left outer join selection on " +
            "vacancy.idVacancy=selection.idVacancy where vacancy.status = 'closed' group by vacancy.idVacancy order by count(selection.idVacancy) desc;";
    public static final String GET_CANDIDATE_VACANCIES = "SELECT idSelection, idHR, selection.idCandidate, selectionDate," +
            " idVacancy, status, registrationDate FROM selection, candidate WHERE candidate.idCandidate=selection.idCandidate and candidate.idCandidate=?;";
    public static final String GET_HR_VACANCIES = "SELECT idSelection, hr.idHR, selection.idCandidate, selectionDate," +
            " idVacancy, status, registrationDate FROM selection, hr WHERE hr.idHR=selection.idHR and hr.idHr=?;";

    /////////////////////////////INSERT//////////////////////
    public static final String INSERT_INTO_VACANCY = "INSERT INTO vacancy (name, description) VALUES (?, ?)";

    /////////////////////////////UPDATE//////////////////////
    public static final String CLOSE_VACANCY_BY_ID = "UPDATE vacancy SET status = 'closed' WHERE idVacancy=?";
    public static final String OPEN_VACANCY_BY_ID = "UPDATE vacancy SET status = 'opened' WHERE idVacancy=?";
    public static final String UPDATE_VACANCY = "UPDATE vacancy SET name=?, description=? WHERE idVacancy=?";



    ///////////////////////////ACCOUNT///////////////////////
    ///////////////////////////GET///////////////////////////
    public static final String GET_ACCOUNT = "SELECT * FROM account WHERE idAccount=?;";
    public static final String GET_ACCOUNT_BY_NAMES = "SELECT * FROM account WHERE name=? and surname=?;";
    public static final String GET_PASSWORD_BY_LOGIN = "SELECT password FROM account WHERE login=?;";
    public static final String GET_ID_ACCOUNT_BY_LOGIN = "SELECT idAccount FROM account WHERE login=?;";

    public static final String GET_ACCOUNT_BY_HR_ID = "SELECT account.idAccount, account.login, account.password," +
            " account.name, account.surname, account.birthday, account.email, account.isConfirmed, account.resume" +
            " FROM account, hr WHERE hr.idHR=? and hr.idAccount=account.idAccount;";
    public static final String GET_ACCOUNT_BY_CANDIDATE_ID = "SELECT account.idAccount, account.login, account.password," +
            " account.name, account.surname, account.birthday, account.email, account.isConfirmed, account.resume" +
            " FROM account, candidate WHERE candidate.idCandidate=? and candidate.idAccount=account.idAccount;";

    /////////////////////////////INSERT//////////////////////
    public static final String INSERT_INTO_ACCOUNT = "INSERT INTO account(login, password, name, surname, birthday, email) VALUES (?, ?, ?, ?, ?, ?);";

    /////////////////////////////UPDATE//////////////////////
    public static final String CHANGE_PASSWORD = "UPDATE account SET password=? WHERE idAccount=?";
    public static final String CHANGE_EMAIL_STATUS = "UPDATE account SET isConfirmed=? WHERE idAccount=?;";
    public static final String UPDATE_ACCOUNT = "UPDATE account SET name=?, surname=?, email=?, resume=? WHERE idAccount=?;";


    ///////////////////////////HR////////////////////////////
    ///////////////////////////GET///////////////////////////
    public static final String GET_HR_BY_ID_ACCOUNT = "SELECT * FROM hr WHERE idAccount=?;";



    ///////////////////////////CANDIDATE/////////////////////
    ///////////////////////////GET///////////////////////////
    public static final String GET_CANDIDATE_BY_ID = "SELECT * FROM candidate WHERE idCandidate=?;";

    public static final String GET_CANDIDATE_BY_ACCOUNT_ID = "SELECT candidate.idCandidate, candidate.idAccount FROM" +
            " account, candidate WHERE candidate.idAccount=account.idAccount and candidate.idAccount=?;";
    public static final String GET_CANDIDATES_IDS_BY_VACANCY_WITH_WAITING_STATUS = "select idCandidate from selection where idVacancy=? " +
            "and (status='Заявка на рассмотрении' or status='Ожидание собеседования');";

    /////////////////////////////INSERT////////////////////
    public static final String INSERT_INTO_CANDIDATE = "INSERT INTO candidate(idAccount) VALUES (?)";
}
