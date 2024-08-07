package cn.tealc.wutheringwavestool.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @program: WutheringWavesTool
 * @description:
 * @author: Leck
 * @create: 2024-07-03 00:38
 */
public class Setting {
    public SimpleStringProperty gameRootDir=new SimpleStringProperty();
    private SimpleBooleanProperty firstViewWithPoolAnalysis=new SimpleBooleanProperty(false);

    public String getGameRootDir() {
        return gameRootDir.get();
    }

    public SimpleStringProperty gameRootDirProperty() {
        return gameRootDir;
    }

    public void setGameRootDir(String gameRootDir) {
        this.gameRootDir.set(gameRootDir);
    }

    public boolean isFirstViewWithPoolAnalysis() {
        return firstViewWithPoolAnalysis.get();
    }

    public SimpleBooleanProperty firstViewWithPoolAnalysisProperty() {
        return firstViewWithPoolAnalysis;
    }

    public void setFirstViewWithPoolAnalysis(boolean firstViewWithPoolAnalysis) {
        this.firstViewWithPoolAnalysis.set(firstViewWithPoolAnalysis);
    }
}