package client;

import client.view.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * App class
 *
 * @author Michael
 * @version 1.0
 */
public class App extends Application
{
  @Override public void start(Stage stage) throws Exception
  {
    ViewHandler.getInstance().start(stage);
  }
}
