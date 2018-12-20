import java.time.LocalDateTime;

public class NowTime {
    LocalDateTime date = LocalDateTime.now();

    int hh = date.getHour();
    int mm = date.getMinute();
    int ss = date.getSecond();

    public int getHh() {
        return hh;
    }

    public int getMm() {
        return mm;
    }

    public int getSs() {
        return ss;
    }

}
