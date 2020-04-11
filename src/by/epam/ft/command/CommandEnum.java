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
    LOAD_PAGE_OPENED_VACANCIES {
        {
            this.command = new LoadOpenedVacancyPageCommand();
        }
    },
    LOAD_PAGE_CLOSED_VACANCIES {
        {
            this.command = new LoadClosedVacancyPageCommand();
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
    CLOSE_VACANCY {
        {
            this.command = new CloseVacancyCommand();
        }
    },
    OPEN_VACANCY {
        {
            this.command = new OpenVacancyCommand();
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
    },
    CHANGE_VACANCY {
        {
            this.command = new ChangeVacancyCommand();
        }
    },
    SEND_REQUEST_CONFIRM_ACCOUNT {
        {
            this.command = new SendRequestConfirmAccountCommand();
        }
    },
    RECEIVE_CONFIRM_EMAIL {
        {
            this.command = new ReceiveConfirmEmail();
        }
    };
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
