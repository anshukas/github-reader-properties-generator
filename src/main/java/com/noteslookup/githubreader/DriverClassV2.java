package com.noteslookup.githubreader;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DriverClassV2 {
	
	public static final List<String> ignoreAttrList = Collections
			.unmodifiableList(Arrays.asList(": ", "-server", "apiVersion", "namespace", "kind", "server.ssl.key-store-type", "server.ssl.key-store"));

	public static void main(String[] args) {
		System.out.println("##############################################");
		System.out.println("    # Github-reader-properties-generator #  ");
		System.out.println("##############################################");

		String githubRawFileUrl = "https://github.bedbath.com/raw/BBBYOrg/ecom-devops-k8s/master/configmaps/prod-e4/ms-configs-preview.yaml?token=GHSAT0AAAAAAAAACPHUKDWDS6OUZU4A27BIY56XOMQ";
		String keyRawFileUrlGithub = "https://github.bedbath.com/raw/BBBYOrg/local-configmaps/master/rf-catalog-v2.properties?token=GHSAT0AAAAAAAAACPHVT3Q2S4ZYJ7IU6NHQY5VRVZA";
		
		try {
			GithubReaderV2 githubReader = new GithubReaderV2();
			List<String> lines = githubReader.getLinesAfterReadingGithubFile(githubRawFileUrl);
			List<String> keysLines =  githubReader.getLinesAfterReadingGithubFile(keyRawFileUrlGithub);
			githubReader.transformTheLines(lines, ignoreAttrList, keysLines);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
