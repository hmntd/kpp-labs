package Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import Models.User;

public class AuthenticationService {
    private List<User> users;

    public AuthenticationService(List<User> initialUsers) {
        this.users = initialUsers;
    }

    public boolean userExists(String login) {
        return users.stream().anyMatch(user -> user.getLogin().equalsIgnoreCase(login));
    }

    public void addUser(User newUser) throws Exception {
        if (userExists(newUser.getLogin()))
            throw new Exception("Error: User already exists.");
        users.add(newUser);
    }

    public boolean deleteUser(String login) {
        if (!userExists(login))
            return false;
        users = users.stream()
                .filter(user -> !user.getLogin().equalsIgnoreCase(login))
                .collect(Collectors.toList());
        return true;
    }

    public void changeLogin(String oldLogin, String newLogin) throws Exception {
        if (userExists(newLogin))
            throw new Exception("Error: Login taken.");
        Optional<User> foundUser = users.stream()
                .filter(user -> user.getLogin().equalsIgnoreCase(oldLogin))
                .findFirst();
        if (foundUser.isPresent())
            foundUser.get().setLogin(newLogin);
        else
            throw new Exception("Error: User not found.");
    }

    public void changePassword(String login, String newPassword) throws Exception {
        User foundUser = users.stream()
                .filter(user -> user.getLogin().equalsIgnoreCase(login))
                .findFirst()
                .orElseThrow(() -> new Exception("Error: User not found."));
        foundUser.setPassword(newPassword);
    }

    public List<User> getAllUsers() {
        return users;
    }
}
