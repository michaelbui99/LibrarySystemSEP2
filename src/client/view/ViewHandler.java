package client.view;

import client.core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class ViewHandler extends Application
{
  private Stage stage;
  private ViewModelFactory vmf;

  public ViewHandler(Stage stage, ViewModelFactory vmf)
  {
    this.stage = stage;
    this.vmf = vmf;
  }

  public void openView(String id)
  {

  }

  @Override public void start(Stage stage) throws Exception
  {
    openView("mainView");
  }
}
