package MiniBank;

// 계좌정보 인터페이스
public interface AccountOperationInterface {
	// 예금
	abstract public void Deposit(int amount);
	// 출금
	abstract public void Withdraw(int amount);
	// 송금
	abstract public void Transfer(int amount);
}
