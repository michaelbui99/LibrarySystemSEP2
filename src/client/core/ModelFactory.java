package client.core;

import client.model.chat.ChatModelClient;
import client.model.chat.ChatModelManagerClient;
import client.model.library.LibraryModelClient;
import client.model.library.LibraryModelManager;
import client.model.loan.LoanModelClient;
import client.model.material.MaterialModelClient;
import client.model.user.UserModelClient;

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


}
