package master;

import javax.servlet.http.HttpServletRequest;

public class User {
	public int id;
	public String name;
	public String email;
	public String city;
	
	public User(String name, String email, String city) {
		super();
		this.name = name;
		this.email = email;
		this.city = city;
		
	}
	
	public User(int id, String name, String email, String city) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.city = city;
		
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setRequestParam(HttpServletRequest request) {

		this.setId(null!=request.getParameter("id")&&!request.getParameter("id").equals("")?Integer.parseInt((String)request.getParameter("id")):0);
		this.setName(null!=request.getParameter("name")?request.getParameter("name"):"");
		this.setEmail(null!=request.getParameter("email")?request.getParameter("email"):"");
		this.setCity(null!=request.getParameter("city")?request.getParameter("city"):"");

		}
	
	public void displayReqParam(HttpServletRequest request) {


		System.out.println("------Begin:Request Param Values---------");
		System.out.println("id = "+request.getParameter("id"));
		System.out.println("name = "+request.getParameter("name"));
		System.out.println("email = "+request.getParameter("email"));
		System.out.println("city = "+request.getParameter("city"));

		System.out.println("------End:Request Param Values---------");
		}
	
	public void displayValues() {

		System.out.println("Id = "+this.getId());
		System.out.println("Name = "+this.getName());
		System.out.println("Email= "+this.getEmail());
		System.out.println("City = "+this.getCity());

		}
	
}
