package ch.zhaw.ads;

import java.util.*;
import java.util.stream.Collectors;

public class RankingListServer implements CommandExecutor {

  private static final String XML_SEPARATOR = ";";
  private static final String LINE_BREAK = "\n";

    public List<Competitor> createList(String rankingText) {
      List<Competitor> competitors = new ArrayList<>();
        Scanner scanner = new Scanner(rankingText);
        while (scanner.hasNextLine()) {
          String[] res = scanner.nextLine().split(XML_SEPARATOR);
          competitors.add(new Competitor(0, res[0], res[1]));
        }

        return competitors;
    }

    public String createSortedText(List<Competitor> competitorList) {
        //competitorList.stream().sorted(Competitor::compareTo).forEach(c -> c.setRank(competitorList.indexOf(c) + 1));
      competitorList.sort(Competitor::compareTo);
        return competitorList.stream().sorted(Competitor::compareTo)
          .map(c -> {
            c.setRank(competitorList.indexOf(c) + 1);
            return c.toString();
          }).collect(Collectors.joining(LINE_BREAK));
    }

    public String createNameList(List<Competitor> competitorList) {
        return competitorList.stream().sorted((o1, o2) -> {
          int nr = o1.getName().compareTo(o2.getName());
          nr = nr == 0 ? o1.compareTo(o2) : nr;
          return nr;
        }).map(Competitor::toString).collect(Collectors.joining(LINE_BREAK));
    }

    public String execute(String rankingList) throws Exception {
        List<Competitor> competitorList = createList(rankingList);
        return "Rangliste\n" + createSortedText(competitorList);
    }

}
