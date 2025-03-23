public class Score implements Comparable<Score> {
    private int score;
    private String name;

    public Score(int score, String name) {
        this.name = name;
        this.score = score;
    }

    public void increase() {
        score++;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Score o) {
        return Integer.compare(this.score, o.score);
    }
}