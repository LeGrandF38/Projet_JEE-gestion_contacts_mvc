package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Contact;
import model.FacadeContact;

@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {

    private FacadeContact facadeContact; // Ajout de l'instance de FacadeContact

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        facadeContact = new Contact(); // Initialisation de l'instance de FacadeContact dans la méthode init
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // INSTANCIATION DE L’OBJET CONTACTFACADE
        // À FAIRE : Remplacez ContactFacadeImpl par le nom de votre classe d'implémentation
        Contact contactFacade = new Contact();

        // RÉCUPÉRATION de L'ACTION À effectuer
        String do_this = request.getParameter("do_this"); // Utilisation de la méthode correcte getRequestParameter

        if (do_this == null) {
            // définir le contexte pour une redirection sur LA PAGE ACCUEIL.JSP
            ServletContext sc = getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/accueil.jsp");
            // CHARGER LA liste des CONTACTS DANS LA requête pour les TRANSMETTRE À LA JSP ACCUEIL.JSP (qui VA les AFFICHER)
            request.setAttribute("LISTCONTACTS", contactFacade.findAll()); // Utilisation de la variable contactFacade
            rd.forward(request, response);
        } else if (do_this.equals("delete")) {
            // RÉCUPÉRATION de l'id du CONTACT
            String id = request.getParameter("contact_id");
            if (id == null) {
                // redirection sur LA PAGE REMOVECONTACT.JSP
                response.sendRedirect("removecontact.jsp");
            } else {
                // id non nul, donc on supprime le CONTACT identifié PAR id
                int contactId = Integer.parseInt(id); // Conversion de la chaîne en entier
                contactFacade.deleteContact(contactId); // Utilisation de la variable contactFacade
                // on RECHARGE LA PAGE D'ACCUEIL
                response.sendRedirect("ControllerServlet");
            }
        } else if (do_this.equals("create")) {
            // RÉCUPÉRATION des informations du CONTACT
            String lastName = request.getParameter("lastName");
            if (lastName == null) {
                // redirection sur LA PAGE REMOVECONTACT.JSP
                response.sendRedirect("addContact.jsp");
            } else {
                // TODO le nom n'est PAS nul, donc on AJOUTE le CONTACT DANS LA BASE
                Contact newContact = new Contact(0, "", lastName, "", "", ""); // Création d'un nouveau contact avec des valeurs par défaut
                contactFacade.createContact(newContact); // Utilisation de la variable contactFacade
                // on RECHARGE LA PAGE D'ACCUEIL
                response.sendRedirect("ControllerServlet");
            }
        } else if (do_this.equals("update")) {
            String id = request.getParameter("contact_id");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            if (id != null && firstName != null && lastName != null && email != null && phone != null && address != null) {
                int contactId = Integer.parseInt(id);
                Contact updatedContact = new Contact(contactId, firstName, lastName, email, phone, address);
                contactFacade.updateContact(updatedContact);
                response.sendRedirect("ControllerServlet");
            } else {
                // Gérer le cas où les paramètres nécessaires ne sont pas fournis
                // Vous pouvez rediriger vers une page d'erreur ou faire autre chose
            }
        }
         else if (do_this.equals("search")) {
        String searchKeyword = request.getParameter("searchKeyword");

        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            List<Contact> searchResults = contactFacade.searchContacts(searchKeyword);
            request.setAttribute("SEARCHRESULTS", searchResults);
            ServletContext sc = getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/searchResults.jsp");
            rd.forward(request, response);
        } else {
            // Gérer le cas où le mot-clé de recherche n'est pas fourni
            // Vous pouvez rediriger vers une page d'erreur ou faire autre chose
        }
    }

    // TODO ...
    }

}
