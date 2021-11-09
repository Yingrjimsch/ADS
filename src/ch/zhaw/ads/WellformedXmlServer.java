package ch.zhaw.ads;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WellformedXmlServer implements CommandExecutor {

  private ListStack stack;
  @Override
  public String execute(String command) throws Exception {
    return String.format("XML is %svalid\n", checkWellformed(command) ? "" : "not ");
  }
  public boolean checkWellformed(String command) {
    List<String> extractTagsFromText = extractTagsFromText(command);
    return validateXML(extractTagsFromText);
  }
  private String getNextToken() {
    return ((String) stack.pop());
  }

  private List<String> extractTagsFromText(String text) {
    stack = new ListStack( );
    List<String> dirtyTags = new ArrayList<>();
    Matcher tagMatcher = Pattern.compile("<[^>]*[^\\/]>").matcher(text);
    while (tagMatcher.find()) {
      dirtyTags.add(tagMatcher.group());
    }
    return removeAttributesFromTags(dirtyTags);
  }
  /**
   * This methos validates the passed XML
   *
   * @param tags
   * @return
   */
  private boolean validateXML(List<String> tags) {
    // If the length of the tags is not even, it can't be valid
    if (tags.size() % 2 != 0) {
      return false;
    }
    for (String currentTag : tags) {
      if (!isClosingTag(currentTag)) {
        stack.push(currentTag);
      } else {
        String tagFromStack = getNextToken();
        if (!tagFromStack.equals(currentTag.replace("/", ""))) {
          return false;
        }
      }
    }
    return true;
  }
  private boolean isClosingTag(String tag) {
    return tag.startsWith("</");
  }
  private List<String> removeAttributesFromTags(List<String> tagsList) {
    List<String> cleanList = new ArrayList<>();
    tagsList.stream().map(tag -> tag.split(" ")).forEach(tag -> cleanList.add(tag[0] + (tag.length > 1 ? ">" : "")));
    return cleanList;
  }
}
