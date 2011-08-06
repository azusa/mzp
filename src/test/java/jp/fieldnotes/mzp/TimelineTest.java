package jp.fieldnotes.mzp;

import static org.junit.Assert.*;

import org.junit.Test;

public class TimelineTest {

	@Test
	public void ネットワーク越しの取得ができること() {

		assertThat(timeline.getTweetList().size, is(20));
	}

}
