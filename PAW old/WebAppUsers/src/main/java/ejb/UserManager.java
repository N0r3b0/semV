package ejb;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.UserDAO;
//import jakarta.annotation.PostConstruct;
//import jakarta.enterprise.inject.Model;
import model.User;

//@Model
public class UserManager implements Serializable{
	private User user;
	private ArrayList<User> users;
	private UserDAO userDAO=new UserDAO();
	private static final long serialVersionUID = 1L;
	private static int selectedID;
	
//	@PostConstruct
	private void init()
	{
	user=new User();
	setUsers(userDAO.selectAllUsers());
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ArrayList<User> getUsers() {
		return users;
	}
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	public String insertUser(User u) throws SQLException {
		userDAO.insertUser(u);
		return ("index");
	}
	public String updateUser(User u) throws SQLException  {
		userDAO.updateUser(u, selectedID);
		return ("index");
	}
    public String selectUser(int userID) throws SQLException{
    	selectedID = userID;
        user = userDAO.selectUser(userID);
        return ("update");
    }
    public String deleteUser(int id) throws SQLException {
        userDAO.deleteUser(id);
        return ("index");
    }
}
