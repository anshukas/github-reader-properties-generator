package com.noteslookup.githubreader;

import java.io.IOException;
import java.util.List;

public class GithubExtractLastWord {
	
	public static void main(String[] args) {
		System.out.println("##############################################");
		System.out.println("    # Github-ExtractLastWord #  ");
		System.out.println("##############################################");

		String keyGithubRawFileUrl = "https://github.bedbath.com/raw/BBBYOrg/local-configmaps/master/rf-catalog-v2.properties?token=GHSAT0AAAAAAAAACPHVT3Q2S4ZYJ7IU6NHQY5VRVZA";
		
		try {
			GithubReaderV2 githubReader = new GithubReaderV2();
			List<String> lines = githubReader.getLinesAfterReadingGithubFile(keyGithubRawFileUrl);
			StringBuffer buffer = new StringBuffer();
			for (String keyLine : lines) {
				String extractedWrd = extractLastWord(keyLine);
				buffer.append(extractedWrd);
				buffer.append("|");
			}
			System.out.println(buffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String extractLastWord(String pKeyLine) {
		int ILastIndexOf = pKeyLine.lastIndexOf('/');
		return pKeyLine.substring(ILastIndexOf + 1);
	}
	
}
