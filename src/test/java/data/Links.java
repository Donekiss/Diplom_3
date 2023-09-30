package data;

public class Links {
    private static final String ConstructorWindow = "https://stellarburgers.nomoreparties.site/";
    private static final String FeedOrdersWindow = "https://stellarburgers.nomoreparties.site/feed";
    private static final String PersonalAccountWindow = "https://stellarburgers.nomoreparties.site/login";
    private static final String RegistrationWindow = "https://stellarburgers.nomoreparties.site/register";
    private static final String PasswordRecoveryWindow = "https://stellarburgers.nomoreparties.site/forgot-password";
    private static final String ProfileWindow = "https://stellarburgers.nomoreparties.site/account/profile";

    public static String getProfileWindow() {
        return ProfileWindow;
    }

    public static String getPasswordRecoveryWindow() {
        return PasswordRecoveryWindow;
    }

    public static String getConstructorWindow() {
        return ConstructorWindow;
    }

    public static String getFeedOrdersWindow() {
        return FeedOrdersWindow;
    }

    public static String getPersonalAccountWindow() {
        return PersonalAccountWindow;
    }

    public static String getRegistrationWindow() {
        return RegistrationWindow;
    }
}
