

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/servletDodajKategorie")
/**
 * Servlet implementation class servletDodajKategorie
 */
public class servletDodajKategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletDodajKategorie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String opis = request.getParameter("opis");

        daoKsiazki dao = new daoKsiazki();

        try {
            dao.dodajKategorie(opis);
            request.setAttribute("komunikat", "Kategoria została dodana pomyślnie.");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("komunikat", "Wystąpił błąd podczas dodawania kategorii.");
        }

        request.getRequestDispatcher("dodajKategorie.jsp").forward(request, response);
	}

}
