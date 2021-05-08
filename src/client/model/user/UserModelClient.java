package client.model.user;

import shared.PropertyChangeSubject;

public interface UserModelClient extends PropertyChangeSubject
{
  Borrower registerBorrower();
  boolean Login();
}
