package com.liskovsoft.youtubeapi.content.models.videos;

import com.liskovsoft.youtubeapi.converters.jsonpath.JsonPath;
import com.liskovsoft.youtubeapi.converters.jsonpath.JsonPathCollection;

public class MusicItem {
    @JsonPath("$.navigationEndpoint.watchEndpoint.videoId")
    private String videoId;
    @JsonPath("$.thumbnail.thumbnails[*]")
    private JsonPathCollection<Thumbnail> thumbnails = new JsonPathCollection<>(Thumbnail.class);
    @JsonPath("$.primaryText.runs[0].text")
    private String title;
    @JsonPath("$.secondaryText.runs[0].text")
    private String userName;
    @JsonPath("$.navigationEndpoint.watchEndpoint.playlistId")
    private String playlistId;
    @JsonPath("$.tertiaryText.runs[2].text")
    private String publishedTime;
    @JsonPath("$.tertiaryText.runs[0].text")
    private String viewCount;
    @JsonPath("$.lengthText.runs[0].text")
    private String length;
    @JsonPath("$.lengthText.accessibility.accessibilityData.label")
    private String accessibilityLength;

    public String getVideoId() {
        return videoId;
    }

    public JsonPathCollection<Thumbnail> getThumbnails() {
        return thumbnails;
    }

    public String getTitle() {
        return title;
    }

    public String getUserName() {
        return userName;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public String getPublishedTime() {
        return publishedTime;
    }

    public String getViewCount() {
        return viewCount;
    }

    public String getLength() {
        return length;
    }

    public String getAccessibilityLength() {
        return accessibilityLength;
    }
}
