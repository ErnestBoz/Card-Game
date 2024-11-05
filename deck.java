import java.util.concurrent.ConcurrentLinkedQueue;

public class Deck {
    private ConcurrentLinkedQueue<Card> cards = new ConcurrentLinkedQueue<>();

    public synchronized void addCard(Card card) {
        cards.add(card);
    }

    public synchronized Card drawCard() {
        return cards.poll();
    }

    public synchronized int getSize() {
        return cards.size();
    }
}
