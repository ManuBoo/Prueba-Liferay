package UserListPortlet.portlet;

import java.util.ArrayList;
import java.util.List;

public class UserService {
	
	public static List<User> getUsers(String name, String surname, String email, int page, int pageSize) {
        List<User> allUsers = new ArrayList<>();

        // Simulación de usuarios
        for (int i = 1; i <= 20; i++) {
            allUsers.add(new User(i + "", "Admin" + i, "admin-" + i, "admin-" + i, "user" + i + "@yopmail.com"));
        }

        // Filtrado de usuarios
        List<User> filteredUsers = new ArrayList<>();
        for (User user : allUsers) {
            if ((name.isEmpty() || user.getName().contains(name)) &&
                (surname.isEmpty() || user.getSurname1().contains(surname) || user.getSurname2().contains(surname)) &&
                (email.isEmpty() || user.getEmail().contains(email))) {
                filteredUsers.add(user);
            }
        }

        // Paginación
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, filteredUsers.size());
        return filteredUsers.subList(start, Math.min(end, filteredUsers.size()));
    }
}
