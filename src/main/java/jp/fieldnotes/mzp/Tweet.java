package jp.fieldnotes.mzp;

public class Tweet {

	private final String tweet;

	public Tweet(String string) {
		this.tweet = string;
	}

	public String getTweet() {
		String[] elements = this.tweet.split("\t");
		String body = elements[elements.length - 1];
		if (body.startsWith("@")) {
			return "Reply\t" + body;
		} else if (body.contains("@")) {
			return body;
		} else if (body.contains("#")) {
			return "!HashTag\t" + body;
		} else {
			return "Normal\t" + body;
		}
	}
}
