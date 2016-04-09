package com.util;

import java.io.IOException;
import java.util.List;

import info.bliki.api.Page;
import info.bliki.api.User;
import info.bliki.wiki.model.WikiModel;

public class WikiUtil {

	public static String provaWikiRender() throws IOException {
		String[] listOfTitleStrings = { "Brad Pitt" };
		User user = new User("", "", "http://en.wikipedia.org/w/api.php");
		user.login();
		List<Page> listOfPages = user.queryContent(listOfTitleStrings);
		for (Page page : listOfPages) {
		  WikiModel wikiModel = new WikiModel("${image}", "${title}");
		  String html = wikiModel.render(page.toString());
		  System.out.println(html);
		}
		return null;
	}
}
