package mk.gameIt.domain;

/**
 * Created by Stefan on 26.03.2016.
 */
public enum Provider {
    LOCAL {
        @Override
        public String getLoginUrl() {
            return "/login";
        }
    },
    GOOGLE{
        @Override
        public String getLoginUrl(){return "/login/google";}
    },
    FACEBOOK {
        @Override
        public String getLoginUrl() {
            return "/login/facebook";
        }
    };

    public String getLoginUrl() {
        return null;
    }
}
