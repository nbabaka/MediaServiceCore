package com.liskovsoft.youtubeapi.browse.models.sections;

import com.liskovsoft.youtubeapi.common.converters.jsonpath.JsonPath;

import java.util.List;

public class SectionTabList {
    @JsonPath("$.contents.tvBrowseRenderer.content.tvSecondaryNavRenderer.sections[0].tvSecondaryNavSectionRenderer.tabs[*].tabRenderer")
    private List<SectionTab> mTabs;

    @JsonPath("$.responseContext.visitorData")
    private String mVisitorData;

    public List<SectionTab> getTabs() {
        return mTabs;
    }

    public String getVisitorData() {
        return mVisitorData;
    }
}
