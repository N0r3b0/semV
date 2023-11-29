import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/servletListaKsiazek") 

/**
 * Servlet implementation class servletListaKsiazek
 */
public class servletListaKsiazek extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletListaKsiazek() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String categoryId = request.getParameter("id");
		
		daoKsiazki dao = new daoKsiazki();
		ArrayList<modelKsiazka> tk;
		
		if (categoryId != null) {
	        int categoryIdInt = Integer.parseInt(categoryId);
	        tk = dao.listaKsiazekKategorii(categoryIdInt);
	    } else {
	    	tk = dao.listaKsiazek();
	    }
		
		HttpSession sesja = request.getSession();
		
		sesja.setAttribute("tk", tk);
		
		String nextURL = "/listaKsiazek.jsp";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(nextURL);
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
