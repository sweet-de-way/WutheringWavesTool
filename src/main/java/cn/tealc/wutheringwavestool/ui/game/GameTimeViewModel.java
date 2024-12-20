package cn.tealc.wutheringwavestool.ui.game;

import cn.tealc.wutheringwavestool.base.Config;
import cn.tealc.wutheringwavestool.dao.GameTimeDao;
import cn.tealc.wutheringwavestool.dao.UserInfoDao;
import cn.tealc.wutheringwavestool.model.game.GameTime;
import com.kuro.kujiequ.model.sign.UserInfo;
import cn.tealc.wutheringwavestool.util.LanguageManager;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @program: WutheringWavesTool
 * @description:
 * @author: Leck
 * @create: 2024-08-04 04:57
 */
public class GameTimeViewModel implements ViewModel {
    private final ObservableList<XYChart.Series<String,Double>> chartData= FXCollections.observableArrayList();
    //private SimpleStringProperty todayGameTimeText=new SimpleStringProperty();
    private final ObservableList<UserInfo> userInfoList= FXCollections.observableArrayList();
    private final SimpleIntegerProperty userIndex = new SimpleIntegerProperty(-1);

    private final SimpleStringProperty allTotalTimeText=new SimpleStringProperty();
    private final SimpleStringProperty currentTotalTimeText=new SimpleStringProperty();
    private final SimpleStringProperty currentDayText=new SimpleStringProperty();
    private final SimpleStringProperty currentTimeText=new SimpleStringProperty();
    private final SimpleStringProperty currentUserName=new SimpleStringProperty();
    private final SimpleDoubleProperty currentProgressValue=new SimpleDoubleProperty();

    private final SimpleDoubleProperty totalProgressValue=new SimpleDoubleProperty();


    //private final SimpleBooleanProperty noKuJieQu = new SimpleBooleanProperty(false);

    public GameTimeViewModel() {
        if (!Config.setting.isNoKuJieQu()){
            UserInfoDao dao = new UserInfoDao();
            List<UserInfo> userInfos = dao.getAll();
            userInfoList.setAll(userInfos);
            UserInfo main = dao.getMain();
            if (main != null) {
                for (int i = 0; i < userInfoList.size(); i++) {
                    if (Objects.equals(userInfoList.get(i).getId(), main.getId())) {
                        userIndex.set(i);
                        break;
                    }
                }
                freshWithAccount();
            }

            userIndex.addListener((observableValue, number, t1) -> {
                if (t1 != null) {
                    freshWithAccount();
                }
            });
        }else {
            freshWithoutAccount();
        }




    }




    /**
     * @description: 使用库街区账号时
     * @param:
     * @return  void
     * @date:   2024/10/8
     */
    private void freshWithAccount() {
        GameTimeDao gameTimeDao = new GameTimeDao();
        //统计所有账号全部时长
        List<GameTime> gameTimeList = gameTimeDao.getAllTime();
        Map<String, List<GameTime>> allTimeMap = getMap(gameTimeList);
        double totalTime = sunTime(allTimeMap);
        allTotalTimeText.set(String.format("%.2f", totalTime));

        UserInfo userInfo = userInfoList.get(userIndex.get());
        List<GameTime> timeListByRoleId = gameTimeDao.getTimeListByRoleId(userInfo.getRoleId());
        Map<String, List<GameTime>> mainMap = getMap(timeListByRoleId);

        currentDayText.set(String.format(LanguageManager.getString("ui.game_time.account.days"), mainMap.keySet().size()));

        Map<String, List<GameTime>> mapInWeek = getMapInWeek(timeListByRoleId);
        //统计七日时长图表
        updateChartDate(mapInWeek,LanguageManager.getString("ui.game_time.total.charts.main_account"));
        //统计当前账号全部时长
        double currentTotalTime = sunTime(mainMap);
        currentTotalTimeText.set(String.format("%.2f", currentTotalTime));

        currentUserName.set(userInfo.getRoleName());

        totalProgressValue.set(currentTotalTime/totalTime);

        updateCurrentGameTime();
    }


    /**
     * @description: 不使用库街区时
     * @param:
     * @return  void
     * @date:   2024/10/8
     */
    private void freshWithoutAccount() {
        GameTimeDao gameTimeDao = new GameTimeDao();
        //统计所有账号全部时长
        List<GameTime> gameTimeList = gameTimeDao.getAllTime();
        Map<String, List<GameTime>> allTimeMap = getMap(gameTimeList);
        double totalTime = sunTime(allTimeMap);
        allTotalTimeText.set(String.format("%.2f", totalTime));



        currentDayText.set(String.format(LanguageManager.getString("ui.game_time.account.days"), allTimeMap.keySet().size()));

        Map<String, List<GameTime>> mapInWeek = getMapInWeek(gameTimeList);
        //统计七日时长图表
        updateChartDate(mapInWeek,LanguageManager.getString("ui.game_time.total.charts.title"));
        //统计当前账号全部时长
        double currentTotalTime = sunTime(allTimeMap);
        currentTotalTimeText.set(String.format("%.2f", currentTotalTime));

        currentUserName.set(LanguageManager.getString("ui.game_time.account.unknown"));

        totalProgressValue.set(currentTotalTime/totalTime);

        updateCurrentGameTime();
    }


    /**
     * @description: 对数据库的记录按照日期分类
     * @param:	list
     * @return  java.util.Map<java.lang.String,java.util.List<cn.tealc.wutheringwavestool.model.game.GameTime>>
     * @date:   2024/8/4
     */
    private Map<String,List<GameTime>> getMapInWeek(List<GameTime> list){
        Map<String,List<GameTime>> map = new LinkedHashMap<>();


        for (int i = list.size() - 1; i >= 0; i--) {
            GameTime gameTime=list.get(i);
            String key=gameTime.getGameDate();
            if (!map.containsKey(key)) {
                if (map.keySet().size() < 7){
                    List<GameTime> temp = new ArrayList<>();
                    temp.add(gameTime);
                    map.put(key,temp);
                }
            }else {
                map.get(key).add(gameTime);
            }
        }
        return map;
    }

    private Map<String,List<GameTime>> getMap(List<GameTime> list){
        Map<String,List<GameTime>> map = new LinkedHashMap<>();
        for (GameTime gameTime : list) {
            String key=gameTime.getGameDate();
            if (!map.containsKey(key)) {
                List<GameTime> temp = new ArrayList<>();
                temp.add(gameTime);
                map.put(key,temp);
            }else {
                map.get(key).add(gameTime);
            }
        }
        return map;
    }
    /**
     * @description: 更新图表
     * @param:	map
     * @param:	name
     * @return  void
     * @date:   2024/8/4
     */
    private void updateChartDate(Map<String,List<GameTime>> map,String name){
        XYChart.Series<String,Double> series = new XYChart.Series<>();
        series.setName(name);


        for (String key : map.keySet()) {
            List<GameTime> gameTimes = map.get(key);
            long count = gameTimes.stream().mapToLong(GameTime::getDuration).sum();
            double minute = count / 1000.0 / 60.0;
            series.getData().add(new XYChart.Data<>(key, minute));
        }
        FXCollections.reverse(series.getData());

        chartData.add(series);
    }


    /**
     * @description: 计算出全部时间，以小时为单位
     * @param:	map
     * @return  double
     * @date:   2024/8/4
     */
    private double sunTime(Map<String,List<GameTime>> map){
        long total=0;
        for (String key : map.keySet()) {
            List<GameTime> gameTimes = map.get(key);
            long count = gameTimes.stream().mapToLong(GameTime::getDuration).sum();
            total = total + count;
        }
        return total/1000.0/60.0/60.0;
    }

    /**
     * @description: 获取当前账号游玩时间
     * @param:
     * @return  void
     * @date:   2024/8/4
     */
    private void updateCurrentGameTime(){
        GameTimeDao gameTimeDao=new GameTimeDao();
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateTimeFormatter.format(localDate);
        List<GameTime> list = gameTimeDao.getTimeListByData(date);
        if (list !=null){
            long sum = list.stream().mapToLong(GameTime::getDuration).sum();
            int hour = (int) (sum / (1000 * 60 * 60));
            int minute = (int) ((sum % (1000 * 60 * 60)) / (1000 * 60));

            currentProgressValue.set(sum / (1000.0 * 60.0 * 60.0)/24.0);

            currentTimeText.set(String.format(LanguageManager.getString("ui.game_time.account.duration"),hour,minute));
        }
    }

    public ObservableList<XYChart.Series<String, Double>> getChartData() {
        return chartData;
    }

    public int getUserIndex() {
        return userIndex.get();
    }

    public SimpleIntegerProperty userIndexProperty() {
        return userIndex;
    }

    public String getAllTotalTimeText() {
        return allTotalTimeText.get();
    }

    public SimpleStringProperty allTotalTimeTextProperty() {
        return allTotalTimeText;
    }

    public String getCurrentTotalTimeText() {
        return currentTotalTimeText.get();
    }

    public SimpleStringProperty currentTotalTimeTextProperty() {
        return currentTotalTimeText;
    }

    public String getCurrentDayText() {
        return currentDayText.get();
    }

    public SimpleStringProperty currentDayTextProperty() {
        return currentDayText;
    }

    public String getCurrentTimeText() {
        return currentTimeText.get();
    }

    public SimpleStringProperty currentTimeTextProperty() {
        return currentTimeText;
    }

    public double getCurrentProgressValue() {
        return currentProgressValue.get();
    }

    public SimpleDoubleProperty currentProgressValueProperty() {
        return currentProgressValue;
    }

    public double getTotalProgressValue() {
        return totalProgressValue.get();
    }

    public SimpleDoubleProperty totalProgressValueProperty() {
        return totalProgressValue;
    }

    public ObservableList<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public String getCurrentUserName() {
        return currentUserName.get();
    }

    public SimpleStringProperty currentUserNameProperty() {
        return currentUserName;
    }
}