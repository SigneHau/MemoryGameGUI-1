class Card {
    private String picture;
    private String description;
    boolean cardFlipped;

    public Card(String picture, String description) {
        this.picture = picture;
        this.description = description;
        this.cardFlipped = false;
    }

    public String getPicture() {
        return picture;
    }

    public String getDescription() {
        return description;
    }
}