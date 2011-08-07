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
	public void mentionのtweetがmentionのtweetとして判定される() throws Exception {
		Tweet tweet = createTweet("Alice", "あいうえお @Bob");
		assertThat(tweet.getTweet(), is("Mention\tあいうえお @Bob"));

	}

	@Test
	public void hashtagのtweetがhashtagのtweetとして判定される() throws Exception {
		Tweet tweet = createTweet("Alice", "あいうえお #hashtag");
		assertThat(tweet.getTweet(), is("!HashTag\tあいうえお #hashtag"));

	}

	@Test
	public void 複数の種類がある場合_カンマ区切りで出力される() {
		Tweet tweet = createTweet("Alice", "@Alice あいうえお@Bob #hashtag");
		assertThat(tweet.getTweet(),
				is("Reply,Mention,!HashTag\t@Alice あいうえお@Bob #hashtag"));

	}

	@Test
	public void 非公式RTの判定ができること() {
		Tweet tweet = createTweet("Mallor", "ふむ RT @Alice: ReTweetって2種類あるのね");
		assertThat(tweet.isUnOfficialRT(), is(true));
	}

	@Test
	public void 通常のtweetは非公式RTとして判定されないこと() {
		Tweet tweet = createTweet("Mallor", "@Alice あいうえお@Bob #hashtag");
		assertThat(tweet.isUnOfficialRT(), is(false));
	}

	private Tweet createTweet(String alias, String body) {
		return new Tweet("2011/08/06 00:01:02\t" + alias + "\t" + body);
	}
}
