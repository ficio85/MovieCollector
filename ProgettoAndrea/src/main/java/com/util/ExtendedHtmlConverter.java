package com.util;

import info.bliki.htmlcleaner.ContentToken;
import info.bliki.htmlcleaner.EndTagToken;
import info.bliki.htmlcleaner.TagNode;
import info.bliki.htmlcleaner.Utils;
import info.bliki.wiki.filter.HTMLConverter;
import info.bliki.wiki.model.Configuration;
import info.bliki.wiki.model.IWikiModel;
import info.bliki.wiki.model.ImageFormat;
import info.bliki.wiki.tags.HTMLTag;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A converter which renders the internal tree node representation as specific
 * HTML text, but which is easier to change in behaviour than its superclass and
 * has a noImages property, which can be set to leave out all images
 *
 */
public class ExtendedHtmlConverter extends HTMLConverter {

  private boolean noImages;

  public ExtendedHtmlConverter() {
    super();
  }

  public ExtendedHtmlConverter(boolean noLinks) {
    super(noLinks);
  }

  public ExtendedHtmlConverter(boolean noLinks, boolean noImages) {
    this(noLinks);
    this.noImages = noImages;
  }

  protected void renderContentToken(Appendable resultBuffer,
      ContentToken contentToken, IWikiModel model) throws IOException {
    String content = contentToken.getContent();
    content = Utils.escapeXml(content, true, true, true);
    resultBuffer.append(content);
  }

  protected void renderHtmlTag(Appendable resultBuffer, HTMLTag htmlTag,
      IWikiModel model) throws IOException {
    htmlTag.renderHTML(this, resultBuffer, model);
  }

  protected void renderTagNode(Appendable resultBuffer, TagNode tagNode,
      IWikiModel model) throws IOException {
    Map<String, Object> map = tagNode.getObjectAttributes();
    if (map != null && map.size() > 0) {
      Object attValue = map.get("wikiobject");
      if (!noImages) {
        if (attValue instanceof ImageFormat) {
          imageNodeToText(tagNode, (ImageFormat) attValue, resultBuffer, model);
        }
      }
    } else {
      nodeToHTML(tagNode, resultBuffer, model);
    }
  }

  public void nodesToText(List<? extends Object> nodes,
      Appendable resultBuffer, IWikiModel model) throws IOException {
    if (nodes != null && !nodes.isEmpty()) {
      try {
        int level = model.incrementRecursionLevel();

        if (level > Configuration.RENDERER_RECURSION_LIMIT) {
          resultBuffer
              .append("<span class=\"error\">Error - recursion limit exceeded rendering tags in HTMLConverter#nodesToText().</span>");
          return;
        }
        Iterator<? extends Object> childrenIt = nodes.iterator();
        while (childrenIt.hasNext()) {
          Object item = childrenIt.next();
          if (item != null) {
            if (item instanceof List) {
              nodesToText((List) item, resultBuffer, model);
            } else if (item instanceof ContentToken) {
              // render plain text content
              ContentToken contentToken = (ContentToken) item;
              renderContentToken(resultBuffer, contentToken, model);
            } else if (item instanceof HTMLTag) {
              HTMLTag htmlTag = (HTMLTag) item;
              renderHtmlTag(resultBuffer, htmlTag, model);
            } else if (item instanceof TagNode) {
              TagNode tagNode = (TagNode) item;
              renderTagNode(resultBuffer, tagNode, model);
            } else if (item instanceof EndTagToken) {
              EndTagToken node = (EndTagToken) item;
              resultBuffer.append('<');
              resultBuffer.append(node.getName());
              resultBuffer.append("/>");
            }
          }
        }
      } finally {
        model.decrementRecursionLevel();
      }
    }
  }

  protected void nodeToHTML(TagNode node, Appendable resultBuffer,
      IWikiModel model) throws IOException {
    super.nodeToHTML(node, resultBuffer, model);
  }

}