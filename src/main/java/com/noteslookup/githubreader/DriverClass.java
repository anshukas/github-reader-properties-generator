package com.noteslookup.githubreader;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DriverClass {
	
	public static final List<String> ignoreAttrList = Collections
			.unmodifiableList(Arrays.asList(": ", "-server", "apiVersion", "namespace", "kind", "server.ssl.key-store-type", "server.ssl.key-store"));

	public static void main(String[] args) {
		System.out.println("##############################################");
		System.out.println("    # Github-reader-properties-generator #  ");
		System.out.println("##############################################");

		String githubRawFileUrl = "https://github.com/develop/configmaps/ms-configs-live.yaml?token=";
		
		try {
			GithubReader githubReader = new GithubReader();
			List<String> lines = githubReader.getLinesAfterReadingGithubFile(githubRawFileUrl);
			lines = githubReader.transformTheLines(lines, ignoreAttrList);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
