package jp.fieldnotes.mzp;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.net.URL;

import org.junit.Test;

public class TimelineTest {

	@Test
	public void ネットワーク越しの取得ができること() {

		Timeline timeline = new Timeline(new URL("http://tddbc.heroku.com/mzp/public_timeline"))
		assertThat(timeline.getTweetList().size, is(20));
	}
}
