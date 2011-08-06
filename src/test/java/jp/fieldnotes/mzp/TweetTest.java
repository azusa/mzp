package jp.fieldnotes.mzp;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class TweetTest {
	@Test
	public void replayでもmentionでもhashtagでもないtweetを普通のtweetと判定する() {
		Tweet tweet = createTweet("Alice", "あいうえお");
		assertThat(tweet.getTweet(), is("Normal\tあいうえお"));
	}

	@Test
	public void replayのtweetは普通のtweetと判定されない() throws Exception {
		Tweet tweet = createTweet("Alice", "@Bob あいうえお");
		assertThat(tweet.getTweet(), is("@Bob あいうえお"));
	}

	@Test
	@Ignore
	public void mentionのtweetは普通のtweetと判定されない() throws Exception {

	}

	@Test
	public void hashtagのtweetは普通のtweetと判定されない() throws Exception {
		Tweet tweet = createTweet("Alice", "あいうえお #hashtag");
		assertThat(tweet.getTweet(), is("あいうえお #hashtag"));

	}

	private Tweet createTweet(String alias, String body) {
		return new Tweet(alias + "\t" + body);
	}
}
