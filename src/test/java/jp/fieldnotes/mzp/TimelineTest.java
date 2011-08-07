package jp.fieldnotes.mzp;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import java.net.URL;
import java.util.List;

import junit.framework.AssertionFailedError;

import org.junit.Ignore;
import org.junit.Test;

public class TimelineTest {

	@Test
	@Ignore
	public void ネットワーク越しの取得ができること() throws Exception {

		Timeline timeline = new Timeline(new URL(
				"http://tddbc.heroku.com/mzp/public_timeline"));
		exercise(timeline);

	}

	@Test
	public void ローカルのリソースを使ったテストができること() throws Exception {

		Timeline timeline = new Timeline(Thread.currentThread()
				.getContextClassLoader().getResource("tweet.txt"));
		exercise(timeline);

	}

	private void exercise(Timeline timeline) {
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
		assertTweet(15, tweetList, Tweet.MENTION, Tweet.UNOFFICIAL_RT);
		assertTweet(16, tweetList, Tweet.NORMAL);
		assertTweet(17, tweetList, Tweet.HASH_TAG);
		assertTweet(18, tweetList, Tweet.MENTION);
		assertTweet(19, tweetList, Tweet.MENTION, Tweet.HASH_TAG);
	}

	private void assertTweet(int offset, List<Tweet> tweet,
			String... exptectType) {
		String result = tweet.get(offset).getTweet().split("\t")[0];
		int length = result.split(",").length;
		if (length != exptectType.length) {
			throw new AssertionFailedError(offset + "の解析結果の要素数が一致しません。期待値:"
					+ exptectType.length + "実際:" + length + "("
					+ tweet.get(offset).getTweet() + ")");
		}
		for (String type : exptectType) {
			assertThat(Integer.toString(offset), result,
					is(containsString(type)));
		}
	}
}
