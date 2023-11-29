

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
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
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String tytul = request.getParameter("tytul");
		String rokWydania = request.getParameter("rokWydania");
        String wydawnictwo = request.getParameter("wydawnictwo");
        String kategoria = request.getParameter("kategoria");

        daoKsiazki dao = new daoKsiazki();
        
        try {
            dao.dodajKsiazke(tytul, rokWydania, wydawnictwo, kategoria);
            request.setAttribute("komunikat", "Książka została dodana pomyślnie.");
  
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("komunikat", "Wystąpił błąd podczas dodawania książki.");
   
        }
        
        request.getRequestDispatcher("dodajKsiazke.jsp").forward(request, response);
	}

}
