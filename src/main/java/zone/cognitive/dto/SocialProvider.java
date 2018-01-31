package zone.cognitive.dto;

/**
 * @author <a href="mailto:cognitive.zone@gmail.com">Shahar Shocron</a>
 * @since 07/04/17
 */
public enum SocialProvider {

    FACEBOOK("facebook"), TWITTER("twitter"), LINKEDIN("linkedin"), GOOGLE("google"), NONE("local");

    private String providerType;

    public String getProviderType() {
        return providerType;
    }

    SocialProvider(final String providerType) {
        this.providerType = providerType;
    }

}
