package ch.zhaw.ads;

public class BracketServer implements CommandExecutor {

  private static final String NEW_LINE = "\n";
  @Override
  public String execute(String s) throws Exception {
    return (checkBrackets(s) ? "Gültig" : "Ungültig") + NEW_LINE;
  }

  public boolean checkBrackets(String command)  {
    ListStack brackets = new ListStack();
    command = refactorComment(command);
    for (char c : command.toCharArray()) {
      Brackets bracket = Brackets.getBracketType(c);
      if (bracket != Brackets.NONE) {
        if (bracket.isOpening(c)) {
          brackets.push(bracket);
        }
        if (bracket.isClosing(c)) {
          if (brackets.isEmpty()) {
            return false;
          }
          if (brackets.peek() == bracket) {
            brackets.pop();
          }
        }
      }
    }
    return brackets.isEmpty();
  }

  private String refactorComment(String command) {
    command = command.replace("/*", "`");
    command = command.replace("*/", "~");
    return command;
  }

  private enum Brackets {
    ROUND('(', ')'),
    SQUARE('[', ']'),
    CURLY('{', '}'),
    ANGLE('<', '>'),
    COMMENT('`', '~'),
    NONE();

    char opening;
    char closing;

    Brackets(char opening, char closing) {
      this.opening = opening;
      this.closing = closing;
    }

    Brackets() {}

    boolean isOpening(char wannabeBracket) {
      return this.opening == wannabeBracket;
    }

    boolean isClosing(char wannabeBracket) {
      return  this.closing == wannabeBracket;
    }

    static Brackets getBracketType(char wannabeBracket) {
      switch (wannabeBracket) {
        case '(':
        case ')':
          return ROUND;
        case '[':
        case ']':
          return SQUARE;
        case '{':
        case '}':
          return CURLY;
        case '<':
        case '>':
          return ANGLE;
        case '`':
        case '~':
          return COMMENT;
        default:
          return NONE;
      }
    }
  }
}
