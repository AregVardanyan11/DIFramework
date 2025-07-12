package app;

import core.annotations.Component;
import core.annotations.Inject;
import core.annotations.Qualifier;
import core.annotations.Scope;
import core.enums.ScopeType;


@Component
@Qualifier("userService")
@Scope(ScopeType.PROTOTYPE)
public class UserService {

    @Inject
    private UserRepository userRepository;

    public String getUserInfo(int userId) {
        return "User Info for ID " + userId + ": " + userRepository.findUserNameById(userId);
    }

    @Override
    public String toString() {
        return "UserService using " + userRepository;
    }
}
