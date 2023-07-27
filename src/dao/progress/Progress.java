package dao.progress;
import java.lang.Math;

public class Progress {

    public double updateProgress(int bookPages, int pagesRead) {
        return Math.floor((pagesRead/bookPages) * 100);
    }

}

