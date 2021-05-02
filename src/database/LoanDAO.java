package database;

import client.model.loan.Loan;

import java.sql.SQLException;

public interface LoanDAO
{
  Loan create(int materialID, int copyNumber, String cpr,
      String loanDate, String deadline) throws SQLException;
}
