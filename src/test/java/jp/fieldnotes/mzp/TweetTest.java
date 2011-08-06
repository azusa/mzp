package jp.fieldnotes.mzp;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class TweetTest {
	@Test
	public void replayでもmentionでもhashtagでもないtweetを普通のtweetと判定する() {
		Tweet tweet = new Tweet("Alice\tあいうえお");
		assertThat(tweet.getTweet(), is("Normal\tあいうえお"));
	}

	@Test
	@Ignore
	public void replayのtweetは普通のtweetと判定されない() throws Exception {

	}

	@Test
	@Ignore
	public void mentionのtweetは普通のtweetと判定されない() throws Exception {

	}

	@Test
	@Ignore
	public void hashtagのtweetは普通のtweetと判定されない() throws Exception {

	}

}
