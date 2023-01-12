package by.epam.ft.command;

import by.epam.ft.dao.VacancyDAO;
import by.epam.ft.entity.Vacancy;
import by.epam.ft.service.mail.EmailSenderCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.ft.constant.AttributeAndParameterConstant.ID_VACANCY;
import static by.epam.ft.constant.PreparedConstant.OPEN_VACANCY_BY_ID;

public class OpenVacancyCommand implements ActionCommand {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int idVacancy = Integer.parseInt(request.getParameter(ID_VACANCY));

        Vacancy vacancy = new Vacancy();
        vacancy.setIdVacancy(idVacancy);
        VacancyDAO vacancyDAO = new VacancyDAO();
        vacancyDAO.updateStatus(vacancy, OPEN_VACANCY_BY_ID);

        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("<h1 style=\"text-align: center;\">Good day!</h1>")
                .append("<p style=\"font-size: 25px; font-family: 'Courier new'\">You received this email because you was registered to vacancy which was closed and opening now</p>")
                .append("<p style=\"font-size: 25px; font-family: 'Courier new'\">You can check it in your account.</p>")
                .append("<p style=\"font-size: 25px; font-family: 'Courier new'\">Now we will checking all requests of this vacancy. Thanks for patient. Stay with us</p>")
                .append("<p style=\"font-size: 25px; font-family: 'Arial'\">Good luck! Regards It Road Company Inc</p>");

        new EmailSenderCommon().sendMessagesIfActionWithVacancy(idVacancy, "Opening vacancy", messageBuilder);

        LoadClosedVacancyPageCommand command = new LoadClosedVacancyPageCommand();
        return command.execute(request, response);
    }
}
