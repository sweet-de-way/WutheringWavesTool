package cn.tealc.wutheringwavestool.thread;

import cn.tealc.wutheringwavestool.dao.GameRecordDao;
import cn.tealc.wutheringwavestool.dao.UserInfoDao;
import cn.tealc.wutheringwavestool.model.game.GameRecord;
import cn.tealc.wutheringwavestool.util.GameResourcesManager;
import com.kuro.kujiequ.model.sign.UserInfo;
import javafx.concurrent.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @program: WutheringWavesTool
 * @description:
 * @author: Leck
 * @create: 2024-11-13 15:08
 */
public class GameLogFileAnalysisTask extends Task<Boolean>{
    private static final Logger LOG= LoggerFactory.getLogger(GameLogFileAnalysisTask.class);

    private enum Type{
        ROLE_CHANGE(false,"角色下场，立即隐藏"),
        ROLE_DEATH(false,"前台角色死亡进行切人"),
        BATTLE(false,"切换玩家状态: 进入战斗造成伤害"),
        PHANTOM_GET(false,"[技能名称: 初次幻象收服]"),
        PHANTOM_CALL_SKILL(false,"召唤系幻象的出生特效"),
        PHANTOM_TRANSFORM_SKILL(false,"结束技能名称: 变身幻象"),
        PARALYSIS(false,"进入倒地状态"),
        TRANSFER(false,"传送:完成"),
        PARRY_FRONT(true,"结束技能名称: (.+)?极限闪避前闪"),
        PARRY_BACK(true,"结束技能名称: (.+)?极限闪避后闪"),
        PARRY_ATTACK(true,"结束技能名称: (.+)?极限闪避反击");

        private final boolean regex;
        private final String key;
        Type(boolean regex,String key) {
            this.key = key;
            this.regex = regex;
        }

    }


    @Override
    protected Boolean call() throws Exception {
        Pattern parryFrontPattern = Pattern.compile(Type.PARRY_FRONT.key);
        Pattern parryBackPattern = Pattern.compile(Type.PARRY_BACK.key);
        Pattern parryAttackPattern = Pattern.compile(Type.PARRY_ATTACK.key);

        GameRecord record=new GameRecord();
        File dir = GameResourcesManager.getGameLogDir();
        if (dir != null){
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files){
                    try (BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            for (Type value : Type.values()) {
                                if (!value.regex){
                                    if (line.contains(value.key)) {
                                        switch (value) {
                                            case ROLE_CHANGE -> record.setRoleChange(record.getRoleChange() + 1);
                                            case ROLE_DEATH -> record.setRoleDeath(record.getRoleDeath() + 1);
                                            case BATTLE -> record.setBattle(record.getBattle() + 1);
                                            case PHANTOM_GET -> record.setPhantomGet(record.getPhantomGet() + 1);
                                            case PHANTOM_CALL_SKILL -> record.setPhantomCallSkill(record.getPhantomCallSkill() + 1);
                                            case PHANTOM_TRANSFORM_SKILL -> record.setPhantomTransformSkill(record.getPhantomTransformSkill() + 1);
                                            case PARALYSIS -> record.setParalysis(record.getParalysis() + 1);
                                            case TRANSFER -> record.setTransfer(record.getTransfer() + 1);
                                        }
                                    }
                                }
                            }
                            if (parryFrontPattern.matcher(line).find()) {
                                record.setParryFront(record.getParryFront() + 1);
                            }else if (parryBackPattern.matcher(line).find()) {
                                record.setParryBack(record.getParryBack() + 1);
                            }
                            //闪避反击与闪避可同时存在
                            if (parryAttackPattern.matcher(line).find()) {
                                record.setParryAttack(record.getParryAttack() + 1);
                            }
                        }
                    } catch (IOException e) {
                        LOG.error(e.getMessage());
                    }
                }
            }
        }

        int num=record.getRoleChange() + record.getRoleDeath() + record.getBattle()
                +record.getPhantomGet()+record.getPhantomCallSkill()+record.getPhantomTransformSkill()
                +record.getParalysis()+record.getTransfer()+record.getParryFront()
                +record.getParryBack()+record.getParryAttack();
        if (num > 0){
            //有数据，保存
            UserInfoDao userInfoDao =new UserInfoDao();
            UserInfo main = userInfoDao.getMain();
            if (main != null){
                record.setRoleId(main.getRoleId());
            }
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = currentDate.format(formatter);
            record.setCreateDate(formattedDate);
            GameRecordDao dao =new GameRecordDao();
            dao.addOrUpdateRecord(record);
            LOG.info(record.toString());
        }else {
            LOG.info("日志无可用数据，无需更新");
        }
        return true;
    }



}