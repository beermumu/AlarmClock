public class Alarm {

    private int hour;
    private int minute;
    private int second;

    public void setTime(int h, int m , int s) {
        if (h >= 0 && h <= 23 && m >= 0 && m <= 59 && s<=59 && s>=0) {
            hour = h;
            minute = m;
            second = s;
        }
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }
}
