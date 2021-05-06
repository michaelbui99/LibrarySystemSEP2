package database.loan;

import client.model.loan.Loan;

import java.sql.SQLException;
import java.util.List;

public interface LoanDAO
{
  Loan create(int materialID, int copyNumber, String cpr,
      String loanDate, String deadline) throws SQLException;
  List<Loan> getAllLoansByCPR(String cpr) throws SQLException;
}
