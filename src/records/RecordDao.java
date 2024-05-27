package records;

import java.util.List;

public interface RecordDao{
    public List<GameRecord> Get_All_Records();
    public void doAdd(GameRecord gameRecord);
    public void doDelete(String gamename);
}
