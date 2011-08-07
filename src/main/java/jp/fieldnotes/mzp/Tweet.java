package jp.fieldnotes.mzp;

import java.util.ArrayList;
import java.util.List;

public class Tweet {

	public static final String NORMAL = "Normal";

	public static final String HASH_TAG = "!HashTag";

	public static final String MENTION = "Mention";

	public static final String REPLY = "Reply";

	private final String tweet;

	public Tweet(String string) {
		this.tweet = string;
	}

	public String getTweet() {
		String[] elements = this.tweet.split("\t");
		String body = elements[elements.length - 1];
		List<String> judge = new ArrayList<String>();
		if (body.startsWith("@")) {
			judge.add(REPLY);
		}
		if (body.substring(1).contains("@")) {
			judge.add(MENTION);
		}
		if (body.contains("#")) {
			judge.add(HASH_TAG);
		}
		if (isUnOfficialRT()) {
			judge.add("UnofficialRT");
		}

		if (judge.isEmpty()) {
			return NORMAL + "\t" + body;
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

	boolean isUnOfficialRT() {
		return this.tweet.contains("RT") || tweet.contains("QT")
				|| tweet.contains("MT");
	}
}
