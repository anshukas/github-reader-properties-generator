package com.noteslookup.githubreader;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class GithubReaderV2 {

	public List<String> getLinesAfterReadingGithubFile(String githubRawFileUrl) throws IOException {
		URL url = new URL(githubRawFileUrl);
		List<String> lines = Resources.readLines(url, Charsets.UTF_8);
		// TODO: @Value("${bannerx.html.template}")
		// lines.forEach(System.out::println);
		return lines;
	}

	public void transformTheLines(List<String> lines, List<String> ignoreAttrlist, List<String> pKeysLinesList) {
		Set<String> finalList = new HashSet<String>();
		Set<String> remainList = new HashSet<String>();
		Set<String> keysLinesContainsList = new HashSet<String>();
		Set<String> pKeysLinesSet = new HashSet<String>(pKeysLinesList);
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
				
				boolean isKeysLinesContains = pKeysLinesSet.stream().anyMatch(i -> i.equalsIgnoreCase(key));
				if(!isKeysLinesContains) {
					continue;
				} else {
					keysLinesContainsList.add(key);
				}
				
				if ("name=nginx-configs".equalsIgnoreCase(newLine))
					break;

				finalList.add(newLine);
			}
		}
		finalList.forEach(System.out::println);
		
		System.out.println("##############################################");
		System.out.println("    # pKeysLines still missing #  ");
		System.out.println("##############################################");
		
		pKeysLinesSet.removeAll(keysLinesContainsList);
		
		for (String key : pKeysLinesSet) {
			boolean isKeysLinesContains = keysLinesContainsList.stream().anyMatch(i -> i.equalsIgnoreCase(key));
			boolean isKeyInFinaList = finalList.stream().anyMatch(i -> i.equalsIgnoreCase(key));
			if(!isKeysLinesContains && !isKeyInFinaList) {
				remainList.add(key);
			}
		}
		remainList.forEach(System.out::println);
	}
}
