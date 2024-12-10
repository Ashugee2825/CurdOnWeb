package master;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet implementation class UserCntrl
 */
@WebServlet("/")
public class UserCntrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       public UserDBService userDBService;
       public User user;
    

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		userDBService = new UserDBService();
		user = new User();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String action = request.getServletPath();
		switch(action) {
		case "/new":
			showNewForm(request, response);
		break;
		case "/insert":
			insertUser(request, response);
			break;
		case "/delete":
			deleteUser(request, response);
			break;
		case "/update":
			try {
				updateUser(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/edit":
			try {
				showEditForm(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			default:
			try {
				listUser(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
			case "/view":
			try {
				viewUser(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
		}
	}
		public void showNewForm(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
		{
			RequestDispatcher rd = request.getRequestDispatcher("user-form.jsp");
			rd.forward(request, response);
		}
		//insert user
		public void insertUser(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
		{
			String name= request.getParameter("name");
			String email = request.getParameter("email");
			String city = request.getParameter("city");
			
			
			
			User newUser = new User(name, email, city);
			
			userDBService.insertUser(newUser);
			response.sendRedirect("list");
		}
		//delete user
		public void deleteUser(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
		{
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				userDBService.deleteUser(id);
			} catch (Exception e) {
				e.printStackTrace();
				}
			request.setAttribute("user",user);
			RequestDispatcher rd=request.getRequestDispatcher("deleteUserSuccess.jsp");
			rd.forward(request, response);
			
		}
		//edit user
		public void showEditForm(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException, SQLException
		{
			int id = Integer.parseInt(request.getParameter("id"));
			
			User existingUser;
			try {
				existingUser = userDBService.selectUser(id);
				RequestDispatcher rd = request.getRequestDispatcher("user-form.jsp");
				request.setAttribute("user", existingUser);
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//update user
		public void updateUser(HttpServletRequest request, HttpServletResponse response ) throws SQLException, IOException
		{
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String city = request.getParameter("city");
			
			User user = new User(id, name, email, city);
			userDBService.updateUser(user);
			response.sendRedirect("list");
		}
		
		//default
		
		public void listUser(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException, SQLException
		{
			try { List<User> listUser = userDBService.selectAllUsers();
			request.setAttribute("listUser", listUser);
			RequestDispatcher rd = request.getRequestDispatcher("user-list.jsp");
			rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		
		//view
		public void viewUser(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException, SQLException
		{
			{
				int id = Integer.parseInt(request.getParameter("id"));
				user = userDBService.selectUser(id);
				request.setAttribute("user",user);
				
				RequestDispatcher rd = request.getRequestDispatcher("viewUsers.jsp");
				rd.forward(request, response);
			}
		}
	

}
