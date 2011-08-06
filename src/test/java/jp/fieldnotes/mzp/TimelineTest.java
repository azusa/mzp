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
		assertTweet(5, tweetList, Tweet.NORMAL);
		assertTweet(6, tweetList, Tweet.HASH_TAG);
		assertTweet(7, tweetList, Tweet.MENTION, Tweet.REPLY);
		assertTweet(8, tweetList, Tweet.NORMAL);
		assertTweet(9, tweetList, Tweet.REPLY);
		assertTweet(10, tweetList, Tweet.NORMAL);
		assertTweet(11, tweetList, Tweet.NORMAL);
		assertTweet(12, tweetList, Tweet.MENTION, Tweet.REPLY);
		assertTweet(13, tweetList, Tweet.MENTION, Tweet.REPLY);
		assertTweet(14, tweetList, Tweet.NORMAL);
		assertTweet(15, tweetList, Tweet.MENTION);
		assertTweet(16, tweetList, Tweet.NORMAL);
		assertTweet(17, tweetList, Tweet.HASH_TAG);
		assertTweet(18, tweetList, Tweet.MENTION);

	}

	private void assertTweet(int offset, List<Tweet> tweet,
			String... exptectType) {
		for (String type : exptectType) {
			assertThat(Integer.toString(offset), tweet.get(offset).getTweet()
					.split("\t")[0], is(containsString(type)));
		}
	}
}
