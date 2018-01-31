package zone.cognitive.security.service;

import org.springframework.security.core.AuthenticationException;

/**
 * @author <a href="mailto:cognitive.zone@gmail.com">Shahar Shocron</a>
 * @since 07/04/17
 */
public class UserAlreadyExistAuthenticationException extends AuthenticationException {

    public UserAlreadyExistAuthenticationException(final String msg) {
        super(msg);
    }

}
