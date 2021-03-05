import java.util.ArrayList;
import java.util.List;

public class BlackJackHandCards extends HandCards {

    @Override
    public int score() {
        List<Integer> scores = generateScores();
        int maxUnder = Integer.MIN_VALUE;
        int minOver = Integer.MAX_VALUE;
        for (int score : scores) {
            if (score > 21 && score < minOver) {
                minOver = score;
            }
            else if (score < 21 && score > maxUnder) {
                maxUnder = score;
            }
        }
        return maxUnder == Integer.MIN_VALUE ? minOver : maxUnder;
    }

    private List<Integer> generateScores() {
        List<Integer> scores = new ArrayList<>();
        for (Card card : cards) {
            updateScores(card, scores);
        }
        return scores;
    }

    private void updateScores(Card card, List<Integer> scores) {
        final int[] toAdd = getScore(card);
        if (scores.isEmpty()) {
            for (int score : toAdd) {
                scores.add(score);
            }
        }
        else {
            final int length = scores.size();
            for (int i = 0; i < length; i++) {
                int oldScore = scores.get(i);
                scores.set(i, oldScore + toAdd[0]);
                for (int j = 1; j < toAdd.length; j++) {
                    scores.add(oldScore + toAdd[j]);
                }
            }
        }
    }
    private int[] getScore(Card card) {
        if (card.getFaceValue() > 1) {
            return new int[]{Math.min(card.getFaceValue(), 10)};
        }
        else {
            // A has two possible values 1 and 11
            return new int[] {1, 11};
        }
    }

    public boolean isBusted() {
        return score() > 21;
    }

    // two cards and its score are 21, then we call it blackjack
    public boolean isBackJack() {
        if (cards.size() != 2) {
            return false;
        }
        Card first = cards.get(0);
        Card second = cards.get(1);
        return (isAce(first) && isFaceCard(second)) || (isAce(second) && isFaceCard(first));
    }
    private static boolean isAce(Card card) {
        return card.getFaceValue() == 1;
    }
    private static boolean isFaceCard(Card card) {
        return card.getFaceValue() <= 13 && card.getFaceValue() >= 10;
    }
}
