package cn.tealc.wutheringwavestool.model.game;


public class GameRecordForLog extends GameRecord {
    private Long closeTime; //下线时间


    public Long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }
}