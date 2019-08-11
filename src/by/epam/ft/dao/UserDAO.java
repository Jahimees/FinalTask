package by.epam.ft.dao;

import by.epam.ft.entity.MyEntity;

/**
 * Interface for DAOs which have user-role
 * Candidate, HR, f.e.
 */
public interface UserDAO {
    MyEntity showByAccountId(int accId);
}
