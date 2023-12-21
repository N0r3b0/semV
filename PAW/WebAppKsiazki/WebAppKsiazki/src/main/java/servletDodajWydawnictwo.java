

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/servletDodajWydawnictwo")
/**
 * Servlet implementation class servletDodajWydawnictwo
 */
public class servletDodajWydawnictwo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletDodajWydawnictwo() {
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
		
		 String nazwa = request.getParameter("nazwa");
	        String miasto = request.getParameter("miasto");
	        String panstwo = request.getParameter("panstwo");

	        daoKsiazki dao = new daoKsiazki();

	        try {
	            dao.dodajWydawnictwo(nazwa, miasto, panstwo);
	            request.setAttribute("komunikat", "Wydawnictwo zostało dodane pomyślnie.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	            request.setAttribute("komunikat", "Wystąpił błąd podczas dodawania wydawnictwa.");
	        }

	        request.getRequestDispatcher("dodajWydawnictwo.jsp").forward(request, response);
	}

}
