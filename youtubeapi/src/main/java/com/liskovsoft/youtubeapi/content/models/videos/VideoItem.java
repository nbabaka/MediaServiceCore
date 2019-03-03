package com.liskovsoft.youtubeapi.content.models.videos;

import com.liskovsoft.youtubeapi.converters.jsonpath.JsonPath;
import com.liskovsoft.youtubeapi.converters.jsonpath.JsonPathCollection;

public class VideoItem {
    @JsonPath("$.videoId")
    private String videoId;
    @JsonPath("$.thumbnail.thumbnails[*]")
    private JsonPathCollection<Thumbnail> thumbnails = new JsonPathCollection<>(Thumbnail.class);
    @JsonPath("$.channelThumbnail.thumbnails[0]")
    private String channelThumbnail;
    @JsonPath("$.title.runs[0].text")
    private String title;
    @JsonPath("$.longBylineText.runs[0].text")
    private String userName;
    @JsonPath("$.longBylineText.runs[0].navigationEndpoint.browseEndpoint.browseId")
    private String channelId;
    @JsonPath("$.longBylineText.runs[0].navigationEndpoint.browseEndpoint.canonicalBaseUrl")
    private String canonicalChannelUrl;
    @JsonPath("$.publishedTimeText.runs[0].text")
    private String publishedTime;
    @JsonPath("$.viewCountText.runs[0].text")
    private String viewCount;
    @JsonPath("$.shortViewCountText.runs[0].text")
    private String shortViewCount;
    @JsonPath("$.lengthText.runs[0].text")
    private String length;
    @JsonPath("$.lengthText.accessibility.accessibilityData.label")
    private String accessibilityLength;
    @JsonPath("$.badges[0].textBadge.label.runs[0].text")
    private String qualityBadge;

    public String getVideoId() {
        return videoId;
    }

    public JsonPathCollection<Thumbnail> getThumbnails() {
        return thumbnails;
    }

    public String getChannelThumbnail() {
        return channelThumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getUserName() {
        return userName;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getCanonicalChannelUrl() {
        return canonicalChannelUrl;
    }

    public String getPublishedTime() {
        return publishedTime;
    }

    public String getViewCount() {
        return viewCount;
    }

    public String getShortViewCount() {
        return shortViewCount;
    }

    public String getLength() {
        return length;
    }

    public String getAccessibilityLength() {
        return accessibilityLength;
    }

    public String getQualityBadge() {
        return qualityBadge;
    }
}
