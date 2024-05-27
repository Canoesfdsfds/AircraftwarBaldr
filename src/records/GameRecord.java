package records;

public class GameRecord {
    private String GameName;
    private int GameScore;
    private String GameDate;
    public GameRecord(String GameName, int GameScore, String GameDate)
    {
        this.GameName = GameName;
        this.GameScore = GameScore;
        this.GameDate = GameDate;
    }
    public String getGameName()
    {
        return GameName;
    }
    public int getGameScore()
    {
        return GameScore;
    }
    public String getGameDate()
    {
        return GameDate;
    }
    public void setGameName(String GameName)
    {
        this.GameName = GameName;
        return;
    }
    public void setGameScore(int GameScore)
    {
        this.GameScore = GameScore;
        return;
    }
    public void setGameDate(String GameDate)
    {
        this.GameDate = GameDate;
        return;
    }
}
