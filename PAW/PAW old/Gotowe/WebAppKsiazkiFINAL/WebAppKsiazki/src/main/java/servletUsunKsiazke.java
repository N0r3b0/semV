

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/servletUsunKsiazke")
/**
 * Servlet implementation class servletUsunKsiazke
 */
public class servletUsunKsiazke extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletUsunKsiazke() {
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
		
		int id = Integer.parseInt(request.getParameter("id"));

        daoKsiazki dao = new daoKsiazki();
        
        try {
            dao.usunKsiazke(id);
            request.setAttribute("komunikat", "Książka została usunięta pomyślnie.");

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("komunikat", "Wystąpił błąd podczas usuwania książki.");

            
        }
        request.getRequestDispatcher("usunKsiazke.jsp").forward(request, response);
	}

}
