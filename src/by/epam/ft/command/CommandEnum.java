package by.epam.ft.command;

/**
 * Enum of all commands to define it from request parameter
 */
public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    OPEN_ACCOUNT {
        {
            this.command = new OpenAccountCommand();
        }
    },
    OPEN_VACANCIES {
        {
            this.command = new OpenVacancyCommand();
        }
    },
    REGISTER {
        {
            this.command = new RegistrationCommand();
        }
    },
    REVOKE_VACANCY {
        {
            this.command = new RevokeVacancyCommand();
        }
    },
    STATE_VACANCY {
        {
            this.command = new StateVacancyCommand();
        }
    },
    ADD_VACANCY {
        {
            this.command = new AddVacancyCommand();
        }
    },
    DELETE_VACANCY {
        {
            this.command = new DeleteVacancyCommand();
        }
    },
    CHANGE_SELECTION {
        {
            this.command = new ChangeSelectionCommand();
        }
    },
    CHANGE_PASSWORD {
        {
            this.command = new ChangePasswordCommand();
        }
    },
    CHANGE_LOCALE {
        {
            this.command = new LocaleChangerCommand();
        }
    };
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
