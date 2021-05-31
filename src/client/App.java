package client;

import client.view.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

//Michael
public class App extends Application
{
  @Override public void start(Stage stage) throws Exception
  {
    ViewHandler.getInstance().start(stage);
  }
}
