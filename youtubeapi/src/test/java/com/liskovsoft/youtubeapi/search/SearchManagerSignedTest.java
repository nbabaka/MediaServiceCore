package com.liskovsoft.youtubeapi.search;

import com.liskovsoft.youtubeapi.common.helpers.RetrofitHelper;
import com.liskovsoft.youtubeapi.common.helpers.TestHelpers;
import com.liskovsoft.youtubeapi.search.models.SearchResult;
import com.liskovsoft.youtubeapi.search.models.SearchResultContinuation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowLog;
import retrofit2.Call;

@RunWith(RobolectricTestRunner.class)
public class SearchManagerSignedTest extends SearchManagerTestBase {
    private static final String SEARCH_TEXT = "thrones season 8 trailer";
    private static final String SEARCH_TEXT_SPECIAL_CHAR = "What's Trending";
    private SearchManagerSigned mSearchManagerSigned;

    @Before
    public void setUp() {
        // fix issue: No password supplied for PKCS#12 KeyStore
        // https://github.com/robolectric/robolectric/issues/5115
        System.setProperty("javax.net.ssl.trustStoreType", "JKS");

        ShadowLog.stream = System.out; // catch Log class output

        mSearchManagerSigned = RetrofitHelper.withJsonPath(SearchManagerSigned.class);
    }

    @Test
    public void testThatSearchResultIsValid() {
        Call<SearchResult> wrapper = mSearchManagerSigned.getSearchResult(SearchManagerParams.getSearchQuery(SEARCH_TEXT), TestHelpers.getAuthorization());
        SearchResult searchResult = RetrofitHelper.get(wrapper);

        checkSearchResult(searchResult);

        wrapper = mSearchManagerSigned.getSearchResult(SearchManagerParams.getSearchQuery(SEARCH_TEXT_SPECIAL_CHAR), TestHelpers.getAuthorization());
        searchResult = RetrofitHelper.get(wrapper);

        checkSearchResult(searchResult);
    }

    @Test
    public void testThatContinuationResultIsValid() {
        Call<SearchResult> wrapper = mSearchManagerSigned.getSearchResult(SearchManagerParams.getSearchQuery(SEARCH_TEXT), TestHelpers.getAuthorization());
        SearchResult result = RetrofitHelper.get(wrapper);
        checkSearchResult(result);

        String nextPageKey = result.getNextPageKey();
        Call<SearchResultContinuation> wrapper2 = mSearchManagerSigned.continueSearchResult(SearchManagerParams.getContinuationQuery(nextPageKey), TestHelpers.getAuthorization());
        SearchResultContinuation result2 = RetrofitHelper.get(wrapper2);
        checkSearchResultContinuation(result2);
    }

}