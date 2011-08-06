package jp.fieldnotes.mzp;

import java.util.ArrayList;
import java.util.List;

public class Tweet {

	private final String tweet;

	public Tweet(String string) {
		this.tweet = string;
	}

	public String getTweet() {
		String[] elements = this.tweet.split("\t");
		String body = elements[elements.length - 1];
		List<String> judge = new ArrayList<String>();
		if (body.startsWith("@")) {
			judge.add("Reply");
		} else if (body.contains("@")) {
			judge.add("Mention");
		} else if (body.contains("#")) {
			judge.add("!HashTag");
		}
		if (judge.isEmpty()) {
			return "Normal\t" + body;
		}
		return null;
	}
}
