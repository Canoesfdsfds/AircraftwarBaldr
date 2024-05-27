package records;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RecordDaoImpl implements RecordDao {
    private List<GameRecord> records;

    public RecordDaoImpl() {
        this.records = new ArrayList<>();
    }

    @Override
    public List<GameRecord> Get_All_Records() {
        return records;
    }

    @Override
    public void doAdd(GameRecord gameRecord) {
        records.add(gameRecord);
        Collections.sort(records, Comparator.comparingInt(GameRecord::getGameScore).reversed());
    }

    @Override
    public void doDelete(String gamename) {
        records.removeIf(record -> record.getGameName().equals(gamename));
    }
}