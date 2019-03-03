package com.liskovsoft.youtubeapi.content.models;

import com.liskovsoft.youtubeapi.converters.jsonpath.JsonPath;
import com.liskovsoft.youtubeapi.converters.jsonpath.JsonPathCollection;

import java.util.List;

public class ContentTab {
    @JsonPath("$.title")
    private String title;

    @JsonPath("$.endpoint.browseEndpoint.browseId")
    private String browseId;

    /**
     * Sections == Rows in web app version
     */
    @JsonPath("$.content.tvSurfaceContentRenderer.content.sectionListRenderer.contents[*].shelfRenderer")
    private JsonPathCollection<ContentTabSection> sections = new JsonPathCollection<>(ContentTabSection.class); // type erase fix

    public String getTitle() {
        return title;
    }

    public String getBrowseId() {
        return browseId;
    }

    public JsonPathCollection<ContentTabSection> getSections() {
        return sections;
    }
}
