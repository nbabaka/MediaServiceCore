package com.liskovsoft.youtubeapi.common.helpers;

import com.liskovsoft.sharedutils.okhttp.OkHttpManager;
import com.liskovsoft.youtubeapi.auth.AuthService;
import com.liskovsoft.youtubeapi.auth.models.AccessToken;

public class TestHelpers {
    private static String mAuthorization; // type: Bearer
    private static final String RAW_AUTH_DATA = "client_id=861556708454-d6dlm3lh05idd8npek18k6be8ba3oc68.apps.googleusercontent.com&client_secret=SboVhoG9s0rNafixCSGGKXAT&refresh_token=1%2F%2F0ca0zVzDYAcWCCgYIARAAGAwSNwF-L9IrCkqjDqPyup8sXFA40LiTGh-8yW2jM4lLBOXyhcRa07fDM35jM-dU80PUemu1u1F8-AY&grant_type=refresh_token";

    // ZHU - In the Morning (Official Video)
    public static final String MUSIC_VIDEO_ID = "5_ARibfCMhw";
    public static final String MUSIC_VIDEO_PLAYLIST_ID = "PLbl01QFpbBY3k5A8412DEqxwNuHBDudBz";
    public static final int MUSIC_VIDEO_INDEX = 1;

    // News One
    public static final String VIDEO_ID_SIMPLE_1 = "x26FXCaUR7E";

    // Kvartal 95
    public static final String VIDEO_ID_SIMPLE_2 = "XemGObKTF0o";

    // Mafia: Definitive Edition - Official Story Trailer | Summer of Gaming 2020
    public static final String VIDEO_ID_CAPTIONS = "s2lGEhSlOTY";

    // Strana: Yasno i ponyatno
    public static final String VIDEO_ID_SUBSCRIBED = "ftrpxWYDIJU";

    // 4K HDR 60FPS ● Sniper Will Smith (Gemini Man) ● Dolby Vision ● Dolby Atmos
    public static final String VIDEO_ID_UNAVAILABLE = "vX2vsvdq8nw";

    // Cyberpunk trailer
    public static final String VIDEO_ID_AGE_RESTRICTED = "8X2kIfS6fb8";

    // LINDEMANN - Mathematik ft. Haftbefehl (Official Video)
    public static final String VIDEO_ID_CIPHERED = "0YEZiDtnbdA";

    // GECID.com
    public static final String CHANNEL_ID_SIMPLE = "UC3PyIqYQ7lw7YKHRLqIvXlw";

    // Strana.ua
    public static final String CHANNEL_ID_SIMPLE2 = "UCDuSNexflm9nFKXLeMlqN2Q";

    // Hayls World
    public static final String CHANNEL_ID_UNSUBSCRIBED = "UCIxLxlan8q9WA7sjuq6LdTQ";

    // NEWS ONE
    public static final String VIDEO_ID_LIVE = "3e0FsU1N6OQ";

    public static String getAuthorization() {
        if (mAuthorization != null) {
            return mAuthorization;
        }

        AccessToken token = AuthService.instance().getAccessTokenRaw(RAW_AUTH_DATA);

        if (token == null) {
            throw new IllegalStateException("Token is null");
        }

        if (token.getAccessToken() == null) {
            throw new IllegalStateException("Authorization is null");
        }

        mAuthorization = String.format("%s %s", token.getTokenType(), token.getAccessToken());

        return mAuthorization;
    }

    public static boolean urlExists(String url) {
        // disable profiler because it could cause out of memory error
        return OkHttpManager.instance(false).doGetOkHttpRequest(url) != null;
    }
}
