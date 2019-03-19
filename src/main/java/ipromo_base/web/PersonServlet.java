package ipromo_base.web;

import com.mashape.unirest.http.exceptions.UnirestException;
import ipromo_base.ValidateEmail;
import ipromo_base.model.Person;
import ipromo_base.model.StockClass;
import ipromo_base.repository.ActiveCampaignRepository;
import ipromo_base.repository.JDBC_Repository;
import ipromo_base.service.PersonService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;;
import java.util.ArrayList;;
import java.util.concurrent.atomic.AtomicInteger;

public class PersonServlet extends HttpServlet {
    private PersonRestController controller;
    private ArrayList<StockClass> stockclasses = JDBC_Repository.select();
    private String message = "email not valid";
    private String email = "sales@ipromo.digital";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        controller = new PersonRestController(new PersonService(new ActiveCampaignRepository()));
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            email = request.getParameter("Email");
            Person person = new Person(request.getParameter("PersonName"), request.getParameter("SecondName"), request.getParameter("Phone"), email, "", request.getParameter("stock"), "");

            try {
                AtomicInteger id = new AtomicInteger();
                stockclasses.forEach(item -> {
                    if (person.getStockName().equals(item.getStock())) {
                        id.set(Integer.parseInt(item.getSerialnumber()));
                        id.addAndGet(1);
                        item.setSerialnumber(String.valueOf(id.get()));
                        JDBC_Repository.Update(id.get(), Integer.parseInt(item.getId()));
                        person.setSerialnumber(String.valueOf(id.get()));
                        person.setStockId(item.getId());

                    }
                });


                controller.GenerateCode(person);
            } catch (UnirestException e) {
                e.printStackTrace();
            }

            response.sendRedirect("person");

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "generate":
                Person person = new Person("", "", "", "", "", "", "");
                request.setAttribute("person", person);
                try {
                    controller.GenerateCode(person);
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
            case "all":
            default:
                if (!ValidateEmail.isAddressValid(email)) {
                    request.setAttribute("message", message);
                }
                request.setAttribute("stockclass", stockclasses);
                request.getRequestDispatcher("/person.jsp").forward(request, response);
                break;
        }
    }


}
