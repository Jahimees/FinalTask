package by.epam.ft.constant;

/**
 * All prepared statement constants
 */
public abstract class PreparedConstant {

    //////////////////////////////GET////////////////////////
    public static final String GET_ALL_SELECTION = "SELECT * FROM selection;";
    public static final String GET_ALL_VACANCY = "SELECT * FROM vacancy;";
    public static final String GET_ACCOUNT = "SELECT * FROM account WHERE idAccount=?;";
    public static final String GET_ACCOUNT_BY_CANDIDATE_ID = "SELECT account.idAccount, account.login, account.password," +
            " account.name, account.surname, account.birthday, account.email" +
            " FROM account, candidate WHERE candidate.idCandidate=? and candidate.idAccount=account.idAccount;";
    public static final String GET_ACCOUNT_BY_HR_ID = "SELECT account.idAccount, account.login, account.password," +
            " account.name, account.surname, account.birthday, account.email" +
            " FROM account, hr WHERE hr.idHR=? and hr.idAccount=account.idAccount;";
    public static final String GET_HR_BY_ID_ACCOUNT = "SELECT * FROM hr WHERE idAccount=?;";
    public static final String GET_ACCOUNT_BY_NAMES = "SELECT * FROM account WHERE name=? and surname=?;";
    public static final String GET_VACANCY = "SELECT * FROM vacancy WHERE idVacancy=?;";
    public static final String GET_CANDIDATE_BY_ACCOUNT_ID = "SELECT candidate.idCandidate, candidate.idAccount FROM" +
            " account, candidate WHERE candidate.idAccount=account.idAccount and candidate.idAccount=?;";
    public static final String GET_PASSWORD_BY_LOGIN = "SELECT password FROM account WHERE login=?;";
    public static final String GET_ID_ACCOUNT_BY_LOGIN = "SELECT idAccount FROM account WHERE login=?;";
    public static final String GET_CANDIDATE_VACANCIES = "SELECT idSelection, idHR, selection.idCandidate, selectionDate," +
            " idVacancy, status, registrationDate FROM selection, candidate WHERE candidate.idCandidate=selection.idCandidate and candidate.idCandidate=?;";
    public static final String GET_HR_VACANCIES = "SELECT idSelection, hr.idHR, selection.idCandidate, selectionDate," +
            " idVacancy, status, registrationDate FROM selection, hr WHERE hr.idHR=selection.idHR and hr.idHr=?;";
    public static final String GET_SELECTION_BY_VACANCY = "SELECT * FROM selection WHERE idVacancy=?;";
    public static final String GET_SELECTION_BY_IDS = "SELECT idSelection FROM selection, candidate WHERE candidate.idCandidate=selection.idCandidate and candidate.idCandidate=? and idVacancy=?;";
    public static final String GET_SELECTION_BY_ID = "SELECT * FROM selection WHERE idSelection=?;";
    public static final String GET_SELECTION_BY_STATUS = "SELECT * FROM selection WHERE status=?;";
    public static final String GET_SELECTION_WITHOUT_HR = "SELECT * FROM selection WHERE idHR is null;";
    public static final String  GET_SELECTION_BY_DATE = "SELECT * FROM selection where selectionDate=?;";
    public static final String GET_ALL_VACANCIES_WITH_COUNT = "select vacancy.idVacancy, count(*), name, description" +
            " from vacancy join selection on " +
            "vacancy.idVacancy=selection.idVacancy group by vacancy.idVacancy order by count(*) desc;";
    public static final String GET_VACANCIES_BY_NAME = "SELECT * FROM vacancy where name=?;";


    /////////////////////////////INSERT////////////////////
    public static final String INSERT_INTO_ACCOUNT = "INSERT INTO account(login, password, name, surname, birthday, email) VALUES (?, ?, ?, ?, ?, ?);";
    public static final String INSERT_INTO_CANDIDATE = "INSERT INTO candidate(idAccount) VALUES (?)";
    public static final String INSERT_INTO_SELECTION = "INSERT INTO selection (idCandidate, idVacancy, status, registrationDate) VALUES (?, ?, ?, ?);";
    public static final String INSERT_INTO_VACANCY = "INSERT INTO vacancy (name, description) VALUES (?, ?)";

    /////////////////////////////DELETE////////////////////
    public static final String DELETE_SELECTION_BY_ID = "DELETE FROM selection WHERE idSelection=?";
    public static final String DELETE_VACANCY_BY_ID = "DELETE FROM vacancy WHERE idVacancy=?";

    ////////////////////////////UPDATE/////////////////////
    public static final String UPDATE_SELECTION = "UPDATE selection SET idHr=?, selectionDate=?, status=? WHERE idSelection=?";
    public static final String CHANGE_PASSWORD = "UPDATE account SET password=? WHERE idAccount=?";

}
