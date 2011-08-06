package jp.fieldnotes.mzp;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

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
		assertTweet(0, tweetList, Tweet.NORMAL);
		assertTweet(1, tweetList, Tweet.REPLY);
		assertTweet(2, tweetList, Tweet.MENTION);
		assertTweet(3, tweetList, Tweet.REPLY);
		assertTweet(4, tweetList, Tweet.NORMAL);

	}

	private void assertTweet(int offset, List<Tweet> tweet,
			String... exptectType) {
		for (String type : exptectType) {
			assertThat(Integer.toString(offset), tweet.get(offset).getTweet()
					.split("\t")[0], is(containsString(type)));
		}
	}
}
