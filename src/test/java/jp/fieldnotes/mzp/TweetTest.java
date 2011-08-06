package jp.fieldnotes.mzp;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TweetTest {
	@Test
	public void replayでもmentionでもhashtagでもないtweetを普通のtweetと判定する() {
		Tweet tweet = createTweet("Alice", "あいうえお");
		assertThat(tweet.getTweet(), is("Normal\tあいうえお"));
	}

	@Test
	public void replayのtweetがReplyのtweetとして判定される() throws Exception {
		Tweet tweet = createTweet("Alice", "@Bob あいうえお");
		assertThat(tweet.getTweet(), is("Reply\t@Bob あいうえお"));
	}

	@Test
	public void mentionのtweetは普通のtweetと判定されない() throws Exception {
		Tweet tweet = createTweet("Alice", "あいうえお @Bob");
		assertThat(tweet.getTweet(), is("あいうえお @Bob"));

	}

	@Test
	public void hashtagのtweetがhashtagのtweetとして判定される() throws Exception {
		Tweet tweet = createTweet("Alice", "あいうえお #hashtag");
		assertThat(tweet.getTweet(), is("!HashTag\tあいうえお #hashtag"));

	}

	private Tweet createTweet(String alias, String body) {
		return new Tweet(alias + "\t" + body);
	}
}
