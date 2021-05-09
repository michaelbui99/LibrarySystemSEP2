package client.core;

import client.model.chat.ChatModelClient;
import client.model.chat.ChatModelManagerClient;
import client.model.library.LibraryModelClient;
import client.model.library.LibraryModelManager;
import client.model.loan.LoanModelClient;
import client.model.loan.LoanModelManagerClient;
import client.model.material.MaterialModelClient;
import client.model.material.MaterialModelManagerClient;
import client.model.user.UserModelClient;
import client.model.user.UserModelManagerClient;
import client.network.Client;

public class ModelFactory
{
private static ModelFactory modelFactory;
public static ModelFactory getInstance(){
  if (modelFactory == null){

    modelFactory = new ModelFactory();
  }
  return modelFactory;
}

private ChatModelClient chatModelClient;
private LibraryModelClient libraryModelClient;
private LoanModelClient loanModelClient;
private MaterialModelClient materialModelClient;
private UserModelClient userModelClient;

public ChatModelClient getChatModelClient(){
  if (chatModelClient == null){
    chatModelClient = new ChatModelManagerClient((Client)ClientFactory.getClientFactory()
        .getClient());
  }
  return chatModelClient;
}

public LibraryModelClient getLibraryModelClient(){
  if (libraryModelClient == null){
    libraryModelClient = new LibraryModelManager(ClientFactory.getClientFactory().getClient());
  }
  return libraryModelClient;
}

public LoanModelClient getLoanModelClient(){
  if (loanModelClient == null){
    loanModelClient = new LoanModelManagerClient((Client)ClientFactory.getClientFactory().getClient());
  }
  return loanModelClient;
}

public MaterialModelClient getMaterialModelClient(){
  if (materialModelClient == null){
    materialModelClient = (MaterialModelClient) new MaterialModelManagerClient((Client)ClientFactory.getClientFactory().getClient());
  }
  return materialModelClient;
}

public UserModelClient getUserModelClient(){
  if (userModelClient==null){
    userModelClient = new UserModelManagerClient((Client)ClientFactory.getClientFactory().getClient());
  }
  return userModelClient;
}
}
