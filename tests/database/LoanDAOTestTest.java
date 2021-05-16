package database;

import shared.loan.Loan;
import database.loan.LoanDAO;
import database.loan.LoanDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class LoanDAOTestTest
{
   LoanDAO loanDAO;

   @BeforeEach
  void setup(){

     loanDAO = new LoanDAOImpl();
   }

   @Test
   void getAllLoansByCprTest()
   {
     List<Loan> loans = loanDAO.getAllLoansByCPR("111111-1111");
     assertEquals(1, loans.size());
     assertEquals("Title1", loans.get(0).getMaterial().getTitle());
   }

//   @Test
//  void testCreate() throws SQLException {
//     Loan loan = loanDAO.create(1,2,"2222","bog","2021-04-22","2021-05-22");
//     assertEquals(1,loan.getMaterialID());
//   }
}