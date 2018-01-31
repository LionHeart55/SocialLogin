package zone.cognitive.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import zone.cognitive.dto.SocialProvider;

/**
 * @author <a href="mailto:cognitive.zone@gmail.com">Shahar Shocron</a>
 * @since 07/04/17
 */
public class SecurityUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtil.class);

    public static void authenticateUser(UserDetails userDetails)
    {
        LOGGER.debug("Logging in principal: {}", userDetails);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        LOGGER.info("User: {} has been logged in.", userDetails);
    }

    public static SocialProvider toSocialProvider(String providerId) {
        for (SocialProvider socialProvider : SocialProvider.values()) {
            if (socialProvider.getProviderType().equals(providerId)) {
                return socialProvider;
            }
        }
        return SocialProvider.NONE;
    }
}
