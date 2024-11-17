package cn.tealc.wutheringwavestool.ui.game;

import cn.tealc.wutheringwavestool.base.Config;
import cn.tealc.wutheringwavestool.dao.GameRecordDao;
import cn.tealc.wutheringwavestool.dao.UserInfoDao;
import cn.tealc.wutheringwavestool.model.SourceType;
import cn.tealc.wutheringwavestool.model.game.GameRecord;
import com.kuro.kujiequ.model.sign.UserInfo;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.SimpleIntegerProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;


/**
 * @program: WutheringWavesTool
 * @description:
 * @author: Leck
 * @create: 2024-11-16 22:44
 */
public class GameRecordViewModel implements ViewModel {

    private final SimpleIntegerProperty battle = new SimpleIntegerProperty();
    private final SimpleIntegerProperty paralysis = new SimpleIntegerProperty();
    private final SimpleIntegerProperty parry = new SimpleIntegerProperty();
    private final SimpleIntegerProperty parryAttack = new SimpleIntegerProperty();
    private final SimpleIntegerProperty phantomGet = new SimpleIntegerProperty();
    private final SimpleIntegerProperty phantomSkill = new SimpleIntegerProperty();
    private final SimpleIntegerProperty roleChange = new SimpleIntegerProperty();
    private final SimpleIntegerProperty roleDeath = new SimpleIntegerProperty();
    private final SimpleIntegerProperty totalBattle = new SimpleIntegerProperty();
    private final SimpleIntegerProperty totalParalysis = new SimpleIntegerProperty();
    private final SimpleIntegerProperty totalParry = new SimpleIntegerProperty();
    private final SimpleIntegerProperty totalParryAttack = new SimpleIntegerProperty();
    private final SimpleIntegerProperty totalPhantomGet = new SimpleIntegerProperty();
    private final SimpleIntegerProperty totalPhantomSkill = new SimpleIntegerProperty();
    private final SimpleIntegerProperty totalRoleChange = new SimpleIntegerProperty();
    private final SimpleIntegerProperty totalRoleDeath = new SimpleIntegerProperty();
    private final SimpleIntegerProperty totalTransfer = new SimpleIntegerProperty();
    private final SimpleIntegerProperty transfer = new SimpleIntegerProperty();


    public GameRecordViewModel() {
        GameRecordDao recordDao = new GameRecordDao();
        String today = getToday();

        List<GameRecord> list = recordDao.getRecordListByDate(today);
        updateTodayData(list);
        List<GameRecord> allRecordList = recordDao.getAllRecordList();

        updateTotalData(allRecordList);


        //后续考虑进行区分
  /*      if (Config.setting.getGameRootDirSource() == SourceType.GLOBAL){ //国际服
            List<GameRecord> list = recordDao.getRecordListByDate(today);
            updateTodayData(list);
            List<GameRecord> allRecordList = recordDao.getAllRecordList();
            updateTodayData(allRecordList);
        }else { //国服
            UserInfoDao userInfoDao = new UserInfoDao();
            UserInfo main = userInfoDao.getMain();
            if (main != null) {
                GameRecord record = recordDao.getRecordByRoleIdAndDate(main.getRoleId(), today);
                updateTodayData(List.of(record));
            }
        }*/





    }

    private void updateTodayData(List<GameRecord> list) {
        roleChange.set(0);
        roleDeath.set(0);
        battle.set(0);
        phantomGet.set(0);
        phantomSkill.set(0);
        paralysis.set(0);
        transfer.set(0);
        parry.set(0);
        parryAttack.set(0);
        for (GameRecord gameRecord : list) {
            roleChange.set(roleChange.get() + gameRecord.getRoleChange());
            roleDeath.set(roleDeath.get()+ gameRecord.getRoleDeath());
            battle.set(battle.get()+gameRecord.getBattle());
            phantomGet.set(phantomGet.get()+gameRecord.getPhantomGet());
            phantomSkill.set(phantomSkill.get()+gameRecord.getPhantomCallSkill() + gameRecord.getPhantomTransformSkill());
            paralysis.set(paralysis.get()+gameRecord.getParalysis());
            transfer.set(transfer.get()+gameRecord.getTransfer());
            parry.set(parry.get()+gameRecord.getParryFront() + gameRecord.getParryBack());
            parryAttack.set(parryAttack.get()+gameRecord.getParryAttack());
        }
    }


    private void updateTotalData(List<GameRecord> list) {
        totalRoleChange.set(0);
        totalRoleDeath.set(0);
        totalBattle.set(0);
        totalPhantomGet.set(0);
        totalPhantomSkill.set(0);
        totalParalysis.set(0);
        totalTransfer.set(0);
        totalParry.set(0);
        totalParryAttack.set(0);
        for (GameRecord gameRecord : list) {
            totalRoleChange.set(totalRoleChange.get() + gameRecord.getRoleChange());
            totalRoleDeath.set(totalRoleDeath.get()+ gameRecord.getRoleDeath());
            totalBattle.set(totalBattle.get()+gameRecord.getBattle());
            totalPhantomGet.set(totalPhantomGet.get()+gameRecord.getPhantomGet());
            totalPhantomSkill.set(totalPhantomSkill.get()+gameRecord.getPhantomCallSkill() + gameRecord.getPhantomTransformSkill());
            totalParalysis.set(totalParalysis.get()+gameRecord.getParalysis());
            totalTransfer.set(totalTransfer.get()+gameRecord.getTransfer());
            totalParry.set(totalParry.get()+gameRecord.getParryFront() + gameRecord.getParryBack());
            totalParryAttack.set(totalParryAttack.get()+gameRecord.getParryAttack());
        }
    }



    private String getToday(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDate.format(formatter);
    }

    public int getBattle() {
        return battle.get();
    }

    public SimpleIntegerProperty battleProperty() {
        return battle;
    }

    public void setBattle(int battle) {
        this.battle.set(battle);
    }

    public int getParalysis() {
        return paralysis.get();
    }

    public SimpleIntegerProperty paralysisProperty() {
        return paralysis;
    }

    public void setParalysis(int paralysis) {
        this.paralysis.set(paralysis);
    }

    public int getParry() {
        return parry.get();
    }

    public SimpleIntegerProperty parryProperty() {
        return parry;
    }

    public void setParry(int parry) {
        this.parry.set(parry);
    }

    public int getParryAttack() {
        return parryAttack.get();
    }

    public SimpleIntegerProperty parryAttackProperty() {
        return parryAttack;
    }

    public void setParryAttack(int parryAttack) {
        this.parryAttack.set(parryAttack);
    }

    public int getPhantomGet() {
        return phantomGet.get();
    }

    public SimpleIntegerProperty phantomGetProperty() {
        return phantomGet;
    }

    public void setPhantomGet(int phantomGet) {
        this.phantomGet.set(phantomGet);
    }

    public int getPhantomSkill() {
        return phantomSkill.get();
    }

    public SimpleIntegerProperty phantomSkillProperty() {
        return phantomSkill;
    }

    public void setPhantomSkill(int phantomSkill) {
        this.phantomSkill.set(phantomSkill);
    }

    public int getRoleChange() {
        return roleChange.get();
    }

    public SimpleIntegerProperty roleChangeProperty() {
        return roleChange;
    }

    public void setRoleChange(int roleChange) {
        this.roleChange.set(roleChange);
    }

    public int getRoleDeath() {
        return roleDeath.get();
    }

    public SimpleIntegerProperty roleDeathProperty() {
        return roleDeath;
    }

    public void setRoleDeath(int roleDeath) {
        this.roleDeath.set(roleDeath);
    }

    public int getTotalBattle() {
        return totalBattle.get();
    }

    public SimpleIntegerProperty totalBattleProperty() {
        return totalBattle;
    }

    public void setTotalBattle(int totalBattle) {
        this.totalBattle.set(totalBattle);
    }

    public int getTotalParalysis() {
        return totalParalysis.get();
    }

    public SimpleIntegerProperty totalParalysisProperty() {
        return totalParalysis;
    }

    public void setTotalParalysis(int totalParalysis) {
        this.totalParalysis.set(totalParalysis);
    }

    public int getTotalParry() {
        return totalParry.get();
    }

    public SimpleIntegerProperty totalParryProperty() {
        return totalParry;
    }

    public void setTotalParry(int totalParry) {
        this.totalParry.set(totalParry);
    }

    public int getTotalParryAttack() {
        return totalParryAttack.get();
    }

    public SimpleIntegerProperty totalParryAttackProperty() {
        return totalParryAttack;
    }

    public void setTotalParryAttack(int totalParryAttack) {
        this.totalParryAttack.set(totalParryAttack);
    }

    public int getTotalPhantomGet() {
        return totalPhantomGet.get();
    }

    public SimpleIntegerProperty totalPhantomGetProperty() {
        return totalPhantomGet;
    }

    public void setTotalPhantomGet(int totalPhantomGet) {
        this.totalPhantomGet.set(totalPhantomGet);
    }

    public int getTotalPhantomSkill() {
        return totalPhantomSkill.get();
    }

    public SimpleIntegerProperty totalPhantomSkillProperty() {
        return totalPhantomSkill;
    }

    public void setTotalPhantomSkill(int totalPhantomSkill) {
        this.totalPhantomSkill.set(totalPhantomSkill);
    }

    public int getTotalRoleChange() {
        return totalRoleChange.get();
    }

    public SimpleIntegerProperty totalRoleChangeProperty() {
        return totalRoleChange;
    }

    public void setTotalRoleChange(int totalRoleChange) {
        this.totalRoleChange.set(totalRoleChange);
    }

    public int getTotalRoleDeath() {
        return totalRoleDeath.get();
    }

    public SimpleIntegerProperty totalRoleDeathProperty() {
        return totalRoleDeath;
    }

    public void setTotalRoleDeath(int totalRoleDeath) {
        this.totalRoleDeath.set(totalRoleDeath);
    }

    public int getTotalTransfer() {
        return totalTransfer.get();
    }

    public SimpleIntegerProperty totalTransferProperty() {
        return totalTransfer;
    }

    public void setTotalTransfer(int totalTransfer) {
        this.totalTransfer.set(totalTransfer);
    }

    public int getTransfer() {
        return transfer.get();
    }

    public SimpleIntegerProperty transferProperty() {
        return transfer;
    }

    public void setTransfer(int transfer) {
        this.transfer.set(transfer);
    }
}