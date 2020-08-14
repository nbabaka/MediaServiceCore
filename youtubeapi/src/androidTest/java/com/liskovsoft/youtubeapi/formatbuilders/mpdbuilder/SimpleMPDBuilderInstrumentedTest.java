package com.liskovsoft.youtubeapi.formatbuilders.mpdbuilder;

import com.liskovsoft.mediaserviceinterfaces.MediaGroup;
import com.liskovsoft.mediaserviceinterfaces.MediaItem;
import com.liskovsoft.mediaserviceinterfaces.MediaItemFormatDetails;
import com.liskovsoft.mediaserviceinterfaces.MediaService;
import com.liskovsoft.sharedutils.helpers.Helpers;
import com.liskovsoft.sharedutils.okhttp.OkHttpManager;
import com.liskovsoft.youtubeapi.service.YouTubeMediaService;
import okhttp3.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class SimpleMPDBuilderInstrumentedTest {
    // Mafia: Definitive Edition - Official Story Trailer | Summer of Gaming 2020
    private static final String VIDEO_ID_SIMPLE = "s2lGEhSlOTY";

    // LINDEMANN - Mathematik ft. Haftbefehl (Official Video)
    private static final String VIDEO_ID_CIPHERED = "0YEZiDtnbdA";

    private MediaService mService;
    private OkHttpManager mOkHttpHelper;

    @Before
    public void setUp() {
        mService = YouTubeMediaService.instance();
        mOkHttpHelper = OkHttpManager.instance();
    }

    @Test
    public void testThatCipheredFormatIsValid() throws IOException {
        testVideoFormatUrl(VIDEO_ID_CIPHERED);
    }

    @Test
    public void testThatSimpleFormatIsValid() throws IOException {
        testVideoFormatUrl(VIDEO_ID_SIMPLE);
    }

    @Test
    public void testThatMpdNotEmpty() {
        MediaItemFormatDetails mediaItemDetails = mService.getMediaItemManager().getFormatDetails(VIDEO_ID_SIMPLE);

        assertTrue("Is dash", mediaItemDetails.containsDashInfo());

        InputStream mpdStream = mService.getMediaItemManager().getMpdStream(mediaItemDetails);

        assertNotNull("Mpd stream not null", mpdStream);
        String mpdContent = Helpers.toString(mpdStream);
        assertFalse("Mpd content not empty", mpdContent.isEmpty());
    }

    private MediaItemFormatDetails getMediaItemDetails() {
        MediaGroup homeGroup = mService.getMediaGroupManager().getHomeGroup();

        List<MediaItem> mediaItems = homeGroup.getNestedGroups().get(0).getMediaItems();

        assertTrue("Media item not empty", mediaItems != null && mediaItems.size() > 0);

        MediaItem mediaItem = mediaItems.get(0);

        return mService.getMediaItemManager().getFormatDetails(mediaItem);
    }

    private void testVideoFormatUrl(String videoId) {
        MediaItemFormatDetails mediaItemDetails = mService.getMediaItemManager().getFormatDetails(videoId);

        assertNotNull("Format info not empty", mediaItemDetails);
        assertTrue("Format list not empty", mediaItemDetails.getAdaptiveFormats().size() > 0);

        String url = mediaItemDetails.getAdaptiveFormats().get(0).getUrl();

        Response response = mOkHttpHelper.doGetOkHttpRequest(url);

        assertNotNull("Video url is working", response);
    }
}