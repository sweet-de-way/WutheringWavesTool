package cn.tealc.wutheringwavestool.ui.resource;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @program: WutheringWavesTool
 * @description:
 * @author: Leck
 * @create: 2024-10-27 23:45
 */
public class UpdateView implements FxmlView<UpdateViewModel>, Initializable {
    @InjectViewModel
    private UpdateViewModel viewModel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}