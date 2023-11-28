

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/servletDodajKsiazke")
/**
 * Servlet implementation class servletDodajKsiazke
 */
public class servletDodajKsiazke extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletDodajKsiazke() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        daoKsiazki dao = new daoKsiazki();
        HttpSession sesja = request.getSession();

        sesja.setAttribute("wydawnictwa", dao.listaWydawnictw());
        sesja.setAttribute("kategorie", dao.listaKategorii());

        String nextURL = "/dodajKsiazke.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(nextURL);
        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String tytul = request.getParameter("tytul");
        String rokW = request.getParameter("dataWydania");
		Date rokWydania = Date.valueOf(rokW);
        int idwyd = Integer.parseInt(request.getParameter("wydawnictwo"));
        int idk = Integer.parseInt(request.getParameter("kategoria"));

        daoKsiazki dao = new daoKsiazki();
        
        try {
            dao.dodajKsiazke(tytul, rokWydania, idwyd, idk);
            request.setAttribute("komunikat", "Książka została dodana pomyślnie.");
  
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("komunikat", "Wystąpił błąd podczas dodawania książki.");
   
        }
        
        request.getRequestDispatcher("dodajKsiazke.jsp").forward(request, response);
	}

}
