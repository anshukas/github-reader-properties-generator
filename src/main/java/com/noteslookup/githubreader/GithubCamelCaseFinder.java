package com.noteslookup.githubreader;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GithubCamelCaseFinder {
	
	public static final List<String> ignoreAttrList = Collections
			.unmodifiableList(Arrays.asList(": ", "-server", "apiVersion", "namespace", "kind", "server.ssl.key-store-type", "server.ssl.key-store"));

	public static void main(String[] args) {
		System.out.println("##############################################");
		System.out.println("    # Github-CamelCase finder #  ");
		System.out.println("##############################################");

		String keyGithubRawFileUrl = "https://github.bedbath.com/raw/BBBYOrg/local-configmaps/master/rf-catalog-v2.properties?token=GHSAT0AAAAAAAAACPHVT3Q2S4ZYJ7IU6NHQY5VRVZA";
		
		try {
			GithubReaderV2 githubReader = new GithubReaderV2();
			List<String> lines = githubReader.getLinesAfterReadingGithubFile(keyGithubRawFileUrl);
			for (String keyLine : lines) {
				if(checkString(keyLine)) {
					System.out.println(keyLine);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static boolean checkString(String str) {
	    char ch;
	    boolean capitalFlag = false;
	    boolean lowerCaseFlag = false;
	    for(int i=0;i < str.length();i++) {
	        ch = str.charAt(i);
	        if (Character.isUpperCase(ch)) {
	            capitalFlag = true;
	        } else if (Character.isLowerCase(ch)) {
	            lowerCaseFlag = true;
	        }
	        if(capitalFlag && lowerCaseFlag)
	            return true;
	    }
	    return false;
	}
}
