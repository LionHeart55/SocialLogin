package zone.cognitive.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

import zone.cognitive.dto.LocalUser;
import zone.cognitive.dto.SocialProvider;
import zone.cognitive.dto.UserRegistrationForm;
import zone.cognitive.service.UserService;

/**
 * If no local user associated with the given connection then
 * connection signup will create a new local user from the given connection.
 *
 * @author <a href="mailto:cognitive.zone@gmail.com">Shahar Shocron</a>
 * @since 07/04/17
 */
public class AppConnectionSignUp implements ConnectionSignUp {

	final private static Logger LOGGER = LoggerFactory.getLogger(AppConnectionSignUp.class);
	
    @Autowired
    private UserService userService;

    @Override
    public String execute(final Connection<?> connection) {
    	UserProfile up = connection.fetchUserProfile();
    	
    	LOGGER.debug("AppConnectionSignUp -> execute() provider: {} UserPrifile type: {}", connection.getKey().getProviderUserId(), up.toString() );

        UserRegistrationForm userDetails = toUserRegistrationObject(connection.getKey().getProviderUserId(), SecurityUtil.toSocialProvider(connection.getKey().getProviderId()), connection.fetchUserProfile());
        LocalUser user = (LocalUser) userService.registerNewUser(userDetails);
        return user.getUserId();
    }

    private UserRegistrationForm toUserRegistrationObject(final String userId, final SocialProvider socialProvider, final UserProfile userProfile) {
        return UserRegistrationForm.getBuilder()
                .addUserId(userId)
                .addFirstName(userProfile.getFirstName())
                .addLastName(userProfile.getLastName())
                .addEmail(userProfile.getEmail())
                .addPassword("SOCIAL")
                .addSocialProvider(socialProvider).build();
    }

}
