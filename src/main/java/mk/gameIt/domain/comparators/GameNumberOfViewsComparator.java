package mk.gameIt.domain.comparators;

import mk.gameIt.domain.Game;

import java.util.Comparator;

/**
 * Created by Stefan on 16.04.2016.
 */
public class GameNumberOfViewsComparator implements Comparator<Game> {
    @Override
    public int compare(Game o1, Game o2) {
        return Long.compare(o2.getGameNumberOfViews(),o1.getGameNumberOfViews());
    }
}
