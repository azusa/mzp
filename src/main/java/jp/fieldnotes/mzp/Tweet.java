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
			return body;
		} else {
			return "Normal\t" + body;
		}
	}
}
