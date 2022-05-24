package com.noteslookup.githubreader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class GithubReader {

	public List<String> getLinesAfterReadingGithubFile(String githubRawFileUrl) throws IOException {
		URL url = new URL(githubRawFileUrl);
		List<String> lines = Resources.readLines(url, Charsets.UTF_8);
		// lines.forEach(System.out::println);
		return lines;
	}

	public List<String> transformTheLines(List<String> lines, List<String> ignoreAttrlist) {
		List<String> finalList = new ArrayList<String>();
		for (String line : lines) {
			if (!line.contains(": ") || line.contains("-server"))
				continue;

			String[] linePartArray = line.split(": ");
			if (linePartArray.length == 2) {

				String key = linePartArray[0].trim().replace("_", ".");
				String value = linePartArray[1].trim().replace("\\\"", "'").replace("\"", "");
				String newLine = key + "=" + value;
				
				if (ignoreAttrlist.contains(key))
					continue;
				
				if ("name=nginx-configs".equalsIgnoreCase(newLine))
					break;

				finalList.add(newLine);
			}
		}
		finalList.forEach(System.out::println);
		return finalList;
	}
}
