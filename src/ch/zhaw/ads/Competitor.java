package ch.zhaw.ads;

import java.util.*;
import java.text.*;


public class Competitor implements Comparable<Competitor> { // TODO: Implement {
        private String name;
        private String time;
        private int rank;

        public Competitor(int rank, String name, String time)  {
            this.rank = rank;
            this.name = name;
            this.time = time;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public String getTime() {
            return time;
        }

        public String getName() {
            return name;
        }

        private static long parseTime(String s)  {
            try {
                DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                Date date = sdf.parse(s);
                return date.getTime();
            } catch (Exception e) {System.err.println(e);}
            return 0;
        }

        private static String timeToString(int time) {
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            return df.format(new Date(time));
        }

        public String toString() {
            return ""+ rank + " "+name+" "+time;
        }

        @Override
        public int compareTo(Competitor o) {
            return Long.compare(parseTime(this.time), parseTime(o.time));
        }

        @Override
        public boolean equals (Object o) {
          Optional<Competitor> tmpC = o instanceof Competitor ? Optional.of((Competitor) o) : Optional.empty();
          return tmpC.isPresent() && tmpC.get().name.equals(this.name) && tmpC.get().rank == this.rank && tmpC.get().time.equals(this.time);
        }

        @Override
        public int hashCode() {
          return this.hashCode();
        }

    }

    class AlphaComparatorCompetitor implements Comparator<Competitor> {

    @Override
    public int compare(Competitor o1, Competitor o2) {
        return o1.getName().toLowerCase().trim().compareTo(o2.getName().toLowerCase().trim());
    }
}
