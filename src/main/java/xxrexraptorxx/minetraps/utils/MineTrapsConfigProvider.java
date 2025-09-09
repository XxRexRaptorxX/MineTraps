package xxrexraptorxx.minetraps.utils;

import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.List;

public class MineTrapsConfigProvider implements SimpleConfig.DefaultConfig {

    private String configContents = "";

    public List<Pair> getConfigsList() {
        return configsList;
    }

    private final List<Pair> configsList = new ArrayList<>();

    public void addKeyValuePair(Pair<String, ?> keyValuePair, String comment, String info) {
        configsList.add(keyValuePair);
        configContents += "# " + info + "\n" + keyValuePair.getFirst() + "=" + keyValuePair.getSecond() + " #" + comment
                + " | default: " + keyValuePair.getSecond() + "\n\n";
    }

    @Override
    public String get(String namespace) {
        return configContents;
    }
}
