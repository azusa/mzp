package jp.fieldnotes.mzp;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;

import java.net.URL;
import java.util.List;

import org.junit.Test;

public class TimelineTest {

	@Test
	public void ネットワーク越しの取得ができること() throws Exception {

		Timeline timeline = new Timeline(new URL(
				"http://tddbc.heroku.com/mzp/public_timeline"));
		List<Tweet> tweetList = timeline.getTweetList();
		assertThat(tweetList.size(), is(20));
		assertTweet(0, tweetList.get(0), Tweet.NORMAL);

	}

	private void assertTweet(int offset, Tweet tweet, String... exptectType) {
		for (String type : exptectType) {
			assertThat(Integer.toString(offset),
					tweet.getTweet().split("\t")[0], is(contains(type)));
		}
	}
}
