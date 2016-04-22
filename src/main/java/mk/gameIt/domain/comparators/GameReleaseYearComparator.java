package mk.gameIt.domain.comparators;

import mk.gameIt.domain.Game;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by Stefan on 16.04.2016.
 */
public class GameReleaseYearComparator implements Comparator<Game> {
    @Override
    public int compare(Game o1, Game o2) {
        return o1.getGameReleaseYear().compareTo(o2.getGameReleaseYear());
    }
}
