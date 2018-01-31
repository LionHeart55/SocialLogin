package zone.cognitive.security.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.social.security.SocialAuthenticationServiceRegistry;
import org.springframework.social.security.provider.SocialAuthenticationService;

/**
 * @author <a href="mailto:cognitive.zone@gmail.com">Shahar Shocron</a>
 * @since 07/04/17
 */
public class AppSocialAuthenticationServiceRegistry extends SocialAuthenticationServiceRegistry {

    private List<SocialAuthenticationService<?>> authenticationServices;

    public AppSocialAuthenticationServiceRegistry(final List<SocialAuthenticationService<?>> authenticationServices) {
        this.authenticationServices = authenticationServices;
    }

    @PostConstruct
    public void init() {
        if (authenticationServices != null) {
            for (SocialAuthenticationService authenticationService : authenticationServices) {
                super.addAuthenticationService(authenticationService);
            }
        }
    }

}
