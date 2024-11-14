import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Player implements Runnable {
    private static int idCounter = 0;
    private final int id;
    private final Deck leftDeck;
    private final Deck rightDeck;
    private final Hand hand = new Hand();

    public Player(Deck leftDeck, Deck rightDeck) {
        this.id = idCounter++;
        this.leftDeck = leftDeck;
        this.rightDeck = rightDeck;
    }

    public Deck getLeftDeck(){
        return this.leftDeck;
    }

    public Deck getRightDeck(){
        return this.rightDeck;
    }

    @Override
    public void run() {
        // Game logic for the player, e.g., drawing and discarding cards
        while (!hand.isWinningHand()) {
            drawCard();
            discardCard();
            // Additional game logic here
        }
        System.out.println("Player " + (id+1) + " has won!");
    }

    public void drawCard() {
        Card cardToDraw = leftDeck.drawCard();
        hand.addCard(cardToDraw);
    }

    public void discardCard() {
        Card cardToDiscard = hand.discardCard();
        rightDeck.addCard(cardToDiscard);
    }

    public void addCardToHand(Card card){
        hand.addCard(card);
    }

    public void showCardsInHand() {
        hand.showCardsInHand(); // Delegate to Hand's method
    }



    private class Hand {
        private List<Card> cards = new ArrayList<>();

        public void addCard(Card card) {//id of card instead
            cards.add(card);
        }

        // public Card discardCard() {
        //     // Implement discard logic, e.g., selecting a card to discard
        //     return cards.remove(0); // Example of discarding the first card
        // }

        public Card discardCard() {
            if (cards.isEmpty()) {
                throw new IllegalStateException("No cards to discard.");
            }

            // Step 1: Count occurrences of card values
            Map<Integer, Integer> valueCount = new HashMap<>();
            for (Card card : cards) {
                int value = card.getValue();
                valueCount.put(value, valueCount.getOrDefault(value, 0) + 1);
            }

            // Step 2: Find the least occurring card value
            int leastOccurringValue = -1;
            int minCount = Integer.MAX_VALUE;

            for (Map.Entry<Integer, Integer> entry : valueCount.entrySet()) {
                if (entry.getValue() < minCount) {
                    minCount = entry.getValue();
                    leastOccurringValue = entry.getKey();
                }
            }

            // Step 3: Remove a card with the least occurring value
            for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i).getValue() == leastOccurringValue) {
                    return cards.remove(i);
                }
            }

            // Fallback: Remove the first card if all values are unique
            return cards.remove(0);
        }

        public boolean isWinningHand() {
            // Logic to check if the hand is winning
            return false; // Placeholder for actual winning condition
        }

        public void showCardsInHand(){
            for (Card card : cards){
                System.out.println("card id: " +card.getId()+ " card value: " + card.getValue());
            }
        }
    }
}
