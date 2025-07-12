package app;

import core.annotations.Component;
import core.annotations.Qualifier;


@Component
@Qualifier("userRepository")
public class UserRepository {

    public String findUserNameById(int id) {
        // Simulate DB fetch
        return "User#" + id;
    }

    @Override
    public String toString() {
        return "UserRepository instance";
    }
}
