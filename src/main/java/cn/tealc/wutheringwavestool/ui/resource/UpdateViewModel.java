package cn.tealc.wutheringwavestool.ui.resource;

import com.kuro.game.thread.BaseInfoTask;
import de.saxsys.mvvmfx.ViewModel;

import java.io.Serializable;

/**
 * @program: WutheringWavesTool
 * @description:
 * @author: Leck
 * @create: 2024-10-27 23:45
 */
public class UpdateViewModel implements ViewModel {
    public UpdateViewModel() {
        BaseInfoTask task = new BaseInfoTask();
        task.setOnSucceeded(workerStateEvent -> {
            System.out.println(task.getValue().getData().getVersion());
        });

        Thread.startVirtualThread(task);

    }
}