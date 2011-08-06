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
		}
		if (body.substring(1).contains("@")) {
			judge.add("Mention");
		}
		if (body.contains("#")) {
			judge.add("!HashTag");
		}
		if (judge.isEmpty()) {
			return "Normal\t" + body;
		}
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < judge.size(); i++) {
			if (i != 0) {
				result.append(",");
			}
			result.append(judge.get(i));
		}
		return result.append("\t").append(body).toString();
	}
}
