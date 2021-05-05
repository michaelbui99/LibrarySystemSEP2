package database;

import database.loan.LoanDAO;
import database.loan.LoanDAOImpl;
import org.junit.jupiter.api.BeforeEach;

class LoanDAOTestTest
{
   LoanDAO loanDAO;

   @BeforeEach
  void setup(){

     loanDAO = new LoanDAOImpl();
   }

//   @Test
//  void testCreate() throws SQLException {
//     Loan loan = loanDAO.create(1,2,"2222","bog","2021-04-22","2021-05-22");
//     assertEquals(1,loan.getMaterialID());
//   }
}