package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ArcadeInventory;

/**
 * Servlet implementation class NavigationServlet
 */
@WebServlet("/navigationServlet")
public class NavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NavigationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String act = request.getParameter("doThisToItem");
		ArcadeInventoryHelper aih = new ArcadeInventoryHelper();
		String path = "/viewAllItemsServlet";
		
		if (act.equals("Delete")) {
			try {
			Integer tempId = Integer.parseInt(request.getParameter("id"));
			ArcadeInventory itemToDelete = aih.lookupArcadeById(tempId);
			aih.deleteArcade(itemToDelete);
			} catch (NumberFormatException e) {
				System.out.println("No option selected.");
			}
		}
		else if (act.equals("Edit")) {
			try {
			Integer tempId = Integer.parseInt(request.getParameter("id"));
			ArcadeInventory itemToEdit = aih.lookupArcadeById(tempId);
			request.setAttribute("itemToEdit", itemToEdit);
			path = "/edit-arcade.jsp";
			} catch (NumberFormatException e) {
				System.out.println("No option selected.");
			}
		}
		else if (act.equals("Return")) {
			path = "/index.html";
		}
		getServletContext().getRequestDispatcher(path).forward(request, response);
	}

}
