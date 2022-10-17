package MiniBank;

// Bank 인터페이스
public interface BankInterface {
	// 계좌생성
	abstract public void MakeAccount();
	// 마이너스 계좌 생성
	abstract public void MakeMinusAccount();
	// 계좌해지
	abstract public void DeleteAccount();
}
