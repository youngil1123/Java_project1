package MiniBank;

// 계좌 인터페이스를 구현하는 계좌클래스
public class Account implements AccountOperationInterface {

	// 예금주
	String Name;
	// 계좌번호
	String AccountNumber;
	// 잔액
	int Balance;
	
	// 생성자
	public Account(String name, String accountNumber, int balance)
	{
		Name = name;
		AccountNumber = accountNumber;
		Balance = balance;
	}
	
	// 예금 인터페이스 구현
	@Override
	public void Deposit(int amount) {
		
		Balance += amount;
	}

	// 출금 인터페이스 구현
	@Override
	public void Withdraw(int amount) {
		Balance -= amount;
	}

	// 송금 인터페이스 구현
	@Override
	public void Transfer(int amount) {
		Balance -= amount;
	}
}
