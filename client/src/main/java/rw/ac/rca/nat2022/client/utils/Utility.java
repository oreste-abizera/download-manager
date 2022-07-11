package rw.ac.rca.nat2022.client.utils;

public class Utility {
    public static String formatURL(String url) {
        return "http://localhost:8000/" + url;
    }
    public static String formatTime(Long time) {
        return String.format("%d:%d:%d", time / 3600, (time % 3600) / 60, (time % 60));
    }
}
