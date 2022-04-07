import javafx.application.Application;
import javafx.stage.Stage;
import mediator.RmiClient;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application
{
  public void start(Stage primaryStage)
  {
    try
    {
      /*
      Model model = new ModelManager();

      ViewModelFactory viewModelFactory = new ViewModelFactory(model);
      ViewHandler view = new ViewHandler(viewModelFactory);

      view.start(primaryStage);

       */
      RmiClient rmiClient = new RmiClient();
      rmiClient.setUsername("BobTest");
      rmiClient.send("I'm locked in");
      // client.execute();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
