package jp.fieldnotes.mzp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Timeline {

	private final List<Tweet> tweetList = new ArrayList<Tweet>();

	public Timeline(URL url) {
		URLConnection connection = null;
		BufferedReader br = null;
		try {
			connection = url.openConnection();
			br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line = br.readLine();
			while (line != null) {
				tweetList.add(new Tweet(line));
				line = br.readLine();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}

		}
	}

	public List<Tweet> getTweetList() {
		return tweetList;
	}

}
